package ui;

import model.CardButton;
import model.Card;
import model.MatchingController;

import utils.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;

/**
 * Timed Mode gameplay screen for a chosen difficulty.
 * Builds the board, manages the countdown timer, updates score, and
 * allows the user to restart or quit the timed game.
 * (Requirements 1.2.0, 2.0.0, 2.1.0, 2.2.0, 3.0.0, 3.1.0)
 */

// Generates an entire game screen according to the difficulty mode chosen
public class TimedModeGameScreen extends JFrame {
	private final GameConfig config;				// Difficulty configuration object
    private final List<JButton> cardButtons;		// Card buttons list array to contain them all
    private HighScoreUpdater score;					// High Score object
    private JLabel highScoreValue;					// Label to display the current high score dynamically
    private JLabel multiplierValue;					// Label to display the current multiplier dynamically
    private JLabel timerValue;						// Label to display the current time
    private MatchingController controller;			// Controller object
    private int currentTime;						// Current time in seconds
    private Timer countdown;						// The time limit that is counted down by seconds

    public TimedModeGameScreen(GameConfig config) {
        super(config.title);						// Super constructor
        this.config = config;						// Match parameter with the local

        AudiowideFont.register();					// Load font
        setDefaultCloseOperation(EXIT_ON_CLOSE);	// Include an option to close the window
        setLocationRelativeTo(null);				// Generate window on the center of the monitor
        
        // Apply the selected background color for a given difficulty mode
        setContentPane(ApplySolidBackgroundColor.createSolidBackgroundPanel(config.bgColor));
        // Use BorderLayout for placing the header (NORTH) and game grid (CENTER)
        getContentPane().setLayout(new BorderLayout());
        
        // Load all card face icons into memory so that they can be displayed instantly
        CardIconography.loadFaceIcons();

        // ===== UI HEADER =====
        JPanel header = buildHeader();
        add(header, BorderLayout.NORTH);

        // ===== GAME GRID =====
        GenerateGameGrid.GameGrid grid = GenerateGameGrid.makeGameGrid(
                config.rows,
                config.cols,
                config.cardSize,
                config.hGap, // Horizontal gap
                config.vGap	 // Vertical gap
        );

        // Store the list of JButtons that represent all the cards
        this.cardButtons = grid.cards;
        // Add the card grid panel to the center of the screen
        add(grid.grid, BorderLayout.CENTER);
        
        // Fit the window to preferred sizes of the components
        pack();
        // Resolution size of the windows
        setSize(1280, 900);
        // Center window on the screen
        setLocationRelativeTo(null);
        // Make the window visible
        setVisible(true);

        // Assign random card pairs
        ApplyCardIcons.applyRandomPairs(cardButtons, config.cardSize, 144);

        // Wrap JButtons → CardButtons
        List<CardButton> wrappedCards =
                ConvertJButtonsToCardButtons.buildCardButtonsFromSwingButtons(cardButtons, config.cardSize);
        
        // Retrieve the universal back icon for cards
        Icon backIcon = cardButtons.get(0).getIcon();

		// Base score along with a multiplier that could go to infinity
        score = new HighScoreUpdater(200, Integer.MAX_VALUE);

        // ===== CONTROLLER =====
        controller = new MatchingController(
                config.flipDelay, // Delay in milliseconds for the cards to flip back down
                
		        // Ties the card buttons with the controller settings of ID, icon states, and the size of said icons and updates them accordingly
                cb -> {
		            // Casts the object of ID retrieval with the integer number of card IDs
                    int cardId = (int) cb.button.getClientProperty("cardId");

		            // Face Icon of cards will be shown if the card is in a facing-up state
                    if (cb.cardRef.isFacingUp) {
                        Icon face = CardIconography.getScaledIcon(
                                cardId,
                                config.cardSize.width,
                                config.cardSize.height
                        );
                        cb.button.setIcon(face);
                    }
                    
		            // The opposite will be true and they wil go back to their default states of being face down
                    else {
		                // Set back-of-card icon here with the backIcon png image
                        cb.button.setIcon(backIcon);
                    }
                    highScoreValue.setText(String.valueOf(score.getScore()));
                },
                
		        // End game popup parameter
                () -> {
                	// Stops the timer upon a victory
                	if (countdown != null && countdown.isRunning())
                	    countdown.stop();
                	
		            // Victory text to be printed out in the java console
                	ProjectEndOfGamePopup.showVictoryPopup(
                            this,				// Parent JFrame window
                            score.getScore(),	// Final Score
                            formatTimeRemaining(),	// Formatted time label
                            "Victory!",
                            () -> new TimedModeGameScreen(config).setVisible(true),	// Replay game
                            () -> new ui.TimedModeMenuFrame().setVisible(true)		// Quit game
                    );
                },
                
		        // High Score parameter for the pairResolved callback
                matched -> {
                    if (matched) {
                    	score.forMatch();
                    	
                        // Add seconds equal to the multiplier to add bonus time
                        currentTime += score.getMultiplier();

                        // Keep timer UI updated and formatted
                        timerValue.setText(formatTimeRemaining());
                    }
                    else score.forMismatch();
                    
		        	// Display both the updated high score and multiplier on screen
                    highScoreValue.setText(String.valueOf(score.getScore()));
                    multiplierValue.setText("x" + score.getMultiplier());
                }
        );
        
        // Hook the cards into the controller so clicks work
        controller.attach(wrappedCards);
        // Start the countdown after everything has been loaded on the game screen
        SwingUtilities.invokeLater(() -> initiateCountdown());
    }
    
    // ===== UI HEADER BUILDER =====
    private JPanel buildHeader() {

        // Back button creation
        JButton backBtn = ButtonCreation.createBackOutOfCurrentGameButton();
        
        // Back button on click logic
        backBtn.addActionListener(e -> {
        	// Stops the timer upon clicking on the back button
        	if (countdown != null && countdown.isRunning())
        	    countdown.stop();
        	
			// Puts a pause on the game upon click with assurance
            if (controller != null) controller.pauseGame(true);
		    
            // Show confirmation dialog
            utils.QuitGame.confirmUserQuitting(this, () -> {
		        // User confirmed quitting
                new ui.TimedModeMenuFrame().setVisible(true);
                this.dispose();
            });

		    // Resume gameplay if the frame is still open (user cancelled)
            if (controller != null) controller.pauseGame(false);
        });
        
        // Title label text and stylization
        JLabel titleLabel = new JLabel(config.title, SwingConstants.CENTER);
        titleLabel.setFont(AudiowideFont.get(30f, Font.PLAIN));
        titleLabel.setBorder(
                BorderFactory.createCompoundBorder(
                        new ApplyRoundedBorder(4, 4, Color.BLACK),
                        BorderFactory.createEmptyBorder(10, 25, 10, 25)
                )
        );
        
        // Wrap tightly around title
        JPanel titleWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        titleWrap.setOpaque(false);
        titleWrap.add(titleLabel);
        
        // Top row panel of the header
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);
        topRow.setBorder(BorderFactory.createEmptyBorder(8, 16, 0, 16));
        topRow.add(backBtn, BorderLayout.WEST);
        topRow.add(titleWrap, BorderLayout.CENTER);

        // High score title display
        JLabel highScoreTitle = new JLabel("High Score:");
        highScoreTitle.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // High score value display
        highScoreValue = new JLabel("0");
        highScoreValue.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // Creates a width wide enough for any number up to 6 digits
        highScoreValue.setPreferredSize(new Dimension(120, highScoreValue.getPreferredSize().height));
        // CENTER the text within that fixed width
        highScoreValue.setHorizontalAlignment(SwingConstants.CENTER);
        
        // High score box to contain both the title and value
        JPanel highScoreBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        highScoreBox.setOpaque(false);
        highScoreBox.add(highScoreTitle);
        highScoreBox.add(highScoreValue);
        
        // Multiplier title display
        JLabel multiplierTitle = new JLabel("Score Multiplier:");
        multiplierTitle.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // Multiplier value display
        multiplierValue = new JLabel("x1");
        multiplierValue.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // Creates a width wide enough for any number up to 3 digits
        multiplierValue.setPreferredSize(new Dimension(70, multiplierValue.getPreferredSize().height));
        // CENTER the text within that fixed width
        multiplierValue.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Multiplier box to contain both the title and value
        JPanel multiplierBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        multiplierBox.setOpaque(false);
        multiplierBox.add(multiplierTitle);
        multiplierBox.add(multiplierValue);
        
        // Timer title display
        JLabel timerTitle = new JLabel("Timer:");
        timerTitle.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // Timer value display (infinity sign to represent unlimited time)
        timerValue = new JLabel("∞");
        timerValue.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // Creates a width wide enough for any number up to 3 digits
        timerValue.setPreferredSize(new Dimension(80, 40));
        // CENTER the text within that fixed width
        timerValue.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Timer box to contain both the title and value
        JPanel timerBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        timerBox.setOpaque(false);
        timerBox.add(timerTitle);
        timerBox.add(timerValue);
        
        // A second row to contain all the boxes (high score, multiplier, timer)
        JPanel gameStatusRow = new JPanel();
        gameStatusRow.setOpaque(false);
        gameStatusRow.setLayout(new BoxLayout(gameStatusRow, BoxLayout.X_AXIS));
        gameStatusRow.add(highScoreBox);
        gameStatusRow.add(Box.createHorizontalGlue());
        gameStatusRow.add(multiplierBox);
        gameStatusRow.add(timerBox);
        
        // The full header to add the top two rows in a vertical alignment
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(topRow);
        header.add(Box.createVerticalStrut(16)); // Some space to be added in between
        header.add(gameStatusRow);

        return header;
    }
    

    //===== COUNTDOWN LOGIC =====
    
    private void initiateCountdown() {
        currentTime = config.timeLimit; // Time limit for a given game mode

        timerValue.setText(String.valueOf(formatTimeRemaining())); // Sets the initial starting time
        
        // Makes the count down that will decrease the time per second
        countdown = new Timer(1000, e -> {
        	currentTime--;
            timerValue.setText(String.valueOf(formatTimeRemaining())); // Updated and formatted string of the time
            
            // Game over
            if (currentTime <= 0) {
                countdown.stop();
                onTimeExpired();
         }
	});
        // Begins the count down
        countdown.start();
    }
    

    // Method to display the failure screen when all the time is ran out
    private void onTimeExpired() {
    	
    	// Stops the timer upon the time limit being exhausted
    	if (countdown != null && countdown.isRunning())
    	    countdown.stop();
    	
    	// Display the failure popup with such parameters
    	ProjectEndOfGamePopup.showFailurePopup(
    			this,
    			score.getScore(),
    			"00:00",
    			"Failure",
    			() -> new TimedModeGameScreen(config).setVisible(true),
    			() -> new ui.TimedModeMenuFrame().setVisible(true)
    			);    	
    }
    
    // To format the time of a given game into seconds
    private String formatTimeRemaining() {
    	return currentTime + "s";
    }
	
	// Getter method to get the current game mode for a given game
	public GameConfig getConfig() {
    	return config;
    }
	
}

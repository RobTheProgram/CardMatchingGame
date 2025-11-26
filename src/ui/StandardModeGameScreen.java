package ui;

import model.CardButton;
import model.Card;
import model.MatchingController;

import utils.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;

// Generates an entire game screen according to the difficulty mode chosen
public class StandardModeGameScreen extends JFrame {
	private final GameConfig config;				// Difficulty configuration object
    private final List<JButton> cardButtons;		// Card buttons list array to contain them all
    private HighScoreUpdater score;					// High Score object
    private JLabel highScoreValue;					// Label to display the current high score dynamically
    private JLabel multiplierValue;					// Label to display the current multiplier dynamically
    private MatchingController controller;			// Controller object

    public StandardModeGameScreen(GameConfig config) {
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
                    multiplierValue.setText("x" + score.getMultiplier());
                },
                
		        // Victory parameter
                () -> {
		            // Victory text to be printed out in the java console
                    ProjectVictoryPopup.showVictoryPopup(
                            this,				// Parent JFrame window
                            score.getScore(),	// Final Score
                            "N/A",				// Formatted time label
                            () -> new StandardModeGameScreen(config).setVisible(true),	// Replay game
                            () -> new ui.StandardModeMenuFrame().setVisible(true)		// Quit game
                    );
                },
                
		        // High Score parameter for the pairResolved callback
                matched -> {
                    if (matched) score.forMatch();
                    else score.forMismatch();
                    
		        	// Display both the updated high score and multiplier on screen
                    highScoreValue.setText(String.valueOf(score.getScore()));
                    multiplierValue.setText("x" + score.getMultiplier());
                }
        );
        
        // Hook the cards into the controller so clicks work
        controller.attach(wrappedCards);
    }

    // ===== UI HEADER BUILDER =====
    private JPanel buildHeader() {

        // Back button creation
        JButton backBtn = ButtonCreation.createBackOutOfCurrentGameButton();
        
        // Back button on click logic
        backBtn.addActionListener(e -> {
			// Puts a pause on the game upon click with assurance
            if (controller != null) controller.pauseGame(true);
		    
            // Show confirmation dialog
            utils.QuitGame.confirmUserQuitting(this, () -> {
		        // User confirmed quitting
                new ui.StandardModeMenuFrame().setVisible(true);
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
        
        // Multiplier box to contain both the title and value
        JPanel multiplierBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        multiplierBox.setOpaque(false);
        multiplierBox.add(multiplierTitle);
        multiplierBox.add(multiplierValue);
        
        // Timer title display
        JLabel timerTitle = new JLabel("Timer:");
        timerTitle.setFont(AudiowideFont.get(28f, Font.PLAIN));
        
        // Timer value display (infinity sign to represent unlimited time)
        JLabel timerValue = new JLabel("∞");
        timerValue.setFont(new Font("Dialog", Font.PLAIN, 40));
        
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
    
    // Getter method to get the current game mode for a given game
    public GameConfig getConfig() {
    	return config;
    }
	
}

package ui;

import utils.HandCursorUtility;
import utils.GenerateGameGrid;
import utils.ButtonCreation;
import utils.BoxLayoutSetup;
import utils.AudiowideFont;
import utils.ApplyRoundedBorder;
import utils.ApplySolidBackgroundColor; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.util.List;


import java.awt.*;
import java.awt.geom.Point2D;

public class GameScreenForStandardModeHard extends JFrame {

    // Keep the cards for wiring listeners / model later
    private final List<JButton> cards;
    
	public GameScreenForStandardModeHard() {
		// Title and setup execution
		super("Standard Mode [Hard]");
		// Called once to apply the Audiowide font
		AudiowideFont.register();
		// Gives the X close button on window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// The window, when executed, won't generate according to any position on screen
		setLocationRelativeTo(null);
		// Set the background color of the screen
		setContentPane(ApplySolidBackgroundColor.createSolidBackgroundPanel(Color.decode("#f2d486")));
		// To set layout to this new gradient panel
		getContentPane().setLayout(new BorderLayout());
		
		//===== Main Page Title Label Section =====
		JLabel titleLabel = new JLabel("Standard Mode [Hard]", SwingConstants.CENTER);
		titleLabel.setFont(AudiowideFont.get(30f, Font.PLAIN));
		titleLabel.setOpaque(false); // Make border background transparent
		
		//===== To wrap the border tightly around the main page =====
		JPanel titleWrap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		titleWrap.setOpaque(false);
		titleWrap.add(titleLabel);
		
		// Border Creation for Main Page Title Label
		titleLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(4, 4, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		titleLabel.setFocusable(false); // Prevents any focus upon clicking on the label

		//===== High Score Label Section =====
		JLabel highScoreLabel = new JLabel("High Score:");
		highScoreLabel.setFont(AudiowideFont.get(30f, Font.PLAIN));
		highScoreLabel.setOpaque(false); // Make border background transparent
		
		// The label field for dynamically displaying the current high score (0 by default)
		JLabel highScoreValue = new JLabel("0");
		highScoreValue.setFont(AudiowideFont.get(28f, Font.PLAIN));
		
		//===== Timer Label Section =====
		JLabel timerLabel = new JLabel("Timer:", SwingConstants.CENTER);
		timerLabel.setFont(AudiowideFont.get(30f, Font.PLAIN));
		timerLabel.setOpaque(false); // Make border background transparent
		
		// The label field for dynamically displaying the time (Infinite for Standard Mode)
		JLabel timerValue = new JLabel("\u221E"); // infinity sign
		timerValue.setFont(new Font("Dialog", Font.PLAIN, 40));
		
		//===== Alignment Section (Header) =====
		
		// To pair the both the high score label and values together to stick them to the left
		JPanel highScoreComponentsLeftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		highScoreComponentsLeftSide.setOpaque(false);
		highScoreComponentsLeftSide.add(highScoreLabel);
		highScoreComponentsLeftSide.add(highScoreValue);
		
		// To pair the both the timer label and values together to stick them to the right
		JPanel timerComponentsRightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
		timerComponentsRightSide.setOpaque(false);
		timerComponentsRightSide.add(timerLabel);
		timerComponentsRightSide.add(timerValue);
		
		// Game Status Row Section
		JPanel gameStatusRow = new JPanel();
		gameStatusRow.setOpaque(false);
		gameStatusRow.setLayout(new BoxLayout(gameStatusRow, BoxLayout.X_AXIS));
		gameStatusRow.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
		gameStatusRow.add(highScoreComponentsLeftSide);
		gameStatusRow.add(Box.createHorizontalGlue());    // Pushes right chunk to the right edge
		gameStatusRow.add(timerComponentsRightSide);
		
		// To create a header section that takes in and centers the components
		JPanel header = new JPanel();
		header.setOpaque(false);
		header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
		header.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16)); // To add padding from the window edges
		titleWrap.setAlignmentX(Component.CENTER_ALIGNMENT);
		gameStatusRow.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add all components to the header
		header.add(titleWrap);
		header.add(Box.createVerticalStrut(16)); // To add space in-between
		header.add(gameStatusRow);
		
		// Add full header to the window
		add(header, BorderLayout.NORTH);
				
		//===== Game Grid 7x8 Section =====
		// Card size for hard mode
		Dimension cardSize = new Dimension(65, 80);
		
		GenerateGameGrid.GameGrid grid = GenerateGameGrid.makeGameGrid(
				7, // rows 
				8, // columns
				cardSize, //preferred card size
				35, // Horizontal gap between cards 
				16 // Vertical gap between cards
				);
				
		this.cards = grid.cards; // Establishing card references for later event listeners
		
		// Center grid panel
		add(grid.grid, BorderLayout.CENTER);
		
		pack(); // Set to preferred sizes
		setSize(1280,900); // 1280 x 900 resolution of pixel size
		setLocationRelativeTo(null); // Center on screen
		setVisible(true);
	}
		// In the case of needing to access cards from elsewhere
		public List<JButton> getCards(){
			return cards;
		}

}

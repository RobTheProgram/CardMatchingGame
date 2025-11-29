package ui;

import utils.HandCursorUtility;
import utils.ButtonCreation;
import utils.BoxLayoutSetup;
import utils.AudiowideFont;
import utils.ApplyRoundedBorder;
import utils.ApplyRadialGradientBackground;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;


import java.awt.*;
import java.awt.geom.Point2D;

public class MainMenuFrame extends JFrame {
	
// =============== Main Menu Frame Setup Section ===============
	public MainMenuFrame() {
		// Title and setup execution
		super("Card Matching Game");
		// Called once to apply the Audiowide font
		AudiowideFont.register();
		// Gives the X close button on window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 1280 x 900 resolution of pixel size
		setSize(1280,900);
		// The window, when executed, won't generate according to any position on screen
		setLocationRelativeTo(null);
		// Replace the default single-colored content pane with a gradient panel
		setContentPane(ApplyRadialGradientBackground.createRadialGradientPanel(
				Color.decode("#ffffcc"), // center color shade
				Color.decode("#ffbf80")  // edge color shade
				));
		// To set layout to this new gradient panel
		getContentPane().setLayout(new BorderLayout());
		
		//===== Main Page Title Label Section =====
		JLabel titleLabel = new JLabel("Card Matching Game", SwingConstants.CENTER);
		titleLabel.setFont(AudiowideFont.get(40f, Font.BOLD));
		titleLabel.setOpaque(false); // Make border background transparent
		
		// Border Creation for Main Page Title Label
		titleLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(12, 7, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		titleLabel.setFocusable(false); // Prevents any focus upon clicking on the label

		//===== Main Menu Label Section =====
		JLabel mainMenuLabel = new JLabel("Main Menu", SwingConstants.CENTER);
		mainMenuLabel.setFont(AudiowideFont.get(28f, Font.BOLD));
		mainMenuLabel.setOpaque(false); // Make border background transparent
		
		// Border Creation for Main Menu Title Label
		mainMenuLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(12, 7, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		mainMenuLabel.setFocusable(false); // Prevents any focus upon clicking on the label
				
		//===== Game Mode Buttons Section =====
		JButton standardModeBtn = ButtonCreation.createGameModeButton("Standard Mode", Color.GREEN);
		JButton timedModeBtn = ButtonCreation.createGameModeButton("Timed Mode", Color.MAGENTA);
		
		// To potentially be added later
		//JButton mathModeBtn = ButtonCreation.createGameModeButton("Math Mode", Color.BLUE);
		//JButton customModeBtn = ButtonCreation.createGameModeButton("Custom Mode", Color.ORANGE);
		//JButton challengeModeBtn = ButtonCreation.createGameModeButton("Challenge Mode", Color.RED);
		
		// ===== Button Clicking Linking Event Listener =====
		
		// To link the main menu to the standard game mode screen
		standardModeBtn.addActionListener(e -> {
			new StandardModeMenuFrame().setVisible(true);
			dispose(); // To close this window after clicking
		});
		
		// To link the main menu to the timed game mode screen
		timedModeBtn.addActionListener(e -> {
			new TimedModeMenuFrame().setVisible(true);
			dispose(); // To close this window after clicking
		});
		
		
		// ===== Header Layout Panel Section ====
		JPanel headerPanel = BoxLayoutSetup.createVerticalPanel(
				Box.createVerticalStrut(1), // To add space from the top of the window to the header panel
				titleLabel,
				titleLabel,
				mainMenuLabel
				);
		
		// ===== Game Mode Button Layout Panel Section ====
		JPanel gameModeButtonPanel = BoxLayoutSetup.createVerticalPanel(
				Box.createVerticalStrut(7),	// To add space between the header and button panels			
				standardModeBtn,
				Box.createVerticalStrut(7),	// To add space between the buttons			
				timedModeBtn
				);
		
		// Add header panel to the window
		add(headerPanel, BorderLayout.NORTH);
		
		// Add game mode button panel to the window
		add(gameModeButtonPanel, BorderLayout.CENTER);
		
	}


}	


	


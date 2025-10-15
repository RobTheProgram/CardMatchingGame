package ui;

import utils.HandCursorUtility;
import utils.ButtonCreation;
import utils.BoxLayoutSetup;
import utils.AudiowideFont;
import utils.ApplyRoundedBorder;
import utils.ApplyVerticalGradientBackground;
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

public class StandardModeMenuFrame extends JFrame{
	// =============== Standard Mode Menu Frame Setup Section ===============
	public StandardModeMenuFrame() {
		// Title and setup execution
		super("Standard Mode Menu");
		// Called once to apply the Audiowide font
		AudiowideFont.register();
		// Gives the X close button on window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 900 x 700 resolution of pixel size
		setSize(900,700);
		// The window, when executed, won't generate according to any position on screen
		setLocationRelativeTo(null);
		// Replace the default single-colored content pane with a gradient panel
		setContentPane((ApplyVerticalGradientBackground.createVerticalGradientPanel(
				Color.decode("#04ca05"), // Green color
				Color.decode("#ffffff")  // White color
				)));
		// To set layout to this new gradient panel
		getContentPane().setLayout(new BorderLayout());
		
		//===== Standard Mode Title Label Section =====
		JLabel titleLabel = new JLabel("Standard Mode", SwingConstants.CENTER);
		titleLabel.setFont(AudiowideFont.get(40f, Font.PLAIN));
		titleLabel.setOpaque(false); // Make border background transparent

		// Border Creation for Standard Mode Menu Title Label
		titleLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(8, 7, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 70, 10, 70)
				));
		titleLabel.setFocusable(false); // Prevents any focus upon clicking on the label
				
		//===== Description Label Section =====
		JLabel descriptionLabel = new JLabel("<html>Description: This mode consists of<br>flipping and matching corresponding<br>cards together until completion with no<br>time or variations in grid dimensions.</html>",
				SwingConstants.CENTER);
		descriptionLabel.setFont(AudiowideFont.get(19f, Font.PLAIN));
		descriptionLabel.setOpaque(false); // Make border background transparent
		descriptionLabel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
		
		// Border Creation for Standard Mode Menu Title Label
		descriptionLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(8, 3, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		descriptionLabel.setFocusable(false); // Prevents any focus upon clicking on the label
		
		//===== Difficulty Button Section =====
		JButton easyBtn = ButtonCreation.createDiffButton("Easy", Color.decode("#6efb87"));			// Light Green
		JButton normalBtn = ButtonCreation.createDiffButton("Normal", Color.decode("#7affff")); 	// Turquoise
		JButton hardBtn = ButtonCreation.createDiffButton("Hard", Color.decode("#fa5c12"));			// Dark Orange
		JButton advancedBtn = ButtonCreation.createDiffButton("Advanced", Color.decode("#fa0ec0")); // Magenta

		//===== Header Panel Section =====
		JPanel headerPanel = BoxLayoutSetup.createVerticalPanel(
				Box.createVerticalStrut(1), // To add space from the top of the window to the header panel
				titleLabel,
				descriptionLabel
				);

		//===== Difficulty Button Panel Section =====
		JPanel difficultyButtonPanel = BoxLayoutSetup.createVerticalPanel(
				Box.createVerticalStrut(7), // To add space between the header and button panels
				easyBtn,
				Box.createVerticalStrut(1), // To add bits of space in-between the buttons
				normalBtn,
				Box.createVerticalStrut(1),
				hardBtn,
				Box.createVerticalStrut(1),
				advancedBtn
				);

		// Add panels to the window
		add(headerPanel, BorderLayout.NORTH);
		add(difficultyButtonPanel, BorderLayout.CENTER);

		

	}

}

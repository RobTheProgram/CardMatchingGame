package ui;

import utils.HandCursorUtility;
import utils.ButtonCreation;
import audio.AudioSettings;
import utils.BoxLayoutSetup;
import utils.AudiowideFont;
import utils.ApplyRoundedBorder;
import utils.DifficultyConfigurationsForStandardMode;
import utils.ApplyVerticalGradientBackground;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Menu screen for Timed Mode.
 * Lets the user choose a timed difficulty and initializes the timed game configuration.
 * (Requirements 1.0.0, 1.2.0)
 */

public class TimedModeMenuFrame extends JFrame {
	// =============== Standard Mode Menu Frame Setup Section ===============
		public TimedModeMenuFrame() {
			// Title and setup execution
			super("Timed Mode Menu");
			// Called once to apply the Audiowide font
			AudiowideFont.register();
			// Gives the X close button on window
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			// 1280 x 900 resolution of pixel size
			setSize(1280,900);
			// The window, when executed, won't generate according to any position on screen
			setLocationRelativeTo(null);
			// Replace the default single-colored content pane with a gradient panel
			setContentPane((ApplyVerticalGradientBackground.createVerticalGradientPanel(
					Color.decode("#ff38df"), // Fuchsia color
					Color.decode("#33efff")  // Light Green color
					)));
			// To set layout to this new gradient panel
			getContentPane().setLayout(new BorderLayout());
			
			//===== Standard Mode Title Label Section =====
			JLabel titleLabel = new JLabel("Timed Mode", SwingConstants.CENTER);
			titleLabel.setFont(AudiowideFont.get(40f, Font.PLAIN));
			titleLabel.setOpaque(false); // Make border background transparent

			// Border Creation for Standard Mode Menu Title Label
			titleLabel.setBorder(BorderFactory.createCompoundBorder(
					// Outer rounded border settings
					new ApplyRoundedBorder(12, 7, Color.BLACK),
					// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
					BorderFactory.createEmptyBorder(10, 70, 10, 70)
					));
			titleLabel.setFocusable(false); // Prevents any focus upon clicking on the label
			
			//===== Description Label Section =====
			JLabel descriptionLabel = new JLabel("<html>Description: This mode is like standard mode, but it consists of timers that test how fast the player can complete the rounds.</html>",
					SwingConstants.CENTER);
			descriptionLabel.setFont(AudiowideFont.get(19f, Font.PLAIN));
			descriptionLabel.setOpaque(false); // Make border background transparent
			descriptionLabel.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));
			
			// Border Creation for Description Label
			descriptionLabel.setBorder(BorderFactory.createCompoundBorder(
					// Outer rounded border settings
					new ApplyRoundedBorder(8, 3, Color.BLACK),
					// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
					BorderFactory.createEmptyBorder(10, 25, 10, 25)
					));
			descriptionLabel.setFocusable(false); // Prevents any focus upon clicking on the label
			
			//===== Header Panel Section =====
			JPanel headerPanel = BoxLayoutSetup.createVerticalPanel(
					Box.createVerticalStrut(1), // To add space from the top of the window to the header panel
					titleLabel,
					descriptionLabel
					);
			
			//===== Difficulty Button Section =====
			JButton easyBtn = ButtonCreation.createDiffButton("Easy", Color.decode("#6efb87"));			// Light Green
			JButton normalBtn = ButtonCreation.createDiffButton("Normal", Color.decode("#7affff")); 	// Turquoise
			JButton hardBtn = ButtonCreation.createDiffButton("Hard", Color.decode("#fa5c12"));			// Dark Orange
			JButton advancedBtn = ButtonCreation.createDiffButton("Advanced", Color.decode("#fa0ec0")); // Magenta

			//===== Button Link Section =====
			// Link to the Easy Mode Game Screen
			easyBtn.addActionListener(e -> {
				new TimedModeGameScreen(DifficultyConfigurationsForStandardMode.TIMED_EASY).setVisible(true);
				dispose(); // To close this window after clicking
			});
					
			// Link to the Normal Mode Game Screen
			normalBtn.addActionListener(e -> {
				new TimedModeGameScreen(DifficultyConfigurationsForStandardMode.TIMED_NORMAL).setVisible(true);
				dispose(); // To close this window after clicking
			});
			
			// Link to the Hard Mode Game Screen
			hardBtn.addActionListener(e -> {
				new TimedModeGameScreen(DifficultyConfigurationsForStandardMode.TIMED_HARD).setVisible(true);
				dispose(); // To close this window after clicking
			});
			
			// Link to the Advanced Mode Game Screen
			advancedBtn.addActionListener(e -> {
				new TimedModeGameScreen(DifficultyConfigurationsForStandardMode.TIMED_ADVANCED).setVisible(true);
				dispose(); // To close this window after clicking
			});
			
			//===== Return to Main Menu Button =====
			JButton returnBtn = ButtonCreation.createReturnToMainMenuButton("Return to Main Menu", Color.BLACK);
			returnBtn.addActionListener(e -> {
				new MainMenuFrame().setVisible(true);
				dispose(); // To close this window after clicking
			});

			//===== Difficulty Button Panel + Return To Main Menu Section =====
			JPanel difficultyButtonPanel = BoxLayoutSetup.createVerticalPanel(
					Box.createVerticalStrut(7), // To add space between the header and button panels
					easyBtn,
					Box.createVerticalStrut(7), // To add bits of space in-between the buttons
					normalBtn,
					Box.createVerticalStrut(7),
					hardBtn,
					Box.createVerticalStrut(7),
					advancedBtn,
					Box.createVerticalStrut(7),
					returnBtn
					);
			
			// Add panels to the window
			add(headerPanel, BorderLayout.NORTH);
			add(difficultyButtonPanel, BorderLayout.CENTER);
		}
}

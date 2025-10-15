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
		// 900 x 600 resolution of pixel size
		setSize(900,600);
		// The window, when executed, won't generate according to any position on screen
		setLocationRelativeTo(null);
		// Replace the default single-colored content pane with a gradient panel
		setContentPane((ApplyVerticalGradientBackground.createVerticalGradientPanel(
				Color.decode("#04ca05"), // Green color
				Color.decode("#ffffff")  // White color
				)));
		// To set layout to this new gradient panel
		getContentPane().setLayout(new BorderLayout());
	}

}

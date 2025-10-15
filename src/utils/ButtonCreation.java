package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import utils.ApplyRoundedBorder;

public class ButtonCreation {
	// To create the game mode buttons on the main menu
	public static JButton createGameModeButton(String text, Color borderColor){
		// The outward text or titles of the buttons
		JButton gmb = new JButton(text);
		
		// Applying the custom Audiowide font for these buttons
		gmb.setFont(AudiowideFont.get(30f, Font.PLAIN));
		
		// Sets the text color of the button text to black
		gmb.setForeground(Color.BLACK);
		
		// Size (Both the preferred and maximum sizes keep them all aligned in the Box Layout
		Dimension size = new Dimension(320, 60);
		gmb.setPreferredSize(size);
		gmb.setMaximumSize(size);
		
		// Create rounded borders with inward and outward padding
		gmb.setBorder(BorderFactory.createCompoundBorder(
				new ApplyRoundedBorder(1, 4, borderColor),
				BorderFactory.createEmptyBorder(20, 25, 20, 25)
				));
		
		// Set background to transparent
		gmb.setContentAreaFilled(false);
		// Remove the focus ring around the border
		gmb.setFocusPainted(false);
		// Show hand cursor when hovered over with the mouse
		gmb.setCursor(HandCursorUtility.getHandCursor());
		
		return gmb;
		
	}
	
	// To create the difficulty buttons that exist within the Mode menus themselves
	public static JButton createDiffButton(String text, Color borderColor){
		// The outward text or titles of the buttons
		JButton db = new JButton(text);
		
		// Applying the custom Audiowide font for these buttons
		db.setFont(AudiowideFont.get(30f, Font.PLAIN));
		
		// Sets the text color of the button text to black
		db.setForeground(Color.BLACK);
		
		// Size (Both the preferred and maximum sizes keep them all aligned in the Box Layout
		Dimension size = new Dimension(320, 60);
		db.setPreferredSize(size);
		db.setMaximumSize(size);
		
		// Create rounded borders with inward and outward padding
		db.setBorder(BorderFactory.createCompoundBorder(
				new ApplyRoundedBorder(1, 4, borderColor),
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		
		// Set background to transparent
		db.setContentAreaFilled(false);
		// Remove the focus ring around the border
		db.setFocusPainted(false);
		// Show hand cursor when hovered over with the mouse
		db.setCursor(HandCursorUtility.getHandCursor());
		
		return db;
		
	}
}



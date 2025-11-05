package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.border.*;

import ui.StandardModeMenuFrame;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import utils.ApplyRoundedBorder;
import utils.ApplyRadialGradientBackground;

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
	
	// To create card buttons for a given game board
	public static JButton createCardButton() {
		JButton cb = ApplyRadialGradientBackground.createRadialGradientCardBack(
				Color.decode("#efd176"), // Center shade
				Color.decode("#fff4b2") // Edge shade
				);
		cb.setPreferredSize(new Dimension(100, 250));
		cb.setContentAreaFilled(false); // To not paint the default background for each card
		cb.setFocusPainted(false); // To prevent focus ring from appearing when clicking on it
		cb.setCursor(HandCursorUtility.getHandCursor());
		cb.setBorder(new ApplyRoundedBorder(8, 4, Color.BLACK));
		return cb;
		
	}

	// To create buttons for returning to the main menu from game mode screens
	public static JButton createReturnToMainMenuButton(String text, Color borderColor) {
		// The outward text or titles of the buttons
		JButton rb = new JButton(text);
		
		// Applying the custom Audiowide font for these buttons
		rb.setFont(AudiowideFont.get(30f, Font.PLAIN));
		
		// Sets the text color of the button text to black
		rb.setForeground(Color.RED);
		
		// Sets the background color of the button
		//rb.setBackground(Color.WHITE);
		
		// Size (Both the preferred and maximum sizes keep them all aligned in the Box Layout
		Dimension size = new Dimension(450, 60);
		rb.setPreferredSize(size);
		rb.setMaximumSize(size);
		
		// Create rounded borders with inward and outward padding
		rb.setBorder(BorderFactory.createCompoundBorder(
				new ApplyRoundedBorder(1, 4, borderColor),
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		
		// Set background to transparent
		rb.setContentAreaFilled(false);
		// Remove the focus ring around the border
		rb.setFocusPainted(false);
		// Show hand cursor when hovered over with the mouse
		rb.setCursor(HandCursorUtility.getHandCursor());
		
		return rb;
	}
	
	public static JButton createBackOutOfCurrentGameButton() {
		// Try to load the arrow from classpath
        java.net.URL imgUrl = ButtonCreation.class.getResource("/assets/BackButtonImage/backArrow.png");

        JButton backBtn;
        if (imgUrl != null) {
            ImageIcon backIcon = new ImageIcon(imgUrl);
            Image scaled = backIcon.getImage().getScaledInstance(46, 46, Image.SCALE_SMOOTH);
            backBtn = new JButton(new ImageIcon(scaled));
        } else {
            // fallback so it’s still visible
            backBtn = new JButton("←");
        }

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setOpaque(false);
        backBtn.setCursor(HandCursorUtility.getHandCursor());

        return backBtn;
	}
}



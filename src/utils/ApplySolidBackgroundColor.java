package utils;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

// Method to apply a simple background color to whatever
public class ApplySolidBackgroundColor {
	public static JPanel createSolidBackgroundPanel(Color bgColor) {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(bgColor); // Apply shade
				g.fillRect(0, 0, getWidth(), getHeight()); // Fill background
			}
		};
	}
}

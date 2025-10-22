package utils;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ApplySolidBackgroundColor {
	public static JPanel createSolidBackgroundPanel(Color bgColor) {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(bgColor);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}
}

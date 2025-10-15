package utils;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class ApplyVerticalGradientBackground {
	// =============== Gradient Panel Section ===============
	public static JPanel createVerticalGradientPanel(Color topShade, Color bottomShade) {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Cast to Graphics2D to access advanced customization options.
				Graphics2D g2D = (Graphics2D) g;
				
				// Top to bottom gradient implementation
				int width = getWidth();
				int height = getHeight();
				
				// To create the vertical gradient from top (y=0) to bottom (y=height)
				GradientPaint gp = new GradientPaint(
						0,			// starting x-coordinate (left edge of panel)
						0,			// starting y-coordinate (top of panel)
						topShade,	// starting color (top of panel color)
						0,			// ending x-coordinate (same vertical line as start)
						height,		// ending y-coordinate (bottom of panel)
						bottomShade // ending color (bottom of panel color)
				);
				
				g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); // Anti-alias for smoother gradient
				g2D.setPaint(gp); // Make the Gradient paint the background fill in
				g2D.fillRect(0, 0, width, height); // Fill in the 2D graphical panel
					
				}
			};
		}
	}

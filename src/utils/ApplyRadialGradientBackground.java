package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class ApplyRadialGradientBackground {
	// =============== Gradient Panel Section ===============
	public static JPanel createRadialGradientPanel(Color centerGradient, Color edgeGradient) {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Cast to Graphics2D to access advanced customization options.
				Graphics2D g2D = (Graphics2D) g;
				
				// Top to bottom gradient implementation
				int width = getWidth();
				int height = getHeight();
				
				// The Radial center of the panel
				Point2D radialCenter = new Point2D.Float(width / 2f, height / 2f);
				float radius = Math.max(width, height) / 2f;
								
				// Fractional distance for the colors
				float[] distance = {0.0f, 1.0f}; // 0.0 = center, 1.0 = edges
				
				// To establish the color gradients for the background
				Color[] shades = {centerGradient, edgeGradient};
				
				RadialGradientPaint rgp = new RadialGradientPaint(
						radialCenter,
						radius,
						distance,
						shades
				);
				
				g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); // Anti-alias for smoother gradient
				g2D.setPaint(rgp); // Make the Gradient paint the background fill in
				g2D.fillRect(0, 0, width, height); // Fill in the 2D graphical panel
				
			}
		};
	}

}

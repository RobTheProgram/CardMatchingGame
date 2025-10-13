package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

// Class to apply rounded borders to elements
public class ApplyRoundedBorder implements Border {
	private final int curvature; // corner radius by pixels (px)
	private final int thickness; // stroke width for border
	private final Color color; // border color
	
	// Self declarations
	public ApplyRoundedBorder(int curvature, int thickness, Color color) {
		this.curvature = curvature;
		this.thickness = Math.max(1, thickness); // 1 is the default thickness setting to avoid invisibility
		this.color = color;
	}
	
	// To determine the amount of space between the border and the inner contents
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(thickness, thickness, thickness, thickness);
	}
	
	// To determine if the border is see-through or not
	@Override
	public boolean isBorderOpaque() {
		// The inner contents within the border have a transparent background even outside the border itself
		return false; 
	}

	/** To paint the graphical border to the window
	 * @param c: the Swing Component the border is part of
	 * @param g: the Graphics object used for painting the border
	 * @param x: x-coordinate of border origin point
	 * @param y: y-coordinate of border origin point
	 * @param width: total border width
	 * @param height: total border height
	 */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// Create a Graphics2D object copy for secure border customization
		Graphics2D g2D = (Graphics2D) g.create();
		try {
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // For a smoother render
			g2D.setColor(color);
			g2D.setStroke(new BasicStroke(thickness)); // To draw border according to the provided thickness
			
			// To keep the full strokes within the bounds of the border components via reducing the size
			int offset = thickness / 2;
			int offsetWidth = width - thickness;
			int offsetHeight = height - thickness;
			
			g2D.drawRoundRect(
					x + offset,		// Left and right padding
					y + offset, 	// Top and bottom padding
					offsetWidth, 	// Adjusted width
					offsetHeight, 	// Adjusted height
					curvature, 		// Horizontal corner radius
					curvature		// Veritical corner radius
					);
		}
		finally {
			// Discard leftover resources to avoid memory leaks
			g2D.dispose();
		}
	}
	
}

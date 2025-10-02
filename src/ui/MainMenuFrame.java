package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MainMenuFrame extends JFrame {
	
// =============== Main Menu Frame Setup Section ===============
	public MainMenuFrame() {
		// Title and setup execution
		super("Card Matching Game");
		// Gives the X close button on window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 900 x 600 resolution of pixel size
		setSize(900,600);
		// The window, when executed, won't generate according to any position on screen
		setLocationRelativeTo(null);
		// Replace the default single-colored content pane with a gradient panel
		setContentPane(new RadialGradientPanel());
		// To set layout to this new gradient panel
		getContentPane().setLayout(new BorderLayout());
	}
	
	// =============== Gradient Panel Section ===============
	private static class RadialGradientPanel extends JPanel {
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
			
			// Make the new color objects with the hex codes in mind
			Color centerColor = Color.decode("#ffffcc"); // Lighter yellow shade
			Color edgeColor = Color.decode("#ffbf80"); // Darker orange shade
			
			// Fractional distance for the colors
			float[] distance = {0.0f, 1.0f}; // 0.0 = center, 1.0 = edges
			Color[] shades = {centerColor, edgeColor};
			
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
	}
}

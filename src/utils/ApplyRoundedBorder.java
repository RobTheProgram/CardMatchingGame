package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

public class ApplyRoundedBorder implements Border {
	private final int curvature; // corner radius by pixels (px)
	private final int thickness; // stroke width for border
	private final Color color; // border color
	
	// Self declarations
	public ApplyRoundedBorder(int curvature, int thickness, Color color) {
		this.curvature = curvature;
		this.thickness = Math.max(1, thickness); // 1 is the default thickness setting
		this.color = color;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(thickness, thickness, thickness, thickness);
	}
	
	@Override
	public boolean isBorderOpaque() {
		return false;
	}


	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2D = (Graphics2D) g.create();
		try {
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2D.setColor(color);
			g2D.setStroke(new BasicStroke(thickness));
			
			// To keep the full strokes within the bounds of the border components via reducing the size
			int offset = thickness / 2;
			int offsetWidth = width - thickness;
			int offsetHeight = height - thickness;
			
			g2D.drawRoundRect(x + offset, y + offset, offsetWidth, offsetHeight, curvature, curvature);
		}
		finally {
			g2D.dispose();
		}
	}
	
}

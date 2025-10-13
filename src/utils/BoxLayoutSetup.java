package utils;

import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

// To create a vertical layout panel to stack multiple J elements
public class BoxLayoutSetup {
	public static JPanel createVerticalPanel(JComponent... components) {
		JPanel panel = new JPanel();
		// Create a box layout object meant for vertical placing
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Transparent background
		panel.setOpaque(false);
		// Center box layout
		panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		// To loop in multiple components into the layout
		for (JComponent c : components) {
			// Center each component
			c.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			panel.add(c);
			// Gaps for in between each component
			panel.add(Box.createVerticalStrut(15));
		}
		
		return panel;
	}

}

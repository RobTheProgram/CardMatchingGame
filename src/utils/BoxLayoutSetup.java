package utils;

import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Component;

// To create a vertical layout panel to stack multiple J elements
public class BoxLayoutSetup {
	// Method for JComponents
	public static JPanel createVerticalPanel(JComponent... components) {
		JPanel panel = new JPanel();
		// Create a box layout object meant for vertical placing
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Transparent background
		panel.setOpaque(false);
		// Center box layout
		panel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		// To loop in multiple components into the layout
		for (JComponent jc : components) {
			// Center each component
			jc.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			panel.add(jc);
			// Gaps for in between each component
			panel.add(Box.createVerticalStrut(15));
		}
		
		return panel;
	}
	
	public static JPanel createVerticalPanel(Component... components) {
		JPanel panel = new JPanel();
		// Create a box layout object meant for vertical placing
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Transparent background
		panel.setOpaque(false);
		// Center box layout
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// To loop in multiple components into the layout
		for (Component c : components) {
			// To type cast Component class to JComponent class to prevent potential compile-time errors due to a mismatch
			if (c instanceof JComponent jc) {
				jc.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			}
			// Center each component
			panel.add(c);
			// Gaps for in between each component
			panel.add(Box.createVerticalStrut(15));
		}
		
		return panel;
	}

}

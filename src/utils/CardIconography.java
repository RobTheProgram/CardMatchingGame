package utils;

import javax.swing.*;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// Deals with the loading and processing of card icons
public class CardIconography {
	 // Cache for icons so we only load once
    private static final List<Icon> FACE_ICONS = new ArrayList<>();
    private static final int totalIconNumber = 144; // Represents the total number of available icons

    // Call this once at startup
    public static void loadFaceIcons() {
        if (!FACE_ICONS.isEmpty()) return; // already loaded
        
        // Loop through the png icon files
        for (int i = 1; i <= totalIconNumber; i++) {
            String iconPath = "/assets/faceUpIcons/" + i + ".png"; // Stringed path for each icon image
            URL url = CardIconography.class.getResource(iconPath); // URL to extract the image content
            
            // If icon is found, then it is added into the icon list
            if (url != null) {
            	FACE_ICONS.add(new ImageIcon(url));
            }
            // To alert that an icon is missing
            else {
            	FACE_ICONS.add(new ImageIcon());
            	System.err.println("This icon is not available: " + iconPath);
            }
        }
    }
    
    // get icon by 1-based id (because your Card ids start at 1)
    public static Icon get(int id) {
        // Checks to make sure the icons are loaded first before proceeding
        if (FACE_ICONS.isEmpty()) {
        	loadFaceIcons();
        }
        // To get the corresponding index of the cards (ex: FACE_ICONS.get(4) = FACE_ICONS[3]);
        return FACE_ICONS.get(id - 1);
    }
    
    // Method to scale the cards accordingly
    public static Icon getScaledIcon(int id, int width, int height) {
        // Get the unscaled icon from cache
    	Icon unscaledIcon = get(id);
    	
    	// If the unscaled icon is of a valid image, then it can be scaled accordingly
    	if(unscaledIcon instanceof ImageIcon imgIcon) {
    		Image scaledIcon = imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    		return new ImageIcon(scaledIcon); // Cast back to an Icon object for returning
    	}
    	// Fallback in case the scaling couldn't be done
    	return unscaledIcon;
    }
}

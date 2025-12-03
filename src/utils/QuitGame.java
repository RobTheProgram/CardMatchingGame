package utils;

import java.awt.Font;
import javax.swing.*;


// Class for dealing with the logic behind a user wanting to quit a current game
public final class QuitGame {

    private QuitGame() {} // utility class

    /**
     * Shows a "Are you sure you want to quit?" dialog.
     *
     * @param parent      the window to center the dialog on (can be null)
     * @param onConfirm   what to do if the user clicks "Yes, I'm sure"
     */	
    public static void confirmUserQuitting(JFrame parent, Runnable onConfirm) {
    	// Audiowide font to be applied
    	Font quitComponentsFont = AudiowideFont.get(18f, Font.PLAIN);
    	
        UIManager.put("OptionPane.messageFont", quitComponentsFont);
        UIManager.put("OptionPane.buttonFont", quitComponentsFont);
        UIManager.put("OptionPane.titleFont", quitComponentsFont);
        
        String[] options = { "Yes, I'm sure", "Return to game" };

        int choice = JOptionPane.showOptionDialog(
                parent,
                "Are you sure you want to quit the game?", // Message body
                "Warning",						// Dialog title
                JOptionPane.YES_NO_OPTION,		// two buttons of either yes or no
                JOptionPane.WARNING_MESSAGE,	// warning icon
                null,		// No custom icon
                options,	// Buttons: Quit or return
                options[1]   // default = "Return to game"
        );

        if (choice == 0) {         // user clicked "Yes, I'm sure"
        	onConfirm.run();
            }
        // else: do nothing, just return to game
        
        // Restores default Swing fonts (Prevents global override)
        UIManager.put("OptionPane.messageFont", null);
        UIManager.put("OptionPane.buttonFont", null);
        UIManager.put("OptionPane.titleFont", null);
     }
    
}


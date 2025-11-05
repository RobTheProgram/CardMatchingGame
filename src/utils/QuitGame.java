package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        String[] options = { "Yes, I'm sure", "Return to game" };

        int choice = JOptionPane.showOptionDialog(
                parent,
                "Are you sure you want to quit the game?",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[1]   // default = "Return to game"
        );

        if (choice == 0) {         // user clicked "Yes, I'm sure"
        	onConfirm.run();
            }
     }
        // else: do nothing, just return to game
}


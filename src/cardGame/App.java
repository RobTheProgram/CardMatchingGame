package cardGame;

import ui.MainMenuFrame; // Imports the Main Menu user interface
import javax.swing.SwingUtilities;

/**
 * Entry point for the Card Matching Game.
 * Launches the main menu so the user can select a game mode and difficulty.
 * (Requirement 1.0.0)
 */

public class App {

	public static void main(String[] args) {
		// Used for thread safety
		SwingUtilities.invokeLater(() -> {
			new MainMenuFrame().setVisible(true); // Generate the Main Menu by default upon running the program
		});
	}
}

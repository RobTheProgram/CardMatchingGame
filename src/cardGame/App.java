package cardGame;

import ui.MainMenuFrame; // Imports the Main Menu user interface
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		// Used for thread safety
		SwingUtilities.invokeLater(() -> {
			new MainMenuFrame().setVisible(true);
		});
	}
}

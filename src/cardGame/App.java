package cardGame;

import ui.MainMenuFrame; // Imports the Main Menu user interface
import javax.swing.SwingUtilities;
import ui.StandardModeMenuFrame;
import ui.GameScreenForStandardModeEasy;

public class App {

	public static void main(String[] args) {
		// Used for thread safety
		SwingUtilities.invokeLater(() -> {
			//new MainMenuFrame().setVisible(true);
			//new StandardModeMenuFrame().setVisible(true);
			new GameScreenForStandardModeEasy().setVisible(true);
		});
	}
}

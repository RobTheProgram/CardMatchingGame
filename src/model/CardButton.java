package model;
import javax.swing.JButton;

// Connects a JButton (visual card) with its Card model (logic/data)
public final class CardButton {
	// The visual card button displayed on the board
	public final JButton button;
	// The logic of the card containing its ID, icon, and state (flip/match) data
	public final Card cardRef;
	
	// Links the UI of the card button and its corresponding Card object
	public CardButton(JButton button, Card cardRef) {
		this.button = button;
		this.cardRef = cardRef;
	}
}

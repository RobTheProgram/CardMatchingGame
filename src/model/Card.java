package model;
import javax.swing.Icon;

// Card model for each card of each mode
public final class Card {
	public final int id; // unique id for each card
	public final Icon face; // The front, or design print, of each card
	public boolean isFacingUp = false; // Boolean value for if the card is facing up or not
	public boolean isMatched = false; // Boolean value for if a card has been matched with its corresponding pair or not
	
	// Self declaration in tying the variables
	public Card(int id, Icon face) {
		this.id = id;
		this.face = face;
	}
	
	// Method to flip a card upwards (Change to an upward state)
	public void flipUp() {
		isFacingUp = true;
	}
	
	// Method to flip a card downwards (Change to a face-down state)
	public void flipDown() {
		isFacingUp = false;
	}
	
}

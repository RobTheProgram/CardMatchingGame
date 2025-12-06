package model;
import javax.swing.Icon;

// Card model for each card of each mode
/**
 * Logical model for an individual card in the game.
 * Tracks the card's ID, face icon, whether it is currently face up, and whether it is matched.
 * (Requirements 2.1.0, 2.2.0, 3.0.0)
 */
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

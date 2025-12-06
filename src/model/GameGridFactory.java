package model;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Responsible for building the logical/visual card set for a board.
 * It does not add cards to a JPanel (that’s the job of the UI/GameScreen class).
 * It just creates the data and ready-to-use buttons.
 */

/**
 * Factory for building the logical/visual set of cards for a board.
 * Creates card pairs, assigns icons, and shuffles them before play begins.
 * (Requirements 1.2.0, 3.0.0)
 */
public class GameGridFactory {
	// Private constructor to make this a utility/factory class only
	private GameGridFactory() {}
	
	/**
	 * Build a shuffled list of CardButton objects for a given board.
	 * @param pairCount: determines how many unique pairs we want based on the board.
	 * Example: Easy board 4x3 board = 12 cards, so 6 pairs would be needed (12/6)
	 * @param backIconSupplier: provides the Icon to use for face-down cards
	 * @param faceIconForId: returns the front Icon (front side) of a given card via its ID
	 * @param buttonStyler: allows the UI to further style buttons (font, border, color, cursor, etc)
	 * @return a shuffled List<CardButton> with matching card pairs with the initial back icons in JButton
	 */

	/**
 * Builds a shuffled list of CardButton objects for a given board configuration.
 * (Requirements 1.2.0, 3.0.0)
 */
	
	public static List<CardButton> buildCardButtonsForGrid( 
			int pairCount,
			java.util.function.Supplier<Icon> backIconSupplier,
			Function<Integer, Icon> faceIconForId,
			Function<JButton, JButton> buttonStyler
	) {
		// Card deck creation (2 for each id)
		List<Card> deck = new ArrayList<>();
		for (int id = 1; id <= pairCount; id++) {
			Icon faceIcon = faceIconForId.apply(id);
			deck.add(new Card(id, faceIcon));
			deck.add(new Card(id, faceIcon));
		}
		
		// Shuffle cards within the deck for each game
		Collections.shuffle(deck);
		
		// Create a JButton wrapped in the CardButton class for each Card
		List<CardButton> finalizedShuffle = new ArrayList<>();
		for(Card card : deck) {
			// Create a JButton face-down
			JButton btn = new JButton();
			btn.setIcon(backIconSupplier.get());
			
			// Remove the focus border whenever the cards are clicked on
			btn.setFocusPainted(false);
			
			// Button stylization
			btn = buttonStyler.apply(btn);
			
			// Wrap model and viewing of CardButton
			CardButton cb = new CardButton(btn, card);
			
			// Add each card button to the finalized deck
			finalizedShuffle.add(cb);
		}
		
		return finalizedShuffle;
	}
}

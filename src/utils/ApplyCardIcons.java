package utils;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Utility for assigning randomized card IDs and (optionally) scaled icons to card buttons.
 * (Requirement 3.0.0)
 */

public class ApplyCardIcons {
	// Private constructor to prevent instantiation and make it a utility only
    private ApplyCardIcons() {}	
	
    /**
     * Applies scaled random pair icons to the given list of card buttons.
     * 
     * @param cards the list of JButton cards
     * @param cardSize the preferred size for scaling icons
     * @param totalIconCount total number of available icons
     */
	/**
 * Applies random paired card IDs to the given list of JButton cards.
 * (Requirement 3.0.0)
 */
	public static void applyRandomPairs(List<JButton> cards, Dimension cardSize, int totalIconNumber) {
		int totalCardAmount = cards.size(); // Gets the total number of cards on a given board
		int pairCount = totalCardAmount / 2; // ex: 16 cards total equals 8 total pairs
		
		// List to represent the pool of icons available (144 in this case)
		List<Integer> pool = new ArrayList<>();
		// Loop through the pool of icons to add them to the array
		for (int i = 1; i <= totalIconNumber; i++) {
		    pool.add(i);
		}
		Collections.shuffle(pool); // Shuffle the pool of icons
		
        // List of ids that will go on the board (two of each)
		List<Integer> boardIds = new ArrayList<>();
		// Apply the same id to a pair of cards
		for(int i = 1; i <= pairCount; i++) {
			// Attach the id of the icons to the board ids on the board
			int icon_id = pool.get(i);
			boardIds.add(icon_id);
			boardIds.add(icon_id);
		}
		
		// Shuffle pairs around on the board
		Collections.shuffle(boardIds);
		
		// Assign icons to cards
		for(int i = 0; i < totalCardAmount; i++) {
			JButton btn = cards.get(i);
			int id = boardIds.get(i);
			//Icon icon = CardIconography.getScaledIcon(id, cardSize.width, cardSize.height);
			
			// Store cardId for MatchingController usage
			btn.putClientProperty("cardId", id);
		}
	}
}

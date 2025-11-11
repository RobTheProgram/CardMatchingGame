package utils;

import utils.HandCursorUtility;
import utils.CardIconography;
import utils.ApplyCardIcons;
import model.Card;
import model.CardButton;
import javax.swing.Icon;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;


import java.awt.*;

/**
 * Turn the plain JButtons into CardButton + Card model objects
 * so MatchingController can work with them.
 */
public class ConvertJButtonsToCardButtons {
	// Private constructor to prevent instantiation (utility class only)
	private ConvertJButtonsToCardButtons() {}
	
	/**
	 * @param buttons: Represents the list of JButton cards from a given board
	 * @param cardSize: Represents the card dimensions for a given board
	 */
    public static List<CardButton> buildCardButtonsFromSwingButtons(List<JButton> buttons, Dimension cardSize) {
        // A list for storing the soon-to-be modified card buttons
    	List<CardButton> result = new ArrayList<>();
    	
    	// Loop through each card button from the list parameter
        for (JButton btn : buttons) {
            // Retrieve the card ID assigned earlier in ApplyCardIcons
        	Object prop = btn.getClientProperty("cardId");
            if (prop == null) {
                // If ApplyCardIcons forgot to set a valid card ID, skip
                System.err.println("This card is missing a cardId property, so it will be skipped.");
                continue;
            }
            // Cast the object retrieval to an integer of the valid ID
            int cardId = (int) prop;
            // Generate a scaled face icon for this card
            Icon faceIcon = CardIconography.getScaledIcon(cardId, cardSize.width, cardSize.height);
            
            // Create the Card model and wrap it into a CardButton
            Card cardModel = new Card(cardId, faceIcon);
            CardButton cb = new CardButton(btn, cardModel);
            
            // Add this newly formed card model into our original list
            result.add(cb);
        }

        return result;
    }

}

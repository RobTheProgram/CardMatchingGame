package model;
import javax.swing.Timer;
import java.util.List;
import java.util.function.Consumer;

/**
 * MatchingController:
 * Handles the logic and conditions of clicking, flipping, and winning.
 * The UI screen (GameScreenForStandardModeEasy, etc.) gives this controller
 * everything it needs via the constructor + attach().
 */
public final class MatchingController {
	// Once a card is clicked on, make it a CardButton, otherwise, null = no selection yet
	private CardButton firstSelected = null;
	// Variable to keep track of clicking activity and disable further interactions to prevent a player from flipping more cards at once than is intended
	private boolean boardLocked = false;
	// Variable to putting a pause on everything (more specifically when the Quit Buttton is clicked on)
	private boolean paused = false;
	// Gives the user three-fourths of a second to observe the second selected card before flipping it back down if it is not a match
	private final int FLIP_BACK_DELAY_MS;
	// Callback variable to reform the flip state of the card
	private final Consumer<CardButton> updateCardImage;
	// Callback variable to indicate when all cards have been matched
	private final Runnable victory;
	// List of every card on a given game grid
	private List<CardButton> allCards;
	
	// ===== Constructor =====
	public MatchingController(int FLIP_BACK_DELAY_MS, 
			Consumer<CardButton> updateCardImage, 
			Runnable victory) {
		this.FLIP_BACK_DELAY_MS = FLIP_BACK_DELAY_MS;
		this.updateCardImage = updateCardImage;
		this.victory = victory;
	}
	
	// Method to attach cards to the controller
	public void attach(List<CardButton> cards) {
		this.allCards = cards;
		
		for(CardButton cb : cards) {
			cb.button.addActionListener(e -> handleClick(cb));
		}
	}
	
	// Method for applying the paused state on a game
	public void pauseGame(boolean valueState) {
		this.paused = valueState;
	}
	
	// To handle the logic behind clicking events on cards
	private void handleClick(CardButton cb) {
		// To return nothing in the event of a game pause
		if(paused) return;
		
		/*
		 * First Condition: If board interaction is disabled
		 * Second Condition: If card match has already been made
		 * Third Condition: If selected card is already flipped up
		 * Then no further action may be done
		 */
		if (boardLocked || cb.cardRef.isMatched || cb.cardRef.isFacingUp) {
			return;
		}
		
		// Flip current card for image
		cb.cardRef.flipUp();
		updateCardImage.accept(cb);
		
		// First card selection
		if (firstSelected == null) {
			firstSelected = cb;
			return;
		}
		
		// Second pick - To allow the matching processes to happen without interference
		boardLocked = true;
		
		// If cards match
		if (cardsMatch(firstSelected.cardRef, cb.cardRef)) // Check both cards for a potential match
		{ 
			markMatched(firstSelected, cb); // Confirm match
	        updateCardImage.accept(firstSelected); // change first card
	        updateCardImage.accept(cb); // change second card
	        firstSelected = null; // Clear card states 
	        boardLocked = false; // Resume play
	        checkWinIfNeeded();
		}
		
		// In the event of the cards not matching
		else {
			startDelay(FLIP_BACK_DELAY_MS, () -> {
				// Flip both cards back down
				firstSelected.cardRef.flipDown(); // flip down first card
	            updateCardImage.accept(firstSelected);
	            cb.cardRef.flipDown(); // flip down second card
	            updateCardImage.accept(cb);
	            
	            // Reset the state of the cards and resume play
	            firstSelected = null;
	            boardLocked = false;
	        });
		}
	}
	
	// To create delay between actions such as clicks and card flipping
	private void startDelay(int milliSeconds, Runnable event) {
		// Create a Swing Timer that waits the given number of milliseconds,
	    // then triggers the lambda (code inside e -> {...})
		Timer timer = new Timer(milliSeconds, e -> {
			try {
				event.run(); // Run the task at hand such as flipping a card up
			}
			finally {
	            // Stop and dispose of the timer after it runs once,
	            // so it doesn't keep firing repeatedly or cause memory leaks.
				((Timer)e.getSource()).stop();
			}
		});
		// Makes it so the timer only runs once and not on repeated intervals
		timer.setRepeats(false); 
		
		// Initiate the timer countdown, or delay in this case
		timer.start();
	}
	
	// To check and compare if cards match via ID
	private boolean cardsMatch(Card card1, Card card2) {
		return card1.id == card2.id;
	}
	
	// To mark both cards as matched for the remainder of the game
	private void markMatched(CardButton firstCard, CardButton secondCard) {
		firstCard.cardRef.isMatched = true;
		secondCard.cardRef.isMatched = true;
		// To prevent further interaction with the matched cards
		firstCard.button.setEnabled(false);
		secondCard.button.setEnabled(false);		
	}
	
	// To confirm if the board has been completed with every card matched up
	private void checkWinIfNeeded() {
		for (CardButton cb : allCards) {
			if(!cb.cardRef.isMatched) return; // The game has yet to conclude
		}
		// The condition in which the all cards have been matched
		if(victory != null) victory.run();
	}

}

package utils;

import java.io.*;
import javax.swing.JLabel;

// Class to dynamically update the high score
public class HighScoreUpdater {
	private final int basePoints;		// Standard points given when a match is made
	private final int maxMultiplier; 	// To allow for the increase in multiplier value
	private int currentScore = 0;		// Default starting score
	private int multiplier = 1;			// Default starting multiplier
	
	// Connect parameter with local variable equivalents
	public HighScoreUpdater(int basePoints, int maxMultiplier) {
		this.basePoints = basePoints;
		this.maxMultiplier = maxMultiplier;
	}
	
	public void forMatch() {
		// Add points by the base points
		currentScore += basePoints * multiplier;
		// Increase multiplier by 1 with 1 being the limit
		multiplier = Math.min(maxMultiplier, multiplier + 1);
	}
	
	public void forMismatch() {
		// Subtract points by 25 times the current multiplier
		currentScore -= (50*multiplier);
		// To prevent going below 0
		if (currentScore < 0) currentScore = 0;
		// Decrease multiplier by 1, but never going below 1
		multiplier = Math.max(1, multiplier - 1);
	}
	
	// To retrieve and display the score of a given game
	public int getScore() {return currentScore;}
	// To retrieve and display the multiplier of a given game
	public int getMultiplier() {return multiplier;}
	
}

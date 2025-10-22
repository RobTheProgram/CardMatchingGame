package utils;

import java.awt.*;
import java.util.*;
import javax.swing.*;

// To produce a game grid for a given difficulty mode
public final class GenerateGameGrid {
	// Private constructor to prevent instantiation
	private GenerateGameGrid() {}
	
	// Nested container class for holding both the JPanel and array list of card buttons
	public static final class GameGrid {
		// JPanel to represent the game grid
        public final JPanel grid;
        // List of card buttons for applying event listeners and logic for matching set models
        public final java.util.List<JButton> cards;
        // Assign the values to self
        private GameGrid(JPanel grid, java.util.List<JButton> cards) {
            this.grid = grid;
            this.cards = cards;
        }		
	}
	
	/**
     * Builds a centered card grid using GridLayout.
     * @param rows: number of rows
     * @param cols: number of cols
     * @param cardSize: preferred size, or dimensions, for each card
     * @param hgap: horizontal gap between cards (px)
     * @param vgap: vertical gap between cards (px)
     */
    public static GameGrid makeGameGrid(int rows, int cols,
                                    Dimension cardSize,
                                    int hgap, int vgap) {
    	
        JPanel grid = new JPanel(new GridLayout(rows, cols, hgap, vgap));
        grid.setOpaque(false); // Make background transparent
        // Produce an array of cards to populate the game grid by the rows and columns
        java.util.List<JButton> cards = new ArrayList<>(rows * cols);
        for (int i = 0; i < rows * cols; i++) {
            JButton card = ButtonCreation.createCardButton();
            // Size management of the cards themselves
            if (cardSize != null) {
                card.setPreferredSize(cardSize);
                card.setMinimumSize(cardSize);
                card.setMaximumSize(cardSize);
            }
            grid.add(card); // Add each card to the grid
            cards.add(card); // Add each card to the array list reference
        }

        // Wrap in a FlowLayout so the grid stays centered and doesn't stretch out too far
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        wrapper.setOpaque(false);
        wrapper.add(grid);
        
        // Return a container that has both the wrapper and card array list
        return new GameGrid(wrapper, cards);
    }

}

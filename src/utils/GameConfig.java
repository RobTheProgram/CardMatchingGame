package utils;

import java.awt.*;

public class GameConfig {
	public final String title;		// Title of game mode
	public final Color bgColor;		// Background Color of game screen
	public final int rows, cols;	// Rows and columns of the grid
    public final Dimension cardSize;// Size of each respective card size
    public final int hGap, vGap;	// horizontal and vertical gaps between cards
    public final int flipDelay;		// The delay in card flips in milliseconds
    
    // Attach parameters to local variables
    public GameConfig(
            String title,
            Color bgColor,
            int rows,
            int cols,
            Dimension cardSize,
            int hGap,
            int vGap,
            int flipDelay
    ) {
        this.title = title;
        this.bgColor = bgColor;
        this.rows = rows;
        this.cols = cols;
        this.cardSize = cardSize;
        this.hGap = hGap;
        this.vGap = vGap;
        this.flipDelay = flipDelay;
    }
}

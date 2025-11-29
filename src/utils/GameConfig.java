package utils;

import java.awt.*;

// Serves as the base parameter configuration for the game modes
public class GameConfig {
	
	// Enumerated data type for the game modes
	public enum GameType {
		STANDARD,
		TIMED
	}
	
	public final String title;		// Title of game mode
	public final Color bgColor;		// Background Color of game screen
	public final int rows, cols;	// Rows and columns of the grid
    public final Dimension cardSize;// Size of each respective card size
    public final int hGap, vGap;	// horizontal and vertical gaps between cards
    public final int flipDelay;		// The delay in card flips in milliseconds
    public final GameType modeType; // Indicative of the game mode at play
	public final Integer timeLimit;		// The timer variable in seconds

    
    // Attach parameters to local variables
    public GameConfig(
            String title,
            Color bgColor,
            int rows,
            int cols,
            Dimension cardSize,
            int hGap,
            int vGap,
            int flipDelay,
            GameType modeType,
            Integer timeLimit
    ) {
        this.title = title;
        this.bgColor = bgColor;
        this.rows = rows;
        this.cols = cols;
        this.cardSize = cardSize;
        this.hGap = hGap;
        this.vGap = vGap;
        this.flipDelay = flipDelay;
        this.modeType = modeType;
        this.timeLimit = timeLimit;
    }
}

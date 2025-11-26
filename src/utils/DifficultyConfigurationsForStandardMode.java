package utils;

import java.awt.*;

// Allow for the difficulty game modes of Standard mode to be pre-customized for window generation
// This uses the GameConfig class to apply these settings
public final class DifficultyConfigurationsForStandardMode {
	private DifficultyConfigurationsForStandardMode() {} // Make utility only
	
	// Easy mode configuration settings
	public static final GameConfig EASY = new GameConfig(
			"Standard Mode [Easy]",
			Color.decode("#c7eae9"),
			4, 4,
			new Dimension(110, 130),
			60, 30,
			750
		);
	
	// Normal mode configuration settings
	public static final GameConfig NORMAL = new GameConfig(
			"Standard Mode [Normal]",
			Color.decode("#d3f4d2"),
			6, 6,
			new Dimension(80, 100),
			44, 16,
			750
		);
	
	// Hard mode configuration settings
	public static final GameConfig HARD = new GameConfig(
			"Standard Mode [Hard]",
			Color.decode("#f2d486"),
			7, 8,
			new Dimension(65, 80),
			35, 16,
			750
		);
	
	// Advanced mode configuration settings
	public static final GameConfig ADVANCED = new GameConfig(
			"Standard Mode [Advanced]",
			Color.decode("#ffd2de"),
			8, 11,
			new Dimension(60, 70),
			35, 16,
			750
		);
}

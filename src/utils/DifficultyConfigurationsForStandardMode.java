package utils;

import java.awt.*;

import utils.GameConfig.GameType;

// Allow for the difficulty game modes of Standard mode to be pre-customized for window generation
// This uses the GameConfig class to apply these settings
public final class DifficultyConfigurationsForStandardMode {
	private DifficultyConfigurationsForStandardMode() {} // Make utility only
	
	//===== STANDARD MODE SECTION =====

	// Easy mode configuration settings
	public static final GameConfig STANDARD_EASY = new GameConfig(
			"Standard Mode [Easy]",
			Color.decode("#c7eae9"),
			4, 4,
			new Dimension(110, 130),
			60, 30,
			750,
			GameType.STANDARD,
			null // No timer
		);
	
	// Normal mode configuration settings
	public static final GameConfig STANDARD_NORMAL = new GameConfig(
			"Standard Mode [Normal]",
			Color.decode("#d3f4d2"),
			6, 6,
			new Dimension(80, 100),
			44, 16,
			750,
			GameType.STANDARD,
			null
		);
	
	// Hard mode configuration settings
	public static final GameConfig STANDARD_HARD = new GameConfig(
			"Standard Mode [Hard]",
			Color.decode("#f2d486"),
			7, 8,
			new Dimension(65, 80),
			35, 16,
			750,
			GameType.STANDARD,
			null
		);
	
	// Advanced mode configuration settings
	public static final GameConfig STANDARD_ADVANCED = new GameConfig(
			"Standard Mode [Advanced]",
			Color.decode("#ffd2de"),
			8, 11,
			new Dimension(60, 70),
			35, 16,
			750,
			GameType.STANDARD,
			null 
		);
	
	//===== TIMED MODE SECTION =====
	
	// Easy mode configuration settings
	public static final GameConfig TIMED_EASY = new GameConfig(
			"Timed Mode [Easy]",
			Color.decode("#c7eae9"),
			4, 4,
			new Dimension(110, 130),
			60, 30,
			750,
			GameType.TIMED,
			30 // 30 seconds
		);
	
	// Normal mode configuration settings
	public static final GameConfig TIMED_NORMAL = new GameConfig(
			"Timed Mode [Normal]",
			Color.decode("#d3f4d2"),
			6, 6,
			new Dimension(80, 100),
			44, 16,
			750,
			GameType.TIMED,
			60 // 60 seconds
		);
	
	// Hard mode configuration settings
	public static final GameConfig TIMED_HARD = new GameConfig(
			"Timed Mode [Hard]",
			Color.decode("#f2d486"),
			7, 8,
			new Dimension(65, 80),
			35, 16,
			750,
			GameType.TIMED,
			90 // 90 seconds
		);
	
	// Advanced mode configuration settings
	public static final GameConfig TIMED_ADVANCED = new GameConfig(
			"Timed Mode [Advanced]",
			Color.decode("#ffd2de"),
			8, 11,
			new Dimension(60, 70),
			35, 16,
			750,
			GameType.TIMED,
			120 // 120 seconds
		);
}

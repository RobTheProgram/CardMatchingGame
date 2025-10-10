package utils;

import java.awt.Cursor;

// Make a hand cursor object for utility purposes
public class HandCursorUtility {
	
	// Make a general hand cursor for the entire application
	public static Cursor getHandCursor() {
		return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	}
}

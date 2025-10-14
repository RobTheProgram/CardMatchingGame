package utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class AudiowideFont {
	
	// Reference Font base to be loaded once
	public static Font BASE;
	
	// Private constructor to prevent instantiation (the creation of new class-derived object)
	private AudiowideFont() {}
	
	// Allows for the customized font to be used globally throughout the application
	public static void register() {
		// To attempt to access resources for Audiowide font and then closes the InputStream once finished
		try(InputStream is = AudiowideFont.class.getResourceAsStream("/fonts/Audiowide-Regular.ttf")) {
			// To test if the font could not be located
			if (is == null) {
				System.out.println("Font not found!");
				return;
			}
			// Create a font object from True type font (TTF)
			Font awFont = Font.createFont(Font.TRUETYPE_FONT, is);
			// To register the font with the graphics environment so that it can be used by name
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(awFont);
		}
		
		catch(Exception e) {
			// Message for if failure to load font
			e.printStackTrace();
		}	
	}
	
	// Function to use anywhere to make the Audiowide font accessible
	public static Font get(float size, int style) {
		return new Font("Audiowide", style, (int) size);
	}
}


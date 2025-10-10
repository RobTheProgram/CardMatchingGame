package utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class AudiowideFont {
	
	public static Font BASE; // Font base to be loaded once
	
	private AudiowideFont() {}
	
	public static void register() {
		try(InputStream is = AudiowideFont.class.getResourceAsStream("/fonts/Audiowide-Regular.ttf")) {
			if (is == null) {
				System.out.println("Font not found!");
				return;
			}
			Font awFont = Font.createFont(Font.TRUETYPE_FONT, is);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(awFont);
			
			System.out.println("Font made!");
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


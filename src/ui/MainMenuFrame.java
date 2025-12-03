package ui;

import utils.HandCursorUtility;
import utils.ButtonCreation;
import utils.BoxLayoutSetup;
import utils.AudiowideFont;
import utils.ApplyRoundedBorder;
import utils.ApplyRadialGradientBackground;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.*;

import audio.AudioSettings;
import audio.SFXPlayer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.*;
import java.awt.geom.Point2D;

public class MainMenuFrame extends JFrame {
	
// =============== Main Menu Frame Setup Section ===============
	public MainMenuFrame() {
		// Title and setup execution
		super("Card Matching Game");
		// Called once to apply the Audiowide font
		AudiowideFont.register();
		// Gives the X close button on window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 1280 x 900 resolution of pixel size
		setSize(1280,900);
		// The window, when executed, won't generate according to any position on screen
		setLocationRelativeTo(null);
		// Replace the default single-colored content pane with a gradient panel
		setContentPane(ApplyRadialGradientBackground.createRadialGradientPanel(
				Color.decode("#ffffcc"), // center color shade
				Color.decode("#ffbf80")  // edge color shade
				));
		// To set layout to this new gradient panel
		getContentPane().setLayout(new BorderLayout());
		
		//===== Background Music Tracks =====
		String[] bgmTracks = {
				"Shuffle Mode (Random)",
			    "Track 1: Amp Plains",
			    "Track 2: Barren Valley",
			    "Track 3: Concealed Ruins",
			    "Track 4: Craggy Coast",
			    "Track 5: Crystal Cave",
			    "Track 6: Fortune Ravine",
			    "Track 7: Mystifying Forest",
			    "Track 8: Revelation Mountain",
			    "Track 9: Sealed Ruin",
			    "Track 10: Sky Tower",
			    "Track 11: Spacial Cliffs",
			    "Track 12: Temporal Tower",
			    "Track 13: Treeshroud Forest",
			    "Track 14: Vast Ice Mountain Peak",
			    "Track 15: Versus Boss"
			};
		
		//===== Main Page Title Label Section =====
		JLabel titleLabel = new JLabel("Card Matching Game", SwingConstants.CENTER);
		titleLabel.setFont(AudiowideFont.get(40f, Font.BOLD));
		titleLabel.setOpaque(false); // Make border background transparent
		
		// Border Creation for Main Page Title Label
		titleLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(12, 7, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		titleLabel.setFocusable(false); // Prevents any focus upon clicking on the label

		//===== Main Menu Label Section =====
		JLabel mainMenuLabel = new JLabel("Main Menu", SwingConstants.CENTER);
		mainMenuLabel.setFont(AudiowideFont.get(28f, Font.BOLD));
		mainMenuLabel.setOpaque(false); // Make border background transparent
		
		// Border Creation for Main Menu Title Label
		mainMenuLabel.setBorder(BorderFactory.createCompoundBorder(
				// Outer rounded border settings
				new ApplyRoundedBorder(12, 7, Color.BLACK),
				// Pads out the inside of the border to provide space for the main title label going counterclockwise with the parameters
				BorderFactory.createEmptyBorder(10, 25, 10, 25)
				));
		mainMenuLabel.setFocusable(false); // Prevents any focus upon clicking on the label
				
		//===== Game Mode Buttons Section =====
		JButton standardModeBtn = ButtonCreation.createGameModeButton("Standard Mode", Color.GREEN);
		JButton timedModeBtn = ButtonCreation.createGameModeButton("Timed Mode", Color.MAGENTA);

		
		// ===== Button Clicking Linking Event Listener =====
		
		// To link the main menu to the standard game mode screen
		standardModeBtn.addActionListener(e -> {
			new StandardModeMenuFrame().setVisible(true);
			dispose(); // To close this window after clicking
		});
		
		// To link the main menu to the timed game mode screen
		timedModeBtn.addActionListener(e -> {
			new TimedModeMenuFrame().setVisible(true);
			dispose(); // To close this window after clicking
		});
		
		
		// ===== Header Layout Panel Section ====
		JPanel headerPanel = BoxLayoutSetup.createVerticalPanel(
				Box.createVerticalStrut(1), // To add space from the top of the window to the header panel
				titleLabel,
				titleLabel,
				mainMenuLabel
				);
		
		// ===== Game Mode Button Layout Panel Section ====
		JPanel gameModeButtonPanel = BoxLayoutSetup.createVerticalPanel(
				Box.createVerticalStrut(7),	// To add space between the header and button panels			
				standardModeBtn,
				Box.createVerticalStrut(7),	// To add space between the buttons			
				timedModeBtn
				);
		
		// ===== Audio Control Section ====
		
		// Label for the bgm tracks
		JLabel bgmLabel = new JLabel("BGM Track: ");
		
		// Create drop down track menu
		JComboBox<String> trackDropdown = new JComboBox<>(bgmTracks);
		
		// Implement the play back features for the drop down
		trackDropdown.addActionListener(e -> {
		    int index = trackDropdown.getSelectedIndex(); // Gets the index of a playlist track
		    
		    // Default shuffled playlist play back
		    if (index == 0) {
		        AudioSettings.playRandom();
		    // Allows for the playing of a specific track upon selection
		    } else {
		        AudioSettings.playSpecificTrack(index - 1);
		    }
		});
		
		// ===== Mute Toggle Button for music =====
		JButton muteButton = new JButton("Mute");

		muteButton.addActionListener(e -> {
			AudioSettings.toggleMute();
		    muteButton.setText(AudioSettings.isMuted() ? "Unmute" : "Mute");
		});
		
		// ===== Shuffle Toggle Button =====
		JButton shuffleButton = new JButton(AudioSettings.isShuffleEnabled() ? "Shuffle: ON" : "Shuffle: OFF");

		shuffleButton.addActionListener(e -> {
		    AudioSettings.toggleShuffle();
		    shuffleButton.setText(AudioSettings.isShuffleEnabled() ? "Shuffle: ON" : "Shuffle: OFF");
		});
		
		// ===== Loop Selected Track Button =====
		JButton loopButton = new JButton(AudioSettings.isLoopEnabled() ? "Loop: ON" : "Loop: OFF");

		loopButton.addActionListener(e -> {
		    AudioSettings.toggleLoop();
		    loopButton.setText(AudioSettings.isLoopEnabled() ? "Loop: ON" : "Loop: OFF");
		});
		
		// ===== SFX (Sound Effects) Toggle Button =====
		JButton sfxButton = new JButton(SFXPlayer.isEnabled() ? "SFX: ON" : "SFX: OFF");

		sfxButton.addActionListener(e -> {
		    SFXPlayer.toggle();
		    sfxButton.setText(SFXPlayer.isEnabled() ? "SFX: ON" : "SFX: OFF");
		});
		
		// Main font for the audio components
		Font audioComponentsFont = AudiowideFont.get(16f, Font.PLAIN);
		
		// Apply the font to the components
		bgmLabel.setFont(AudiowideFont.get(18f, Font.PLAIN));
		trackDropdown.setFont(audioComponentsFont);
		muteButton.setFont(audioComponentsFont);
		shuffleButton.setFont(audioComponentsFont);
		loopButton.setFont(audioComponentsFont);
		sfxButton.setFont(audioComponentsFont);
		
		// Apply padding
		trackDropdown.setPreferredSize(new Dimension(325, 36));
		muteButton.setPreferredSize(new Dimension(150, 36));
		shuffleButton.setPreferredSize(new Dimension(150, 36));
		loopButton.setPreferredSize(new Dimension(150, 36));
		sfxButton.setPreferredSize(new Dimension(150, 36));
		
		// Create the panel to put all the musical components together
		JPanel musicPanel = new JPanel();
		// Apply stylization
		musicPanel.setOpaque(false);
		musicPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		// Add components to it
		musicPanel.add(bgmLabel);
		musicPanel.add(trackDropdown);
		musicPanel.add(muteButton);
		musicPanel.add(shuffleButton);
		musicPanel.add(loopButton);  // optional
		musicPanel.add(sfxButton);
		
		// ===== Add Components to Window Section ====
		
		// Add header panel to the window
		add(headerPanel, BorderLayout.NORTH);
		
		// Add game mode button panel to the window
		add(gameModeButtonPanel, BorderLayout.CENTER);
		
		// Add music panel to the bottom of the window
		add(musicPanel, BorderLayout.SOUTH);

	}
}	


	


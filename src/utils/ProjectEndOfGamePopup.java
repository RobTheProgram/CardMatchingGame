package utils;

import javax.swing.*;

import ui.StandardModeGameScreen;
import utils.DifficultyConfigurationsForStandardMode;

import java.awt.*;
import utils.ButtonCreation;
import audio.SFXPlayer;

public class ProjectEndOfGamePopup {
	private ProjectEndOfGamePopup() {} // Utility class only
	
	 /**
     * Shows a popup with the final score, time, and replay/quit options.
     *
     * @param window               The parent JFrame (the game screen)
     * @param finalScore           The score the player finished with
     * @param timeElapsedFormatted Preformatted string like "05:45" or "∞"
     * @param message 			   Displays the appropriate message indicating either victory or failure
     * @param onReplay             Callback if the player chooses to replay
     * @param onQuit               Callback if the player chooses to quit
     */
    public static void showPopup(
            JFrame window,
            int finalScore,
            String timeElapsedFormatted,
            String message,
            Runnable onReplay,
            Runnable onQuit
    ) {
    	// Create dialog popup
    	JDialog popup = new JDialog(window, message, true);
    	popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Create message panel
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40)); // padding around edges
        container.setBackground(Color.WHITE);
        
        // Title and stylization
        JLabel title = new JLabel(message);
        title.setFont(AudiowideFont.get(28f, Font.PLAIN));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Score display
        JLabel scoreLabel = new JLabel("Final Score: " + finalScore);
        scoreLabel.setFont(AudiowideFont.get(22f, Font.PLAIN));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Time display
        JLabel timeLabel = new JLabel("Time: " + timeElapsedFormatted);
        timeLabel.setFont(AudiowideFont.get(22f, Font.PLAIN));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Ask of the player if they wish to play again or not
        JLabel question = new JLabel("Would you like to play again?");
        question.setFont(AudiowideFont.get(20f, Font.PLAIN));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Button row
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        // Question buttons
        JButton replayBtn = ButtonCreation.createQuestionButton("Replay", Color.GREEN);
        JButton quitBtn = ButtonCreation.createQuestionButton("Quit", Color.RED);
        
        // Button for player wanting to replay the game
        replayBtn.addActionListener(e -> {
        	popup.dispose();		// Close victory popup
        	window.dispose();		// Close window game screen
        	
            // If caller provided a custom replay action, run it
            if (onReplay != null) onReplay.run();
            // Otherwise, restart the current game mode by default
            else new ui.StandardModeGameScreen(null).setVisible(true);
        });
        
        // Button for player wanting to return to the game mode screen
        quitBtn.addActionListener(e -> {
            popup.dispose();		// Close victory popup
            window.dispose();		// Close window game screen
            
            // If caller provided a custom quit action, run it
            if (onQuit != null) onQuit.run();
            // Otherwise, go back to the game mode menu by default
            else {
            	if (window instanceof StandardModeGameScreen screen) {
            		GameConfig config = screen.getConfig();
            		new StandardModeGameScreen(config).setVisible(true);
            	}
            }
        });
        
        // Add question buttons to the button row
        buttonRow.add(replayBtn);
        buttonRow.add(quitBtn);
        
        // Add the title and various labels + button row to the container
        container.add(title);
        container.add(Box.createVerticalStrut(10));
        container.add(scoreLabel);
        container.add(timeLabel);
        container.add(Box.createVerticalStrut(15));
        container.add(question);
        container.add(Box.createVerticalStrut(10));
        container.add(buttonRow);
        
        // Add container to the popup and position the popup on the window
        popup.setContentPane(container); 
        popup.pack();
        popup.setLocationRelativeTo(window);
        popup.setVisible(true);
        
    }
    
    // Should the player succeed in matching all cards within the time limit or plays to the end, display the victory popup
    public static void showVictoryPopup(
            JFrame window,
            int finalScore,
            String timeElapsedFormatted,
            String message,
            Runnable onReplay,
            Runnable onQuit
    ) {
    	// Play the victory sound effect
        SFXPlayer.play("/sfx/victory.wav");

        showPopup(window, 
        		finalScore, 
        		timeElapsedFormatted, 
        		"Victory!", 
        		onReplay, 
        		onQuit
        		);
    }
    
    // Should the player fail to match within the time limit, display the failure popup
    public static void showFailurePopup(
            JFrame window,
            int finalScore,
            String timeElapsedFormatted,
            String message,
            Runnable onReplay,
            Runnable onQuit
    ) {
    	
    	// Play the failure sound effect
        SFXPlayer.play("/sfx/failure.wav");
        
        showPopup(window, 
        		finalScore, 
        		timeElapsedFormatted, 
        		"Time's Up!", 
        		onReplay, 
        		onQuit
        		);
    }
}

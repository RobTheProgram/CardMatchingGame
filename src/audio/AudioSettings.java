package audio;

import java.util.Random;

import javax.sound.sampled.*;

public class AudioSettings {
    private static Clip currentClip;				// Represents a musical track
    private static FloatControl volumeController;
    public static boolean muted = false;			// Variable that determines if the music is muted or not
    private static final Random randomlySelectedTrack = new Random(); // Indicates a randomly selected track from the playlist
    private static boolean shuffleEnabled = true;   // Shuffle ON by default
    private static int currentIndex = -1; 			// Index to select the track (starts at the end)
    private static boolean loopSingle = false; // Specifies what tracks are played solely in a loop
    private static FloatControl gain = null;        // Gain or volume control
    private static float defaultVolumeDb = -15.0f;			// Sets the standard default volume for all background music
    
    // Returns true if Shuffle Mode is currently enabled (random track playback)
    public static boolean isShuffleEnabled() { return shuffleEnabled; }
    // Returns true if Loop Mode is currently enabled (repeats the same selected track)
    public static boolean isLoopEnabled() { return loopSingle; } 
    // Boolean to determine the blocking of any STOP events or not in music
    private static boolean suppressStopEvent = false;

    
    // Musical background tracks list
    public static final String[] playlist = {
            "/audio/amp_plains.wav",
            "/audio/barren_valley.wav",
            "/audio/concealed_ruins.wav",
            "/audio/craggy_coast.wav",
            "/audio/crystal_cave.wav",
            "/audio/fortune_ravine.wav",
            "/audio/mystifying_forest.wav",
            "/audio/revelation_mountain.wav",
            "/audio/sealed_ruin.wav",
            "/audio/sky_tower.wav",
            "/audio/spacial_cliffs.wav",
            "/audio/temporal_tower.wav",
            "/audio/treeshroud_forest.wav",
            "/audio/vast_ice_mountain_peak.wav",
            "/audio/versus_boss.wav"
        };
  
    // ===== Play a music track =====
    public static void playMusic(String filePath) {
        loopSingle = true; // user chose a track manually → loop only this one
        playClip(filePath);
    }
    
    // ===== Play shuffled tracks =====
    public static void playRandom() {
        loopSingle = false; // disable singular track looping
        int randomized_index = randomlySelectedTrack.nextInt(playlist.length); // Randomly picked track from the playlist
        currentIndex = randomized_index; // Store which track in now playing
        playClip(playlist[randomized_index]); // Play random track
    }
    
    // ===== Play specific track =====
    public static void playSpecificTrack(int singleTrackIndex) {
        shuffleEnabled = false; // Disable shuffled tracks from playing
        loopSingle = true; // Make a singular play true
        currentIndex = singleTrackIndex; // Make the current index the selected loop track
        playClip(playlist[singleTrackIndex]); // Play said single track from the index
    }
    
    // ===== Main playback method =====
    private static void playClip(String filePath) {
        try {
            suppressStopEvent = true; // Block STOP events during transition
            stopMusic();             // safely stop any current track
            suppressStopEvent = false;
            
//            // ===== Safely close old clip before starting a new one =====
//            if (currentClip != null) {
//                if (currentClip.isRunning()) currentClip.stop();
//                if (currentClip.isOpen()) currentClip.close();
//                currentClip = null;
//            }
            
        	// Retrieve the bgm tracks from the file path
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
            		AudioSettings.class.getResource(filePath)
            );
            
            // A new clip to represent the transition to a new song
            Clip newClip = AudioSystem.getClip();
            // Opens the retrieved clip from the file path
            newClip.open(audioStream);
            
            // Get the clip
            currentClip = newClip;

            // ===== Volume Setup =====
            if (currentClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                gain = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
                gain.setValue(muted ? -80.0f : defaultVolumeDb);
            } else {
                gain = null;
            }
            
            // When track ends, auto-play to the next one safely, or loop)
            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                	
                    // Prevent STOP from firing during manual switching
                    if (suppressStopEvent) return;
                    if (currentClip.isRunning()) return;
                    
                    if (loopSingle) {
                        playClip(playlist[currentIndex]); // replay same track
                        return;
                    }
                    
                    else if (shuffleEnabled) {			  // Play randomly shuffled tracks
                        playRandom();
                        return;
                    }
                    
                    // Neither loop nor shuffle → play next track sequentially
                    currentIndex = (currentIndex + 1) % playlist.length;
                    playClip(playlist[currentIndex]);
                }
            });
            
            // Start the song
            currentClip.start();
        
        // Fallback message in the event of an error
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== Stop music completely to avoid duplicate STOP events from happening at once =====
    public static void stopMusic() {
        try {
            suppressStopEvent = true; // Prevent recursive STOP calls
            
            // If there is a clip playing, stop and close it out
            if (currentClip != null) {
                if (currentClip.isRunning()) currentClip.stop();
                if (currentClip.isOpen()) currentClip.close();
            }

        } catch (Exception ignored) {

        } finally {
            suppressStopEvent = false;
            currentClip = null; // Ensure no ghost clips remain
        }
    }

    // ===== Change volume =====
    public static void setVolume(float volume) {
        if (volumeController != null) {
            volumeController.setValue(volume);
        }
    }
    
    // ===== Mute music =====
    public static void mute() {
        if (currentClip != null) currentClip.stop();
        muted = true;
    }

    // ===== Unmute music =====
    public static void unmute() {
        if (currentClip != null) currentClip.start();
        muted = false;
    }

    // ===== Toggle the muting of music =====
    public static void toggleMute() {
        muted = !muted;
        if (gain != null) {
        	// Sets the volume really low to simulate a muted state while still allowing the audio to play silently
            gain.setValue(muted ? -80.0f : defaultVolumeDb); // silence vs normal
        }
    }
    
    // ===== Getter for mute state =====
    public static boolean isMuted() {
        return muted;
    }
    
    // ===== Toggle Shuffle mode on for music tracks =====
    public static void toggleShuffle() {
        shuffleEnabled = !shuffleEnabled;
        // Turning shuffle on disables forced looping
        if (shuffleEnabled) loopSingle = false;
    }

    // ===== Toggle Loop mode on for music tracks =====
    public static void toggleLoop() {
        loopSingle = !loopSingle;
        // Turning loop on disables shuffle
        if (loopSingle) shuffleEnabled = false;
    }

}

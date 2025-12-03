package audio;

import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Map;

// Class to play sound effects for the cards and game conclusions?
public class SFXPlayer {

    // SFX toggle control
    private static boolean enabled = true;

    // Default backup dB
    private static final float DEFAULT_SFX_DB = -12f;

    // Per-sound custom volumes
    private static final Map<String, Float> VOLUME_MAP = new HashMap<>();

    static {
        // Tune each SFX to a balanced loudness
        VOLUME_MAP.put("/sfx/card_flip.wav",    -9.5f);
        VOLUME_MAP.put("/sfx/match.wav",        -13f);
        VOLUME_MAP.put("/sfx/mismatch.wav",     -11f);
        VOLUME_MAP.put("/sfx/victory.wav",      -13f);
        VOLUME_MAP.put("/sfx/failure.wav",      -14f);
    }

    // Toggles SFX ON/OFF 
    public static void toggle() {
        enabled = !enabled;
    }

    // Returns if SFX is enabled
    public static boolean isEnabled() {
        return enabled;
    }

    // Play a sound effect without interrupting BGM
    public static void play(String path) {
        if (!enabled) return;

        try {
        	// Retrieve SFX from directory path
            AudioInputStream baseStream =
                    AudioSystem.getAudioInputStream(SFXPlayer.class.getResource(path));

            AudioFormat baseFormat = baseStream.getFormat();

            // Convert to 16-bit PCM so Java can play it
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream convertedStream =
                    AudioSystem.getAudioInputStream(decodedFormat, baseStream);
            
            // Converted sfx clips to be played during game
            Clip clip = AudioSystem.getClip();
            clip.open(convertedStream);

            // Apply per-sound volume if the control is supported by Java
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                
                // Sets the applied volume to each specified clip (or the default volume if no support could be found)
                float volume = VOLUME_MAP.getOrDefault(path, DEFAULT_SFX_DB);
                // Apply said volume to the gain
                gain.setValue(volume);
            }
            // Play sfx on queue
            clip.start();

            // Auto-close after playback
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
          // In the event of no expected sfx file found
        } catch (Exception e) {
            System.err.println("SFX error for: " + path);
            e.printStackTrace();
        }
    }
}

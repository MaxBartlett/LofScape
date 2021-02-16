/** Sound plays the background music and the scream used with the lofgren JLabel.
 * @author Max Bartlett
 * @version 12/12/2015
 */
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
/**
 * Initializes the Clip and String used in playSound
 */
	Clip clip = null;
	String soundName;

/**
 * The constructor plays the theme on startup.
 */
	public Sound() {
		playSound("theme");
	}
/**
 * playSound performs the necessary steps for playing sounds.
 * If the sound is the theme music, it is looped until the program closes.
 * @param soundName String used to specify .wav file name.
 */
	public void playSound(String soundName) {
		try {
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(getClass().getResource("/music/" + soundName + ".wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			if (soundName == "theme")
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {
		} catch (IOException e) {
		} catch (UnsupportedAudioFileException e) {
		}
		clip.start();
	}
}

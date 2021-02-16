/** FullScreen handles fullscreen JLabels
 * @author Max Bartlett
 * @version 12/12/2015
 */
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FullScreen {
/**
 * Initializes ImageIcons, an array of ImageIcons, JLabels, and bounds to be used in setBounds.
 */
	ImageIcon instructionsImage = new ImageIcon(getClass().getResource("/images/ui/instructions.png")),
			winImage = new ImageIcon(getClass().getResource("/images/ui/winscreen.png"));
	ImageIcon[] lofgrenImage = new ImageIcon[6];
	JLabel openingScreen = new JLabel(), instructions = new JLabel(), winscreen = new JLabel(), lofgren = new JLabel();
	Rectangle bounds = new Rectangle(0, 0, 771, 641);
/**
 * The constructor class sets the icons for the JLabels and the ImageIcon array with a for-loop.
 * The class also sets the bounds and visibility of the JLabels.
 */
	public FullScreen() {
		instructions.setIcon(instructionsImage);
		winscreen.setIcon(winImage);
		for (int i = 0; i < 6; i++) {
			lofgrenImage[i] = new ImageIcon(
					getClass().getResource("/images/lofgren/lofgren" + Integer.toString(i + 1) + ".jpg"));
		}
		lofgren.setBounds(bounds);
		instructions.setBounds(bounds);
		winscreen.setBounds(bounds);
		lofgren.setVisible(false);
		winscreen.setVisible(false);
	}
}
/** Frame adds and packs the GameScreen JPanel to a JFrame, modifies the specified  and displays it.
 * @author Max Bartlett
 * @version 12/12/2015
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	GameScreen gameScreen = new GameScreen();

	public Frame() {

		initGameScreen();

	}

	private void initGameScreen() {
		add(gameScreen);
		pack();
		setTitle("LofScape");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame gameFrame = new Frame();
				gameFrame.setVisible(true);
				gameFrame.setSize(771, 641);
				gameFrame.setResizable(false);
			}
		});
	}
}

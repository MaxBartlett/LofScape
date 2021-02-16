/** GameScreen handles the JPanel and its components to be displayed on the JFrame. It also handles keybindings.
 * @author Max Bartlett
 * @version 12/12/2015
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameScreen extends JPanel implements ActionListener {
/**
 * Initializes instances of all the necessary classes.  
 * Random is used to pick a random image for lofgren.
 * The timer calls actionPerformed (an action listener) every 50 ms.
 * im and am are used for the keybindings.
 * Step is used to check if the player has completed certain parts of the game.
 */
	GameMap map = new GameMap();
	Character character = new Character();
	GUI gui = new GUI();
	Random random = new Random();
	FullScreen fullscreen = new FullScreen();
	Sound sound = new Sound();
	Timer timer = new Timer(50, this);
	InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	ActionMap am = getActionMap();
	JLabel goal = new JLabel("Go to Draynor Manor", SwingConstants.CENTER);
	int step = 1;
	
/**
 * The GameScreen constructor adds the layout, sets the keybindings, and starts the timer.
 */
	GameScreen() {
		addLayout();
		keyBinding();
		timer.start();
	}
/**
 * addLayout sets the layout for the JLabels (text and images) that are displayed on screen.
 * addLayout also alters the font, color, and location of the JLabel goal.
 */
	public void addLayout() {
		setLayout(null);
		add(fullscreen.lofgren);
		add(fullscreen.instructions);
		add(fullscreen.winscreen);
		add(goal);
		add(gui.inventory);
		add(gui.chat);
		add(map.location);
		goal.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		goal.setForeground(Color.white);
		goal.setBounds(483, 0, 300, 100);
	}
/**
 * paintComponent draws the map and character to the screen. 
 * @param g Graphics object used to draw images.
 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.drawMap(g);
		character.drawCharacter(g);
	}
/**
 * Because GameScreen implements ActionListener, it is required to have an actionPerformed method.
 * actionPerformed repeats every time the timer is called (every 50 ms).
 * It repaints the screen, and calls the checkLocation and goals methods.
 */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		map.checkLocation();
		goals();
	}
/**
 * goals() updates the goal JLabel, which displays the goals necessary to complete the game.
 * It checks current location and if the location matches the goal, it moves to the next goal.
 */
	public void goals() {
		if (map.location.getText() == "Draynor Manor" && step == 1) {
			goal.setText("Go to Falador");
			step++;
		}

		if (map.location.getText() == "Falador" && step == 2) {
			goal.setText("Go to Varrock");
			step++;
		}

		if (map.location.getText() == "Varrock" && step == 3) {
			goal.setText("Go to the Wilderness");
			step++;
		}

		if (map.location.getText() == "Wilderness" && step == 4) {
			goal.setText("Press L");
			step++;
		}
	}
/**
 * The keybindings assign keypresses and keyreleases to actions.
 */
	public void keyBinding() {
		setFocusable(true);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move Up");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move Down");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move Left");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move Right");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "Lofgren");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "Stop");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Stop");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "Stop");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "Stop");

		am.put("Move Up", moveUp);
		am.put("Move Down", moveDown);
		am.put("Move Left", moveLeft);
		am.put("Move Right", moveRight);
		am.put("Stop", stop);
		am.put("Exit", exit);
		am.put("Action", action);
		am.put("Lofgren", lofgren);
	}
/**
 * The following actions determine what happens when a key is pressed or released.
 * The moveUp, moveDown, moveLeft, and moveRight actions update the map location and character animation.
 * stop stops the character from moving.
 * exit closes the game screen.
 * action closes the instruction screen
 * lofgren opens up the lofgren JLabel and plays a scream sound. It opens the game over screen when pressed again.
 */
	Action moveUp = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			character.animateCharacter(0, 0, 7);
			map.moveMap("up");
		}
	};
	Action moveDown = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			character.animateCharacter(1, 0, 8);
			map.moveMap("down");
		}
	};
	Action moveLeft = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			character.animateCharacter(2, 0, 6);
			map.moveMap("left");
		}
	};
	Action moveRight = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			character.animateCharacter(3, 0, 6);
			map.moveMap("right");
		}
	};

	Action stop = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			character.animateCharacter(character.rows - 1, 0, 0);
			map.moveMap("stop");
		}
	};
	Action exit = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	Action action = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			if (fullscreen.instructions.getParent() != null) {
				remove(fullscreen.instructions);
			}
		}
	};
	Action lofgren = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			if (fullscreen.lofgren.isVisible() == true && step == 6) {
				remove(fullscreen.lofgren);
				fullscreen.winscreen.setVisible(true);
			}

			if (fullscreen.lofgren.isVisible() == false && step == 5) {
				fullscreen.lofgren.setIcon(fullscreen.lofgrenImage[random.nextInt(6)]);
				fullscreen.lofgren.setVisible(true);
				sound.playSound("lofgren");
				step++;
			}
		}
	};
}

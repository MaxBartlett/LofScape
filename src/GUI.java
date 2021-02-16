/** GUI handles the chat and inventory JLabels.
 * @author Max Bartlett
 * @version 12/12/2015
 */
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GUI {
/**
 * Initializes ImageIcons and JLabels
 */
	ImageIcon inventoryImage = new ImageIcon(getClass().getResource("/images/ui/inventory.png")),
			chatImage = new ImageIcon(getClass().getResource("/images/ui/chatbox.png"));
	JLabel inventory = new JLabel(), chat = new JLabel();

/**
 * The class constructor sets the ImageIcons to their designated JLabels and sets the bounds for each label.
 */
	public GUI() {
		inventory.setIcon(inventoryImage);
		chat.setIcon(chatImage);
		inventory.setBounds(500, 0, 265, 500);
		chat.setBounds(0, 500, 800, 140);
	}
}

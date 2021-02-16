/** GameMap handles all methods relating to the map, including displaying and moving the map, and changing the area names.
 * @author Max Bartlett
 * @version 12/12/2015
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class GameMap {
/**
 * Initializes entire map and map parts, map location variables, map location label, and HashMap of city locations.
 */
	BufferedImage map = new BufferedImage(2500, 2500, BufferedImage.TYPE_INT_ARGB), mapPart = null;
	public int mapX = 1580, mapY = 2090, velX = 0, velY = 0;
	JLabel location = new JLabel();
	Map<String, Area> cities = new HashMap<String, Area>();
/**
 * The constructor class loads the entire map and mapPart, sets the location name, and modifies JLabel text options for location.
 */
	public GameMap() {
		try {
			map = ImageIO.read(getClass().getResource("/images/map/map.png"));
		} catch (IOException e) {
		}
		loadMap();
		setLocation();
		textOptions();
	}
/**
 * loadMap gets a 100x100 pixel subImage of the larger map image
 */
	public void loadMap() {
		mapPart = map.getSubimage(mapX, mapY, 100, 100);
	}
/**
 * drawMap draws the map at the specified location and size.
 * @param g Graphics object used to draw images.
 */
	public void drawMap(Graphics g) {
		g.drawImage(mapPart, 0, 0, 500, 500, null);
	}

/**
 * moveMap is used in the GameScreen class keybinding actions to move the map in a specified direction.
 * @param direction Specifies the direction in which to move the map
 */
	public void moveMap(String direction) {
		if (direction.equalsIgnoreCase("up")) {
			velY = -1;
			velX = 0;
		}

		if (direction.equalsIgnoreCase("down")) {
			velY = 1;
			velX = 0;
		}

		if (direction.equalsIgnoreCase("left")) {
			velX = -1;
			velY = 0;
		}

		if (direction.equalsIgnoreCase("right")) {
			velX = 1;
			velY = 0;
		}
		if (direction.equalsIgnoreCase("stop")) {
			velX = 0;
			velY = 0;
		}
		mapX += velX;
		mapY += velY;
		loadMap();
	}
/**
 * putLocation puts keys and values into the HashMap cities
 * @param locationName Specifies the key in the HashMap entry.
 * @param x Specifies the x coordinate of the bounding rectangle.
 * @param y Specifies the y coordinate of the bounding rectangle.
 * @param width Specifies the width of the bounding rectangle.
 * @param height Specifies the height of the bounding rectangle.
 */
	public void putLocation(String locationName, int x, int y, int width, int height) {
		cities.put(locationName, new Area(new Rectangle(x, y, width, height)));
	}
/**
 * setLocation Sets the location of all cities and their coordinates
 */
	public void setLocation() {
		putLocation("Lumbridge", 1441, 1906, 317, 279);
		putLocation("Lumbridge Swamp", 1390, 2201, 329, 180);
		putLocation("Wizard's Tower", 1241, 2174, 91, 182);
		putLocation("Draynor Village", 1118, 1860, 200, 196);
		putLocation("Varrock", 1369, 1251, 461, 430);
		putLocation("Draynor Manor", 1212, 1640, 138, 175);
		putLocation("Port Sarim", 938, 1994, 206, 397);
		putLocation("Rimmington", 695, 2003, 270, 224);
		putLocation("Falador", 767, 1618, 394, 264);
		putLocation("Edgeville", 1183, 1249, 187, 172);
		putLocation("Barbarian Village", 1165, 1455, 102, 140);
		putLocation("Chaos Temple", 1605, 900, 158, 153);
		putLocation("Wilderness", 662, 76, 1464, 1168);
	}
/**
 * checkLocation compares the current mapX and mapY values to the HashMap entries to display the current location of the player in the JLabel location.
 * If the current location isn't in the HashMap, it is set as "LofScape."
 */
	public void checkLocation() {
		int i = 0;
		for (Map.Entry<String, Area> entry : cities.entrySet()) {
			i++;
			if (entry.getValue().contains(mapX, mapY)) {
				location.setText(entry.getKey());
				break;
			}
			if (i == 13) {
				location.setText("LofScape");
			}
		}
	}
/**
 * textOptions sets the font, color, and bounds of the the JLabel location.
 */
	public void textOptions() {
		location.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		location.setForeground(Color.white);
		location.setBounds(0, 0, 200, 30);
	}
}

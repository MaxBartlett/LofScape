/** Character handles all actions related to animating and displaying the character.
 * @author Max Bartlett
 * @version 12/12/2015
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Character {
/** 
 * Initializes BufferedImage knightSheet (a spritesheet) and knight (the character image itself).
 * The ints are used in the for-loop
 * knightArray holds the images in the spritesheet so they can be easily called later.
 */
	public BufferedImage knightSheet, knight;
	int width = 34, height = 34, rows = 4, cols = 15, i = 0;
	BufferedImage[][] knightArray = new BufferedImage[rows][cols];

/**
 * The constructor class loads the spritesheet and loops through it to put each image in the 2D array knightArray.
 * The constructor also sets the default sprite position that loads when the game starts.
 */
	public Character() {
		try {
			knightSheet = ImageIO.read(getClass().getResource("/images/character/character_spritesheet.png"));
		} catch (IOException e) {
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				knightArray[i][j] = knightSheet.getSubimage((2 * (j + 1)) + (width * j), (2 * (i + 1)) + (height * i),
						width, height);
			}
		}
		animateCharacter(1, 0, 0);
	}
/**
 * drawCharacter draws the character sprite at the specified location and size.
 * @param g Graphics object used to draw images.
 */
	public void drawCharacter(Graphics g) {
		g.drawImage(knight, 250, 250, 45, 45, null);
	}

/**
 * animateCharacter loops through the 2D array knightSheet to frame-by-frame animation.
 * @param rows Row from which to get image (starts at 0).
 * @param startCol Column at which to start to get image (starts at 0).
 * @param endCol Column at which to end to get image (starts at 0).
 */
	public void animateCharacter(int rows, int startCol, int endCol) {
		if (i <= endCol) {
			knight = knightArray[rows][i];
		} else {
			i = 0;
		}
		i++;
	}
}
/**
 * 
 */
package sdp.monkeygame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 
 * @author Sahana Ravikumar
 *
 * Banana
 */
public class Banana {

	private int x;
	private int y;
	private BufferedImage bananaImage;
	private Random random;

	/**
	 * banana image is stored in the resource folder.
	 * @return banana image
	 */
	public BufferedImage getBananaImage() {
		URL bananaResource = getClass().getResource("/media/banana.png");
		try {
			bananaImage = ImageIO.read(bananaResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bananaImage;
	}

	/**
	 *  calculate banana co-ordinates using Java.Util.Random function
	 * @return banana
	 */
	public Banana randomNumberGenerator() {
		random = new Random();
		x = random.nextInt(19) * Constants.SQUARE_SIZE;
		y = random.nextInt(19) * Constants.SQUARE_SIZE;
		if (x >= Constants.FRAME_WIDTH || y >= Constants.FRAME_HEIGHT)
			randomNumberGenerator();
		return this;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}

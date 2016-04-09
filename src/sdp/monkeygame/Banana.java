/**
 * 
 */
package sdp.monkeygame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Banana {
	
	public int x ;
	public int y ;
	private BufferedImage bananaImage;
	
	public BufferedImage getBananaImage(){
		URL bananaResource = getClass().getResource("/media/banana.png");
		try {
			bananaImage = ImageIO.read(bananaResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bananaImage;
	}
	
	public Banana randomNumberGenerator(){
		x = (int)(Math.random()*100) + Constants.SQUARE_SIZE;
		y = (int)(Math.random()*100) + Constants.SQUARE_SIZE;
		return this;
	}
}

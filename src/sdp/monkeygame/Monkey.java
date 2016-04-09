/**
 * 
 */
package sdp.monkeygame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import sdp.monkeygame.states.RestState;

public class Monkey {

	public int x;
	public int y;
	private BufferedImage monkeyImage;
	MonkeyState monkeyState;
	
	public Monkey() {
		monkeyState=new RestState();
		x = 0;
		y = 0;
	}
	
	public void setMonkeyState(MonkeyState newMonkeyState){
		monkeyState = newMonkeyState;
	}
	
	public BufferedImage getMonkeyImage(){
		URL monkeyResource = getClass().getResource("/media/monkey.png");
		try {
			monkeyImage = ImageIO.read(monkeyResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return monkeyImage;
	}

	public MonkeyState keyUp(Monkey monkey){
		return monkeyState.keyUp(this);
	}
	
	public MonkeyState keyDown(Monkey monkey){
		return monkeyState.keyDown(this);
	}
	
	public MonkeyState keyLeft(Monkey monkey){
		return monkeyState.keyLeft(this);
	}
	
	public MonkeyState keyRight(Monkey monkey){
		return monkeyState.keyRight(this);
	}
	
	public MonkeyState keyReleased(Monkey monkey){
		return monkeyState.keyReleased(this);
	}
}

/**
 * 
 */
package sdp.monkeygame.states;

import sdp.monkeygame.Constants;
import sdp.monkeygame.Monkey;
import sdp.monkeygame.MonkeyState;

public class MoveState extends MonkeyState{
	
	public MonkeyState keyUp(Monkey monkey){
		monkey.y -= Constants.SQUARE_SIZE;
		monkey.setMonkeyState(this);
		return this;
	}
	
	public MonkeyState keyDown(Monkey monkey){
		monkey.y += Constants.SQUARE_SIZE;
		monkey.setMonkeyState(this);
		return this;
	}
	
	public MonkeyState keyLeft(Monkey monkey){
		monkey.x -= Constants.SQUARE_SIZE;
		monkey.setMonkeyState(this);
		return this;
	}
	
	public MonkeyState keyRight(Monkey monkey){
		monkey.x += Constants.SQUARE_SIZE;
		monkey.setMonkeyState(this);
		return this;
	}
	
	public MonkeyState keyReleased(Monkey monkey){
		monkey.setMonkeyState(new RestState());
		return new RestState();
	}
}

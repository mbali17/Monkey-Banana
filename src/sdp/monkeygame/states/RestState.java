/**
 * 
 */
package sdp.monkeygame.states;

import sdp.monkeygame.Constants;
import sdp.monkeygame.Monkey;
import sdp.monkeygame.MonkeyState;

public class RestState extends MonkeyState{	
	
	public MonkeyState keyUp(Monkey monkey){
		monkey.y -= Constants.SQUARE_SIZE;
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}
	
	public MonkeyState keyRight(Monkey monkey){
		monkey.x += Constants.SQUARE_SIZE;
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}
	
	public MonkeyState keyDown(Monkey monkey){
		monkey.y += Constants.SQUARE_SIZE;
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}
	
	public MonkeyState keyLeft(Monkey monkey){
		monkey.x -= Constants.SQUARE_SIZE;
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}
	
}

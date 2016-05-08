/**
 * 
 */
package sdp.monkeygame.states;

import sdp.monkeygame.Constants;
import sdp.monkeygame.Monkey;
import sdp.monkeygame.MonkeyState;

/**
 * 
 * @author Sahana Ravikumar
 *
 * Monkey is idle
 * 
 * When the monkey is idle and the key is pressed, 
 * the new state of the monkey is set to moving and 
 * the Movestate is returned.
 */
public class RestState extends MonkeyState {

	public MonkeyState keyUp(Monkey monkey) {
		monkey.setY(monkey.getY() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}

	public MonkeyState keyRight(Monkey monkey) {
		monkey.setX(monkey.getX() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}

	public MonkeyState keyDown(Monkey monkey) {
		monkey.setY(monkey.getY() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}

	public MonkeyState keyLeft(Monkey monkey) {
		monkey.setX(monkey.getX() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MoveState());
		return new MoveState();
	}

}

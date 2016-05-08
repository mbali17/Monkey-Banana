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
 * Monkey is in motion
 * 
 * When the monkey is in motion and the key is pressed, 
 * the state of the monkey continues to be moving and 
 * the Movestate is returned.
 * 
 * When the monkey is in motion and the key is released, 
 * the state of the monkey changes to idle and 
 * the RestState is returned.
 */
public class MoveState extends MonkeyState {

	public MonkeyState keyUp(Monkey monkey) {
		monkey.setY(monkey.getY() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}

	public MonkeyState keyDown(Monkey monkey) {
		monkey.setY(monkey.getY() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}

	public MonkeyState keyLeft(Monkey monkey) {
		monkey.setX(monkey.getX() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}

	public MonkeyState keyRight(Monkey monkey) {
		monkey.setX(monkey.getX() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}

	public MonkeyState keyReleased(Monkey monkey) {
		monkey.setMonkeyState(new RestState());
		return new RestState();
	}
}

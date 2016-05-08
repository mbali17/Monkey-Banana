/**
 * 
 */
package sdp.monkeygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Sahana Ravikumar
 *
 */
public class Wonderland extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private Monkey monkey;
	private Banana banana;

	private static JFrame frame;
	private static JPanel controlPanel;
	private static JLabel timerLabel, bananaLabel, bananatimerLabel;
	Timer timer = new Timer(1000, this);

	private int GAME_TIMER = 50;

	private int BANANA_TIMER = 8;

	private int BANANAS_REMAINING = 8;

	private boolean isCollision;

	public static void main(String[] args) {
		initializeUI();
	}

	/**
	 * create the screen and add UI elements
	 */
	private static void initializeUI() {

		frame = new JFrame("Monkey Banana game");
		bananaLabel = new JLabel("Number of bananas to be eaten = 8");
		timerLabel = new JLabel("Time remaining = 20");
		bananatimerLabel = new JLabel("Eat this banana within 6 s");

		createPanel();
		createFrame();
	}

	private static void createPanel() {
		controlPanel = new JPanel();
		controlPanel.add(timerLabel);
		controlPanel.add(bananaLabel);
		controlPanel.add(bananatimerLabel);
		controlPanel.setBounds(0, 0, 500, 30);
		controlPanel.setBackground(Color.GREEN);
	}

	private static void createFrame() {
		frame.add(new Wonderland());
		frame.setBackground(Color.GREEN);
		frame.setResizable(false);
		frame.add(controlPanel, BorderLayout.NORTH);
		frame.pack();
		frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * The below logic is performed every second. GAME_TIMER and BANANA_TIMER are checked.
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		if (BANANA_TIMER > 0) {
			BANANA_TIMER--;
			bananatimerLabel.setText("Eat this banana within " + BANANA_TIMER + "s");
		}
		if (BANANA_TIMER < 0) {
			updateBananaPosition();
		} else if (BANANA_TIMER == 0) {
			updateBananaPosition();
			BANANA_TIMER = 8;
			repaint();
		}
		if (GAME_TIMER > 0) {
			GAME_TIMER--;
			timerLabel.setText("  Time remaining = " + GAME_TIMER);
		} else {
			gameOver();
		}
	}

	/**
	 * Display appropriate text on game over
	 */
	public void gameOver() {
		timer.stop();
		timerLabel.setText("  Game over" + "Number of bananas eaten = " + (Constants.BANANAS - BANANAS_REMAINING));
		bananatimerLabel.setText("");
		if (BANANAS_REMAINING > 0) {
			bananaLabel.setText("You lose!!");
		} else {
			bananaLabel.setText("You win!!");
		}
		repaint();
	}

	/**
	 * create monkey and banana in the wonderland
	 */
	public Wonderland() {
		timer.start();
		monkey = new Monkey();
		banana = new Banana();
		updateBananaPosition();
		setBackground(Color.GREEN);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	/**
	 * updating the banana position
	 */
	public void updateBananaPosition() {
		banana.randomNumberGenerator();
	}

	/**
	 * paint banana and monkey on the UI
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (GAME_TIMER > 0 && BANANAS_REMAINING > 0) {
			displayBanana(g);
			g.drawImage(monkey.getMonkeyImage(), monkey.getX(), monkey.getY(), Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this);
		}
	}

	public void displayBanana(Graphics g) {
		g.drawImage(banana.getBananaImage(), banana.getX(), banana.getY(), Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this);
	}

	/**
	 * handle keyboard key pressed event
	 * 
	 * Also, handle the screen boundary conditions
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		/**
		 *  keyboard up key is pressed
		 */
		if (code == KeyEvent.VK_UP) {
			if (monkey.getX() < 30) {
				monkey.setY(30);
			}
			monkey.keyUp(monkey);
		}
		
		/**
		 *  keyboard down key is pressed
		 */
		if (code == KeyEvent.VK_DOWN) {
			if (monkey.getY() > 480) {
				monkey.setY(480);
			}
			monkey.keyDown(monkey);
		}
		
		/**
		 *  keyboard left key is pressed
		 */
		if (code == KeyEvent.VK_LEFT) {
			if (monkey.getX() < 30) {
				monkey.setX(30);
			}
			monkey.keyLeft(monkey);
		}
		
		/**
		 *  keyboard right key is pressed
		 */
		if (code == KeyEvent.VK_RIGHT) {
			if (monkey.getX() > 510) {
				monkey.setX(510);
			}
			monkey.keyRight(monkey);
		}

		isCollision = checkCollision();

		/**
		 * if banana and monkey are colliding update the banana position
		 */
		if (isCollision == true && BANANAS_REMAINING >= 0) {
			bananaLabel.setText("Number of bananas to be eaten = " + BANANAS_REMAINING);
			updateBananaPosition();
			BANANA_TIMER = 8;
		}
		
		/**
		 * if bananas to be eaten is 0, then game is over
		 */
		if (BANANAS_REMAINING == 0)
			gameOver();
		repaint();
	}

	/**
	 * check if monkey banana collision
	 * 
	 * @return true; if monkey and banana positions are same
	 * 		   false; otherwise
	 */
	public boolean checkCollision() {
		System.out.println("monkey n banana val " + monkey.getX() + " " + banana.getX() + " " + monkey.getY() + " "
				+ banana.getY());
		if (monkey.getX() == banana.getX() && monkey.getY() == banana.getY()) {
			System.out.println("inside coll");
			BANANAS_REMAINING--;
			return true;
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		monkey.keyReleased(monkey);
	}
}

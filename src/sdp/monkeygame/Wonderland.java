/**
 * 
 */
package sdp.monkeygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wonderland extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private Monkey monkey;
	private Banana banana;

	private static JFrame frame;
	private static JPanel controlPanel;
	private static JLabel timerLabel, bananaLabel, bananatimerLabel;
	Timer timer = new Timer(1000, this);

	private int GAME_TIMER = 20;

	private int BANANA_TIMER = 5;

	private int BANANAS_REMAINING = 10;

	private boolean isCollision;

	public static void main(String[] args) {
		initializeUI();
	}

	private static void initializeUI() {

		frame = new JFrame("Monkey Banana game");
		bananaLabel = new JLabel("Number of bananas to be eaten = 10");
		timerLabel = new JLabel("Time remaining = 20");
		bananatimerLabel = new JLabel("Eat this banana within 5 s");

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

	public void actionPerformed(ActionEvent actionEvent) {
		if (BANANA_TIMER > 0) {
			BANANA_TIMER--;
			bananatimerLabel.setText("Eat this banana within " + BANANA_TIMER + "s");
		}
		if (GAME_TIMER > 0) {
			GAME_TIMER--;
			timerLabel.setText("Time remaining = " + GAME_TIMER);
		} else {
			gameOver();
		}
	}

	public void gameOver() {
		timer.stop();
		timerLabel.setText("Game over" + "Number of bananas eaten = " + (Constants.BANANAS - BANANAS_REMAINING));
		bananatimerLabel.setText("");
		if (BANANAS_REMAINING > 0) {
			bananaLabel.setText("You lose!!");
		} else {
			bananaLabel.setText("You win!!");
		}
	}

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

	public void updateBananaPosition() {
		banana.randomNumberGenerator();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(banana.getBananaImage(), banana.x, banana.y, 40, 40, this);
		g.drawImage(monkey.getMonkeyImage(), monkey.x, monkey.y, 40, 40, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			monkey.keyUp(monkey);
		}
		if (code == KeyEvent.VK_DOWN) {
			monkey.keyDown(monkey);
		}
		if (code == KeyEvent.VK_LEFT) {
			monkey.keyLeft(monkey);
		}
		if (code == KeyEvent.VK_RIGHT) {
			monkey.keyRight(monkey);
		}

		isCollision = checkCollision();

		if (isCollision == true && BANANAS_REMAINING >= 0) {
			bananaLabel.setText("Number of bananas to be eaten = " + BANANAS_REMAINING);
			updateBananaPosition();
			BANANA_TIMER = 5;
		}
		if (BANANA_TIMER == 0) {
			updateBananaPosition();
		}
		if (BANANAS_REMAINING == 0)
			gameOver();
		repaint();
	}

	public boolean checkCollision() {
		if ((Math.abs(banana.x - monkey.x) <= Constants.DIFFERENCE)
				|| (Math.abs(monkey.y - banana.y) <= Constants.DIFFERENCE) || ((Math.abs(banana.x - monkey.x) == 0))
				|| (Math.abs(banana.y - monkey.y) == 0)) {
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

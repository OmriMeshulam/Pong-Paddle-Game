import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static PlayerPaddle player;
	public static AIPaddle ai;

	InputHandler IH;

	JFrame frame; // Window of the game
	public final int WIDTH = 400;
	public final int HEIGHT = WIDTH / 16 * 9;
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT);
	public final String TITLE = "Pong!";

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	static boolean gameRunning = false;
		
	@Override
	public void run() {

		// Game loop
		while (gameRunning) {
			tick();
			render();

			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized void start() {
		gameRunning = true;
		new Thread(this).start();
	
	}

	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	}

	public Game() {

		frame = new JFrame();

		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);

		IH = new InputHandler(this);

		player = new PlayerPaddle(10, 60);
		ai = new AIPaddle(getWidth() - 25, 60);

	}

	public void tick() { // Updates

		player.tick(this);
		ai.tick(this);
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Black
																	// background

		/*
		 * g.setColor(Color.RED); g.fillRect(0, 0, getWidth(), getHeight());
		 */
		player.render(g);
		ai.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}

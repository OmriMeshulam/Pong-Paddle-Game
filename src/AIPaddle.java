import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	int x;
	int y;
	int width = 15;
	int height = 60;
	int speed = 1;

	Rectangle boundingBox;

	boolean goingUp = false;
	boolean goingDown = false;

	public AIPaddle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, height);
		boundingBox.setBounds(x, y, width, height);
	}

	public void tick(Game game) {
		
		boundingBox.setBounds(x, y, width, height);

		if (game.ball.y - height/2 < y && y >= 0) {
			y-= speed;
		}
		
		if (game.ball.y > y + height/2 && y + height <= game.getHeight()) {
			y+= speed;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	int x;
	int y;
	int width = 15;
	int height = 40;
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
		if (game.player.y < y) {
			y--;
		}
		
		if (game.player.y > y) {
			y++;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);

	}
}
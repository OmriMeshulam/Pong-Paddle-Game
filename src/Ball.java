import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	int x, y;
	int size = 16;
	int speed = 2;

	Rectangle boundingBox;

	int vx, vy;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, size, size);
		boundingBox.setBounds(x, y, size, size);

		vx = speed;
		vy = speed;
	}
	
	public void paddleCollision(Game game){
		if(boundingBox.intersects(game.player.boundingBox)){
			vx = speed;
		}else if(boundingBox.intersects(game.ai.boundingBox)){
			vx = -speed;
		}
	}

	public void tick(Game game) {

		boundingBox.setBounds(x, y, size, size);

		if (x <= 0) { // If left edge of screen
			vx = speed;
			game.p2Score++;
		} else if (x + size >= game.getWidth()) { // If right edge of screen, flip
			game.p1Score++;									
			vx = -speed;
		}

		if (y <= 0) { // Top of screen
			vy = speed;
		} else if (y + size >= game.getHeight()) { // Bottom of screen
			vy = -speed;
		}

		x += vx;
		y += vy;
		
		paddleCollision(game);
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, size, size);

	}
}

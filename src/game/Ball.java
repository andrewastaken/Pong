package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle
{	
	private static final long serialVersionUID = 1L;
	private final int SPEED_INCREMENT = 1;
	
	private int xDirection, yDirection, speed;
	
	public Ball(int x, int y, int radius)
	{
		super(x, y, radius * 2, radius * 2);
		
		Random random = new Random();
		xDirection = random.nextInt(2);
		yDirection = random.nextInt(2);
		
		if(xDirection == 0)
			xDirection--;
		if(yDirection == 0)
			yDirection--;
		
		speed = 2;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval((int) x, (int) y, (int) width, (int) height);
	}
	
	public void move()
	{
		x += xDirection * speed;
		y += yDirection * speed;
	}
		
	public void inverseXVelocity() { xDirection *= -1; }
		
	public void inverseYVelocity() { yDirection *= -1; }
	
	public void incrementVelocity() { speed += SPEED_INCREMENT; }
}

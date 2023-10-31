package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle
{
	private static final long serialVersionUID = 1L;
	private final int SPEED = 10;
	
	private int yDirection;
	private boolean onLeftSide;
	
	public Paddle(int x, int y, int width, int height, boolean isOnLeftSide)
	{
		super(x, y, width, height);
		yDirection = 0;
		onLeftSide = isOnLeftSide;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(onLeftSide ? Color.RED : Color.BLUE);
		g.fillRect(x, y, width, height);
	}
	
	public void keyPressed(KeyEvent event)
	{
		if(onLeftSide)
		{
			if(event.getKeyCode() == KeyEvent.VK_W)
				yDirection = -1;
			else if(event.getKeyCode() == KeyEvent.VK_S)
				yDirection = 1;
		}
		else
		{
			if(event.getKeyCode() == KeyEvent.VK_UP)
				yDirection = -1;
			else if(event.getKeyCode() == KeyEvent.VK_DOWN)
				yDirection = 1;
		}
	}
	
	public void keyReleased(KeyEvent event)
	{
		if(onLeftSide)
		{
			if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S)
				yDirection = 0;
		}
		else
		{
			if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN)
				yDirection = 0;
		}
	}

	public void move() { y += yDirection * SPEED; }
	
	public void setX(int x) { this.x = x; }
	
	public void setY(int y) { this.y = y; }
}

package gui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import game.Game;

public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private Thread thread;

	public GamePanel()
	{
		game = new Game();
		
		setFocusable(true);	
		setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));
		addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent event) { game.keyPressed(event); }

			@Override
			public void keyReleased(KeyEvent event) { game.keyReleased(event); }
		});

		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g)
	{
		Image image = createImage(getWidth(), getHeight());
		Graphics graphics = image.getGraphics();
		game.draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void run() 
	{
		//simple game loop
		long last = System.nanoTime();	
		double ticks = 60;
		double ns = 1000000000 / ticks;
		double delta = 0;

		while(true)
		{
			long now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;
			
			if(delta >= 1)
			{
				game.move();
				game.checkCollisions();
				repaint();
				delta--;
			}
		}
	}
}

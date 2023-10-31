package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Game
{
	private final int GAME_WIDTH = 1000;
	 //average ping pong tables have a 5 to 9 ratio between the width and the length
	private final int GAME_HEIGHT = (int) (GAME_WIDTH * (5.0 / 9));
	private final int BALL_RADIUS = 10;
	private final int PADDLE_WIDTH = 25;
	private final int PADDLE_HEIGHT = 100;

	private Paddle leftPaddle, rightPaddle;
	private Ball ball;
	private int leftPaddleScore, rightPaddleScore;
	
	public Game()
	{
		newBall();
		newPaddles();
		
		leftPaddleScore = 0;
		rightPaddleScore = 0;
	}
	
	private void newPaddles()
	{
		leftPaddle = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, true);
		rightPaddle = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, false);
	}
	
	private void newBall()
	{
		ball = new Ball((GAME_WIDTH / 2) - BALL_RADIUS, (GAME_HEIGHT / 2) - BALL_RADIUS, BALL_RADIUS);
	}
	
	public void checkCollisions()
	{
		//bounces ball off top and bottom edges
		if(ball.getY() <= 0 || ball.getY() >= GAME_HEIGHT - (2 * BALL_RADIUS))
			ball.inverseYVelocity();
		
		//bounce ball off of paddles
		else if(ball.intersects(leftPaddle) || ball.intersects(rightPaddle))
		{
			ball.inverseXVelocity();
			ball.incrementVelocity();
		}
		
		//gives the corresponding player a point if the ball goes past their paddle
		if(ball.getX() <= 0)
		{
			newPaddles();
			newBall();
			rightPaddleScore++;
		}
		else if(ball.getX() >= GAME_WIDTH - (2 * BALL_RADIUS))
		{
			newPaddles();
			newBall();
			leftPaddleScore++;
		}
		
		//stops paddles at top and bottom edges
		if(leftPaddle.getY() <= 0)
			leftPaddle.setY(0);
		else if(leftPaddle.getY() >= GAME_HEIGHT - PADDLE_HEIGHT)
			leftPaddle.setY(GAME_HEIGHT - PADDLE_HEIGHT);
		if(rightPaddle.getY() <= 0)
			rightPaddle.setY(0);
		else if(rightPaddle.getY() >= GAME_HEIGHT - PADDLE_HEIGHT)
			rightPaddle.setY(GAME_HEIGHT - PADDLE_HEIGHT);
	}
	
	public void keyPressed(KeyEvent event)
	{
		leftPaddle.keyPressed(event);
		rightPaddle.keyPressed(event);
	}
	
	public void keyReleased(KeyEvent event)
	{
		leftPaddle.keyReleased(event);
		rightPaddle.keyReleased(event);
	}
	
	public void draw(Graphics g)
	{
		leftPaddle.draw(g);
		rightPaddle.draw(g);
		ball.draw(g);
		drawScore(g);
		//line down the center of the panel
		g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);
	}
	
	//helper method to draw the two scores
	private void drawScore(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 60));
		g.drawString((leftPaddleScore / 10) + "" + (leftPaddleScore % 10), (GAME_WIDTH / 2) - 85, 50);
		g.drawString((rightPaddleScore / 10) + "" + (rightPaddleScore % 10), (GAME_WIDTH / 2) + 20, 50);
	}
	
	public void move()
	{
		leftPaddle.move();
		rightPaddle.move();
		ball.move();
	}
	
	public int getWidth() { return GAME_WIDTH; }
	public int getHeight() { return GAME_HEIGHT; }
}

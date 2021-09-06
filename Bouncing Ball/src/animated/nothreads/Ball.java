package animated.nothreads;

import static animated.nothreads.Constants.BALL_DIAMETER;
import static animated.nothreads.Constants.PANE_SIZE;
import static animated.nothreads.Constants.SLEEP_TIME;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ball extends JPanel {

	Color color;
	private int vx;
	private int vy;

	public Ball(Color color, int xvelocity, int yvelocity) {
		this.color = color;
		vx = xvelocity;
		vy = yvelocity;
		setSize(getPreferredSize());
		setLocation(new Random().nextInt(PANE_SIZE)-BALL_DIAMETER,
				new Random().nextInt(PANE_SIZE)-BALL_DIAMETER);
	}
	
	public void animate() {
		while (isVisible()) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
			}
			move();
			repaint();
		}
	}

	public void move() {

		int x = getX();
		int y = getY();

		if (x + vx < 0 && vx < 0 
				|| x + BALL_DIAMETER + vx > getParent().getWidth() && vx > 0) {
			vx *= -1;
		}
		if (y + vy < 0 && vy < 0 
				|| y + BALL_DIAMETER + vy > getParent().getHeight() && vy > 0) {
			vy *= -1;
		}
		x += vx;
		y += vy;

		setLocation(x, y);

	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BALL_DIAMETER, BALL_DIAMETER);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(color);
		g.fillOval(0, 0, BALL_DIAMETER-1, BALL_DIAMETER-1);
		g.setColor(Color.black);
		g.drawOval(0, 0, BALL_DIAMETER-1, BALL_DIAMETER-1);
	}

}


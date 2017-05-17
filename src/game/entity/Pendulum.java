package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import mainpkge.Handler;
import mainpkge.Id;

public class Pendulum extends Entity {
	private double pendulumAngle = Math.PI / 2;
	private double airResConstant = 0.003;
	private double velocityOfAngle = 0;
	private double accelerationOfAngle = 0;
	private double gravity = -9.8;
	private double velocityLoss = 0;

	private Color pendulumColor = new Color(225, 39, 39);

	private final int SIZE = 40;
	private final int RADIUS = SIZE / 2;
	private final int STRINGLENGTH = 260;

	private double dt = 0.1;

	private int xCoordinate;
	private int yCoordinate;
	private int xPendulum;
	private int yPendulum;

	public Pendulum(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {

		super(x, y, width, height, solid, id, handler);

		xCoordinate = x;
		yCoordinate = y;

	}

	public Rectangle getBoundsPendulum() {
		return new Rectangle(xPendulum - RADIUS, yPendulum - RADIUS, SIZE, SIZE);
	}

	@Override
	public void render(Graphics g) {

		// calculating the angle of which the pendulum is directed
		xPendulum = xCoordinate + (int) (Math.sin(pendulumAngle) * STRINGLENGTH);
		yPendulum = yCoordinate + (int) (Math.cos(pendulumAngle) * STRINGLENGTH);

		g.setColor(Color.BLACK);
		g.drawLine(xCoordinate, yCoordinate, xPendulum, yPendulum);

		g.setColor(pendulumColor);
		g.fillOval(xPendulum - RADIUS, yPendulum - RADIUS, SIZE, SIZE);
	}

	@Override
	public void tick() {
		
			velocityOfAngle -= velocityLoss;

			accelerationOfAngle = gravity / STRINGLENGTH * Math.sin(pendulumAngle);

			velocityOfAngle += accelerationOfAngle * dt;

			velocityLoss = velocityOfAngle * airResConstant;

			pendulumAngle += velocityOfAngle * dt;

			repaint();

			for (int i = 0; i < handler.entity.size(); i++) {
				Entity e = handler.entity.get(i);

				if (e.getId() == Id.player) {
					if (getBoundsPendulum().intersects(e.getBounds())) {
						e.die();
					}
				}
			}
	}

}

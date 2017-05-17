package game.entity;

import java.awt.Color;
import java.awt.Graphics;

import game.tile.Tile;
import mainpkge.Handler;
import mainpkge.Id;
import mainpkge.MainGame;

public class Ball extends Entity {

	public boolean ballOnGround = false;
	public double xfriction = 0.9, ballvX = 55, ballvY = 10, energyloss = 0.65;
	public boolean stop = false;
	public double airR = 0.003;
	public double lastVx, lastVy;

	public Ball(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(MainGame.ball.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {

		if (!stop) {
			/*
			 * if((Math.abs(ballvX) < 0.8 && (Math.abs(ballvY) < 0.8))){ ballvY
			 * = 0; y += ballvY * dt + 0.5 * gravity * dt * dt; ballvX = 0; x+=
			 * ballvX *dt; }
			 */
			ballvX -= lastVx * airR;
			ballvY -= lastVy * airR;
			ballvY += gravity * dt;
			y += ballvY * dt + 0.5 * gravity * dt * dt;
			x += ballvX * dt;

			for (int i = 0; i < handler.tile.size(); i++) {
				Tile t = handler.tile.get(i);
				if (t.isSolid()) {
					if (getBoundsTop().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
						y = t.getY() + t.width;
					}

					if (getBoundsBottom().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
						y = t.getY() - t.height;

						ballvX *= xfriction;
						ballvY *= energyloss;
						ballvY = -ballvY;
						System.out.println("hejhej");
						System.out.println(ballvY);
						System.out.println(ballvX);

						if (Math.abs(ballvX) < 0.2) {
							stop = true;
							ballvY = 0;
							ballvX = 0;
						}
					}
				}

				if (getBoundsLeft().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
					x = t.getX() + t.width;
					ballvX = -ballvX;
				}
				if (getBoundsRight().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
					x = t.getX() - t.width;
					ballvX = -ballvX;
				}
				
				

			}

		}
		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);

			if (e.getId() == Id.player) {
				if (getBounds().intersects(e.getBounds())) {
					e.die();
				}
			}
		}


		lastVx = ballvX;
		lastVy = ballvY;

	}

}

package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mainpkge.Handler;
import mainpkge.Id;
import mainpkge.MainGame;
import game.entity.Entity;
import game.tile.Tile;

public class Player extends Entity {

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public void render(Graphics g) {
		if (facing == 0) {
			g.drawImage(MainGame.player[1].getBufferedImage(), x, y, width, height, null);
		} else if (facing == 1) {
			g.drawImage(MainGame.player[0].getBufferedImage(), x, y, width, height, null);
		}
	}

	public void tick() {

		if (jumping) {
			y += vY;
			x += vX;
			vY += gravity * dt;
			vY += -vY * airR;
		} else {
			y += vY;
			x += vX;
			vY += gravity * dt;
		}
		if (falling) {
			if (vY < 10) {
				y += vY * dt + 0.5 * gravity * dt * dt;
				vY += gravity * dt;
			} else {
				y += vY * dt + 0.5 * gravity * dt * dt;
				vY = 10;
			}
		}
		// VAD SKA H�NDA OM JAG FALLER?

		// VAD SKA H�NDA OM JAG HOPPAR?

		// VAD SKA H�NDA OM JAG �R P� MARKEN? Jag kan g� h�ger, v�nster eller
		// hoppa!

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (t.isSolid()) {
				if (getBoundsTop().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
					setvY(0);
					y = t.getY() + t.width;

					// VAD SKA H�NDA OM JAG TR�FFAR TAKET?
				}

				if (getBoundsBottom().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
					setvY(0);
					y = t.getY() - t.width;
					// VAD SKA H�NDA OM JAG TR�FFAR BOTTEN?
					canJump = true;
					if (jumping)
						jumping = false;
					vX = 0;
				}
				if (getBoundsLeft().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
					setvX(0);
					x = t.getX() + t.width;
				}
				if (getBoundsRight().intersects(t.getBounds()) && t.getId() != Id.keys && t.getId() != Id.cage) {
					setvX(0);
					x = t.getX() - t.width;
				}

				if (getBounds().intersects(t.getBounds()) && t.getId() == Id.keys) {
					MainGame.key++;
					t.die();
				}

				if (getBounds().intersects(t.getBounds()) && t.getId() == Id.cage && MainGame.key >= 5) {
					MainGame.gameComplete = true;
					t.die();
				}

			}
		}

	}

}

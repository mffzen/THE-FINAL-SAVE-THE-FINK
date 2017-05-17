package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import mainpkge.Handler;
import mainpkge.Id;
import mainpkge.MainGame;
import game.entity.Entity;
import game.tile.Tile;

public class Homework extends Entity {

	private Random random = new Random();

	public int amplitude = 80;
	public int freq = 1;
	public int yBase = 0;
	public int dx = 4;
	private int xCoordinate, yCoordinate;

	public Homework(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	public Rectangle getBoundsDragon() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(MainGame.homework.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		
		x += dx;
		final int waveLength = width / freq;
		yBase = (++yBase) % waveLength;

		final double normalized = (double) yBase / (double) waveLength;
		final double rad = normalized * Math.PI * 2;
		final double sine = Math.sin(rad);

		y = (int) (sine * amplitude) + height * 6;

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);

			if (e.getId() == Id.player) {
				if (getBoundsDragon().intersects(e.getBounds())) {
					e.homeWork();
				}
			}
		}

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (t.isSolid()) {
				if (getBoundsLeft().intersects(t.getBounds())) {
					dx = -dx;
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					dx = -dx;
				}
			}
		}

	}
}

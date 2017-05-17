package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mainpkge.Id;
import mainpkge.MainGame;
import game.entity.Entity;

public class KeyInput implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (Entity en : MainGame.handler.entity) {
			if (en.getId() == Id.player) {
				switch (key) {
				case KeyEvent.VK_W:					
					if(en.canJump){
						if(en.leftPressed){
							en.vX = -10;
						}if(en.rightPressed){
							en.vX = 10;
						}
						en.jump();
					}
					break;
				case KeyEvent.VK_A:
					// VAD SKA HÄNDA OM JAG TRYCKER A?
					if(en.canJump){
						en.leftPressed = true;
					en.vX = -10;
					en.facing = 0;
					}
					break;
				case KeyEvent.VK_D:
					// VAD SKA HÄNDA OM JAG TRYCKER D
					if(en.canJump){
				
						en.rightPressed = true;
						en.vX = 10;
						en.facing = 1;
					}
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (Entity en : MainGame.handler.entity) {
			if (en.getId() == Id.player) {
				switch (key) {
				
				case KeyEvent.VK_A:
					// VAD SKA HÄNDA OM JAG SLÄPPER A?
					en.leftPressed = false;
					en.vX = 0;
					break;
				case KeyEvent.VK_D:
					// VAD SKA HÄNDA OM JAG SLÄPPER D?
					en.rightPressed = false;
					en.vX = 0;
					break;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// används inte
	}

}

package DynamicObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import GameSetUp.Handler;
import Resources.Images;

public class Shop extends GameObject{

	BufferedImage shopImage;
	private boolean debug = false;

	public Shop(int x, int y, int width, int height) {
		super(x,y,width,height);
		shopImage = Images.get("MapSprite").getSubimage(202, 546, 64, 66);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(shopImage,x,y,width,height,null);	
		if(debug) {
			g.drawRect(x, y, width, height);
		}
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(shopImage,x,y,width,height,null);	
		if(debug) {
			g.drawRect(x, y, width, height);
		}
	}

	@Override
	public void tick() {	
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)) {
			debug = !debug;
		}
	}

}

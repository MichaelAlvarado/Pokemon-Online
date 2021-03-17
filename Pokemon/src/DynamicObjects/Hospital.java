package DynamicObjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import GameSetUp.Handler;
import Resources.Images;

public class Hospital extends GameObject{
	
	BufferedImage hospitalImage;
	private boolean debug = false;
	
	public Hospital(int x, int y, int width, int height) {
		super(x,y,width,height);
		 hospitalImage = Images.get("MapSprite").getSubimage(271, 529, 82, 83);
	}

	@Override
	public void render(Graphics arg0) {
		arg0.drawImage(hospitalImage,x,y,width,height,null);	
		if(debug) {
			arg0.drawRect(x, y, width, height);
		}
	}
	
	@Override
	public void render(Graphics arg0, int x, int y) {
		arg0.drawImage(hospitalImage,x,y,width,height,null);	
		if(debug) {
			arg0.drawRect(x, y, width, height);
		}
	}

	@Override
	public void tick() {
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)) {
			debug = !debug;
		}
	}
	

}

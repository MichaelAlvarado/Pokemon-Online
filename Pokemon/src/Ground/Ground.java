package Ground;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameObject.GameObject;

public class Ground extends GameObject{
	BufferedImage image;
	
	public Ground(int x, int y, int width,int height) {
		super(x,y,width,height);
	}

	@Override
	public void render(Graphics arg0) {
		arg0.drawImage(image,x,y,width,height,null);				
	}
	
	@Override
	public void render(Graphics arg0, int x, int y) {
		arg0.drawImage(image,x,y,width,height,null);				
	}

	@Override
	public void tick() {
		
	}

}

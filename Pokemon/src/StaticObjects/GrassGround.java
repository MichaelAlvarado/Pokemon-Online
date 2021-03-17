package StaticObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Resources.Images;

public class GrassGround extends GameObject{
	
	BufferedImage grassImage;
	
	public GrassGround(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.grassImage = Images.get("MapSprite").getSubimage(1, 153, 16, 15);
	}
	
	@Override
	public void render(Graphics arg0) {
		arg0.drawImage(grassImage,x,y,width,height,null);		
	}
	
	@Override
	public void render(Graphics arg0, int x, int y) {
		arg0.drawImage(grassImage,x,y,width,height,null);		
	}

	@Override
	public void tick() {
	}

}

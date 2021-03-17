package StaticObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Resources.Images;

public class DirtGround extends GameObject{
	
	BufferedImage dirtImage;
	
	public DirtGround(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.dirtImage = Images.get("MapSprite").getSubimage(79, 272, 16, 16);
	}
	
	@Override
	public void render(Graphics arg0) {
		arg0.drawImage(dirtImage,x,y,width,height,null);		
	}
	
	@Override
	public void render(Graphics arg0, int x, int y) {
		arg0.drawImage(dirtImage,x,y,width,height,null);		
	}

	@Override
	public void tick() {
	}

}

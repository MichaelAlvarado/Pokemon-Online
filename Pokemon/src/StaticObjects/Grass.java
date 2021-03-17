package StaticObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Resources.Images;

public class Grass extends GameObject{
	
	BufferedImage grassImage;
	
	public Grass(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.grassImage = Images.get("MapSprite").getSubimage(35, 170, 16, 16);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(grassImage,x,y,width,height,null);		
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(grassImage,x,y,width,height,null);		
	}
	@Override
	public void tick() {
	}

}

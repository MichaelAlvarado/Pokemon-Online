package StaticObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameObject.GameObject;
import Resources.Images;

public class Tree extends GameObject{
	
	BufferedImage treeImage;
	
	public Tree(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.treeImage = Images.get("MapSprite").getSubimage(133, 116, 16, 35);
	}
	
	@Override
	public void render(Graphics arg0) {
		arg0.drawImage(treeImage,x,y,width,height,null);		
	}
	
	@Override
	public void render(Graphics arg0, int x, int y) {
		arg0.drawImage(treeImage,x,y,width,height,null);		
	}

	@Override
	public void tick() {
	}

}

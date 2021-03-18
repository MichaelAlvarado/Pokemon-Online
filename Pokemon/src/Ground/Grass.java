package Ground;

import Resources.Images;

public class Grass extends Ground{
		
	public Grass(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.image = Images.get("MapSprite").getSubimage(35, 170, 16, 16);
	}

}

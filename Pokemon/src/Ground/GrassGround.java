package Ground;

import Resources.Images;

public class GrassGround extends Ground{
		
	public GrassGround(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.image = Images.get("MapSprite").getSubimage(1, 153, 16, 15);
	}

}

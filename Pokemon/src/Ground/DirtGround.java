package Ground;

import Resources.Images;

public class DirtGround extends Ground{
	
	
	public DirtGround(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.image = Images.get("MapSprite").getSubimage(79, 272, 16, 16);
	}

}

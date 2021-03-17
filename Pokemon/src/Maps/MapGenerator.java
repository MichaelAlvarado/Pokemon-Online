package Maps;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Camera.Camera;
import GameObject.GameObject;
import DynamicObjects.*;
import StaticObjects.*;

public class MapGenerator {
	
	public static int pixelMultiplier = 48; 							//Scale Factor
	
	//Colors
	public static int playerSpawn = new Color(0,0,0).getRGB();			//Black
	public static int hospital = new Color(255,0,0).getRGB();			//Red
	public static int shop = new Color(0,0,255).getRGB();				//Blue
	public static int tree = new Color(0,255,0).getRGB();				//Green
	public static int grass = new Color(34,177,76).getRGB();				//Dark Green
	//Ground Colors
	//public static int grassGround = new Color(0,0,0).getRGB();
	public static int dirtGround = new Color(185,122,87).getRGB();

	// Verify each pixel of the map to spawn corresponding gameobject
	public static Map createMap(BufferedImage mapImage){
		Map mapInCreation = new Map();
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				
				if(currentPixel == playerSpawn){
					GameObject grass = new GrassGround(xPos,yPos,pixelMultiplier,Math.round(pixelMultiplier*1.2f));
					mapInCreation.addStaticObject(grass);
					mapInCreation.player = new Player(xPos,yPos,pixelMultiplier,pixelMultiplier);
					//mapInCreation.addDynamicObject(player);
				}
				else if(currentPixel == tree) {
					GameObject grass = new GrassGround(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addStaticObject(grass);
					GameObject tree = new Tree(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addStaticObject(tree);
				}
				else if(currentPixel == hospital) {
					GameObject grass = new GrassGround(xPos,yPos,pixelMultiplier*3,pixelMultiplier*3);
					mapInCreation.addStaticObject(grass);
					GameObject hospital = new Hospital(xPos,yPos,pixelMultiplier*3,pixelMultiplier*3);
					mapInCreation.addDynamicObject(hospital);
				}
				else if(currentPixel == shop) {
					GameObject grass = new GrassGround(xPos,yPos,pixelMultiplier*3,pixelMultiplier*3);
					mapInCreation.addStaticObject(grass);
					GameObject shop = new Shop(xPos,yPos,pixelMultiplier*3,pixelMultiplier*3);
					mapInCreation.addDynamicObject(shop);
				}
				else if (currentPixel == grass) {
					GameObject grass = new Grass(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addStaticObject(grass);
				}
				//Grounds
				else if (currentPixel == dirtGround) {
					GameObject ground = new DirtGround(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addStaticObject(ground);
				}
				else {
					GameObject grass = new GrassGround(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addStaticObject(grass);
				}

			}
		}//End of pixels loop
		mapInCreation.camera = new Camera(mapInCreation, mapInCreation.player);
		return mapInCreation;
	}
	
}

package Maps;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Camera.Camera;
import GameObject.GameObject;
import Ground.DirtGround;
import Ground.Grass;
import Ground.GrassGround;
import Ground.Ground;
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
					mapInCreation.player = new Player(xPos,yPos,pixelMultiplier,pixelMultiplier);
					//mapInCreation.addDynamicObject(player);
				}
				else if(currentPixel == tree) {
					GameObject tree = new Tree(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addStaticObject(tree);
				}
				else if(currentPixel == hospital) {
					GameObject hospital = new Hospital(xPos,yPos,pixelMultiplier*3,pixelMultiplier*3);
					mapInCreation.addDynamicObject(hospital);
				}
				else if(currentPixel == shop) {
					GameObject shop = new Shop(xPos,yPos,pixelMultiplier*3,pixelMultiplier*3);
					mapInCreation.addDynamicObject(shop);
				}

			}
		}//End of pixels loop
		MapTerrain(mapImage, mapInCreation);
		mapInCreation.camera = new Camera(mapInCreation, mapInCreation.player);
		return mapInCreation;
	}
	
	//Grounds
	public static void MapTerrain(BufferedImage terrain, Map mapInCreation) {
		for (int i = 0; i < terrain.getWidth(); i++) {
			for (int j = 0; j < terrain.getHeight(); j++) {
				int currentPixel = terrain.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				
				//Grounds
				if (currentPixel == dirtGround) {
					Ground ground = new DirtGround(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addGround(ground);
				}
				else if (currentPixel == grass) {
					Ground grass = new Grass(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addGround(grass);
				}
				else {
					Ground grass = new GrassGround(xPos,yPos,pixelMultiplier,pixelMultiplier);
					mapInCreation.addGround(grass);
				}

			}
		}//End of pixels loop
	}
	
}

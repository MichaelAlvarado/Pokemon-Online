package Maps;

import java.awt.Graphics;

import Resources.Images;
import Scene.Scene;

public class FirstMap implements Scene{

	public Map map;
	
	public FirstMap() {
		loadAssets();
	}
	
	//Loads all assets content in game (images, sound, etc)
	public void loadAssets() {
		//Load all images
		Images.LoadImage("Map", "res/Map.png");
		Images.LoadImage("MapSprite", "res/MapSprite.png");
		Images.LoadImage("Player", "res/MalePlayer.png");

		//Create Map
		map = MapGenerator.createMap(Images.get("Map"));
	}
	
	//This Class renders the graphics (60 FPS) 
	@Override
	public void render(Graphics arg0) {
		map.render(arg0);
	}

	//This physics and objects actions of the Game (60 FPS)
	@Override
	public void tick() {
		map.tick();
	}

}

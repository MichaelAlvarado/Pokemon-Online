package Camera;

import java.awt.Graphics;

import GameObject.GameObject;
import GameSetUp.Handler;
import Maps.Map;

public class Camera {
	
	GameObject focalPoint;
	Map map;
	int width, height;
	
	public Camera(Map map, GameObject focalPoint) {
		this.focalPoint = focalPoint;
		this.map = map;
	}
	
	public void render(Graphics g){
		this.width = Handler.getWidth();
		this.height = Handler.getHeight();
		int x = this.focalPoint.x - (width/2); //Camera left
		int y = this.focalPoint.y - (height/2); //Camera Up
		
		for(GameObject o : map.staticObjects) {
			o.render(g, o.x - x, o.y - y);
		}
		
		for(GameObject o : map.dynamicObjects) {
			o.render(g, o.x - x, o.y - y);
		}
		focalPoint.render(g, focalPoint.x - x, focalPoint.y - y);
	}
}


//this.width = Handler.getWidth();
//this.height = Handler.getHeight();
//int x = this.focalPoint.x - (width/2); //Camera left
//int y = this.focalPoint.y - (height/2); //Camera Up
//
//for(GameObject o : map.staticObjects) {
//	int x1 = o.x - focalPoint.x; //X distance from focalPoint
//	int y1 = o.y - focalPoint.y; //Y distance from focalPoint
//	o.x = o.x + x1; //New position x in respect of Camera
//	o.y = o.y + y1; //New position y in respect of Camera
//}
//
//for(GameObject o : map.dynamicObjects) {
//	int x1 = o.x - focalPoint.x; //X distance from focalPoint
//	int y1 = o.y - focalPoint.y; //Y distance from focalPoint
//	o.x = o.x + x1; //New position x in respect of Camera
//	o.y = o.y + y1; //New position y in respect of Camera
//}
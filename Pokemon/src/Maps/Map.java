package Maps;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Camera.Camera;
import DynamicObjects.Player;
import GameObject.GameObject;
import GameSetUp.Handler;
import Online.Client;
import Online.Server;

public class Map {

	public List<GameObject> staticObjects;
	public List<GameObject> dynamicObjects;
	public Player player;
	public Camera camera; //Testing
	private boolean ServerOn = false;
	private boolean ClientOn = false;

	public Map () {
		staticObjects = new ArrayList<GameObject>();
		dynamicObjects = new ArrayList<GameObject>();
	}

	public Player addOnlinePlayer() {
		Player playerOnline = new Player(-100,-100,player.width,player.height);
		this.addDynamicObject(playerOnline);
		return playerOnline;
	}
	
	public void addStaticObject(GameObject object) {
		staticObjects.add(object);
	}

	public void addDynamicObject(GameObject object) {
		dynamicObjects.add(object);
	}

	public void render(Graphics g) {
		camera.render(g);
		//		for(GameObject o : staticObjects) {
		//			o.render(g);
		//		}
		//		for(GameObject o : dynamicObjects) {
		//			o.render(g);
		//		}
		//		player.render(g);
	}

	public void tick() {
		player.tick();
		for(GameObject o : staticObjects) {
			o.tick();
		}
		for(GameObject o : dynamicObjects) {
			if(!(o instanceof Player)) {
				o.tick();
				if(player.collision(o)) player.ySpeed = 0;
			}
		}
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_0) && !ClientOn && !ServerOn) {
			this.ServerOn = true;
			openServer(this.addOnlinePlayer(), this);
		}
		else if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_9) && !ServerOn && !ClientOn) {
			this.ClientOn = true;
			openClient(this.addOnlinePlayer(), this);
		}

	}

	public void openClient(Player player, Map map) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Loading Client");
					long start = System.nanoTime();
					Client client = new Client(player, map);
					client.start();
					System.out.println("Client Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void openServer(Player player, Map map) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Loading Server");
					long start = System.nanoTime();
					Server server = new Server(player, map);
					server.start();
					System.out.println("Server Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

}

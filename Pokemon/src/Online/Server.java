package Online;
import java.io.*;  
import java.net.*;
import java.util.List;

import DynamicObjects.Player;  

/**
 * @author Michael J. Alvarado
 * Date - 16/March/2021
 */
public class Server implements Runnable {  

	private static boolean running = false;
	private Thread thread;

	Player player; //New Client Player

	Socket s;
	ServerSocket ss;
	final static int port = 6666;


	public Server(Player player) {
		runServer(player);
	}

	public void runServer(Player player){  
		this.player = player;
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();		//this runs the run method in this  class
	}

	@Override
	public void run() {
		while(running) {
			ReceivePlayer();
		}
	}

	public void ReceivePlayer() {
		try{  
			ss = new ServerSocket(port);  
			s=ss.accept();//establishes connection   
			DataInputStream dis=new DataInputStream(s.getInputStream());  
			while(running) {
				String  str=(String)dis.readUTF();  
				System.out.println("message= "+str); 
				String[] data = str.split(",");
				player.x = Integer.valueOf(data[0]);
				player.y = Integer.valueOf(data[1]);
				player.direction = Player.facing.valueOf(data[2]);
				player.moving = Boolean.valueOf(data[3]);
			}
			ss.close();  

		}catch(Exception e){
			System.out.println("SERVER: No client found");
			e.printStackTrace();
		}  
	}

}  
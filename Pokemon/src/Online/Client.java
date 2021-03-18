package Online;
import java.io.*;  
import java.net.*;

import DynamicObjects.Player;
import Maps.Map;  

/**
 * @author Michael J. Alvarado
 * Date - 16/March/2021
 */
public class Client implements Runnable{  

	private static boolean running = false;
	private Thread thread;

	Player player; //Newly create player
	Map map;
	
	Socket s;
	final static String ipAddress = "localhost";
	final static int port = 6666;

	public Client(Player player, Map map) {
		this.player = player; 
		this.map = map;
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
			SendPlayer();
		}
	}

	public void SendPlayer(){
		try{    
			s = new Socket(ipAddress,port);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			DataInputStream dis=new DataInputStream(s.getInputStream());  
			while(running) {
				//Write
				dout.writeUTF(map.player.x+","+map.player.y+","+map.player.direction+","+map.player.moving);  
				//				dout.flush(); 
				//Read
				String  str=(String)dis.readUTF();  
				System.out.println("Client message= "+str); 
				String[] data = str.split(",");
				player.x = Integer.valueOf(data[0]);
				player.y = Integer.valueOf(data[1]);
				player.direction = Player.facing.valueOf(data[2]);
				player.moving = Boolean.valueOf(data[3]);
			}
			dout.close();  
			s.close(); 
		}catch(Exception e){
			System.out.println("Error Client"); start();
			e.printStackTrace();
		} 
	}


} 
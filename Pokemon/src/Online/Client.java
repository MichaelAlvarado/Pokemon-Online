package Online;
import java.io.*;  
import java.net.*;

import DynamicObjects.Player;  

/**
 * @author Michael J. Alvarado
 * Date - 16/March/2021
 */
public class Client implements Runnable{  

	private static boolean running = false;
	private Thread thread;

	Player player; 

	Socket s;
	final static int port = 6666;

	public Client(Player player) {
		runClient(player);
	}

	public void runClient(Player player) {  
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
			SendPlayer();
		}
	}

	public void SendPlayer(){
		try{    
			s = new Socket("localhost",port);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			while(running) {
				dout.writeUTF(player.x+","+player.y+","+player.direction+","+player.moving);  
				dout.flush(); 
			}
			dout.close();  
			s.close(); 
		}catch(Exception e){
			System.out.println("Error Client"); start();
			e.printStackTrace();
		} 
	}


} 
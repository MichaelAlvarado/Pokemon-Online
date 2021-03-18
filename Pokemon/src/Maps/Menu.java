package Maps;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import GameSetUp.Handler;
import Resources.Images;
import Resources.SoundManager;
import Scene.Scene;
import UI.Button;

public class Menu implements Scene{

	Button startButton, optionButton;
	Image icon;

	public Menu() {
		Images.LoadImage("Background Sky", "res/Background Sky.jpg");
		Images.LoadImage("Pokemon Logo", "res/Pokemon Logo.png");
		Images.LoadImage("Start", "res/buttonPlay.png");
		Images.LoadImage("Option", "res/buttonOption.png");
		Images.LoadImage("Charizard", "res/Charizard.png");
		SoundManager.addAudio("Select");
		startButton = new Button(0,0,250,120,Images.get("Start")) {
			public void onClick() {
				SoundManager.startAudio("Select");
				Handler.setCurrentScene(new FirstMap());
			}
		};
		optionButton = new Button(0,0,250,120,Images.get("Option")) {
			public void onClick() {
				System.out.println("Option works");			
			}
		};
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Images.get("Background Sky"), 0, 0, Handler.getWidth(), Handler.getHeight(), null);
		g.drawImage(Images.get("Charizard"), 10, Handler.getHeight()/3, Handler.getWidth()/4, Handler.getHeight()/2, null);
		g.drawImage(Images.get("Pokemon Logo"), 10, 15, Handler.getWidth()-20, Handler.getHeight()/4, null);
		updateButtonLocation();
		startButton.render(g);
		optionButton.render(g);
	}

	@Override
	public void tick() {
		startButton.tick();
		optionButton.tick();
	}
	
	//Use to scale the buttons with the window
	private void updateButtonLocation() {
		startButton.x = (Handler.getWidth()/2)-(startButton.width/2);
		startButton.y = (Handler.getHeight()/2)-(startButton.height/2);
		optionButton.x = (Handler.getWidth()/2)-(optionButton.width/2);
		optionButton.y = (Handler.getHeight()/2)+(optionButton.height);
	}

}

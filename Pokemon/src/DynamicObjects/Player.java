package DynamicObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import GameObject.GameObject;
import GameSetUp.Handler;
import Resources.Animation;
import Resources.Sprite;

/**
 * 
 * @author Michael J. Alvarado
 * @date Jan 12, 2021
 * Sample player
 *
 */
public class Player extends GameObject{

	//Physics Related
	public enum facing{
		Up, Down, Left, Rigth
	}
	public String name;
	public facing direction = facing.Down;
	public int xSpeed = 0, ySpeed = 0, walkingSpeed = 4;
	public boolean moving = false;
	private boolean collided = false;
	private Rectangle bound; 

	private boolean debug = false;

	//Animation Related
	public Animation animationR, animationL, animationU, animationD;


	public Player(int x, int y, int width, int height) {
		super(x,y,width,height);
		this.bound = new Rectangle(x,y,width, height);

		//Make Sprite
		Sprite d = new Sprite("res/MalePlayer.png", 12, 10, 36, 52, 255, 29);
		Sprite l = new Sprite("res/MalePlayer.png", 12, 76, 36, 52, 255, 29);
		Sprite r = new Sprite("res/MalePlayer.png", 12, 141, 36, 52, 255, 29);
		Sprite u = new Sprite("res/MalePlayer.png", 12, 203, 36, 52, 255, 29);

		//Make Animation
		this.animationR = new Animation(r.images,0.2);
		this.animationL = new Animation(l.images,0.2);
		this.animationU = new Animation(u.images,0.2);
		this.animationD = new Animation(d.images,0.2);
	}

	public Rectangle getBound() {return bound;}

	@Override
	public void tick() {
		if(Handler.getKeyManager().up) {
			direction = facing.Up;
			moving = true;
			ySpeed = -walkingSpeed;
			xSpeed = 0;
			animate().startAnimation();
		}
		else if(Handler.getKeyManager().down) {
			direction = facing.Down;
			moving = true;
			ySpeed = walkingSpeed;
			xSpeed = 0;
			animate().startAnimation();
		}
		else if(Handler.getKeyManager().left) {
			direction = facing.Left;
			moving = true;
			xSpeed = -walkingSpeed;
			ySpeed = 0;
			animate().startAnimation();
		}
		else if(Handler.getKeyManager().right) {
			direction = facing.Rigth;
			moving = true;
			xSpeed = walkingSpeed;
			ySpeed = 0;
			animate().startAnimation();
		}
		else {
			xSpeed = 0;
			ySpeed = 0;
			moving = false;
		}
		this.x += xSpeed;
		this.y += ySpeed;

		updateBound();

		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)) {
			debug = !debug;
		}
	}

	@Override
	public void render(Graphics g) {
		Animation animate = animate();
		if(moving) {
			animate.startAnimation();
		}
		else {
			animate.stopAnimation();
		}
		animate.renderWithIdle(g);

		if(debug) {
			g.drawRect(x, y, width, height);
		}
		if(this.name != null) {
			g.setColor(Color.WHITE);
			g.drawString(name, this.x, this.y);
		}
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		Animation animate = animate();
		if(moving) {
			animate.startAnimation();
		}
		else {
			animate.stopAnimation();
		}
		animate.renderWithIdle(g);

		if(debug) {
			g.drawRect(x, y, width, height);
		}
		updateAnimationPosition(x,y);	
		if(this.name != null) {
			g.setColor(Color.WHITE);
			g.drawString(name, this.x, this.y);
		}
	}

	//returns the animation that should be playing in the current time
	private Animation animate() {
		switch(direction) {
		case Down:
			return animationD;

		case Up:
			return animationU;

		case Left:
			return animationL;

		case Rigth:
			return animationR;

		default:
			return animationU;
		}
	}

	//Make sure all animation positions are in the players position
	private void updateAnimationPosition(int x, int y) {
		animationL.setBound(x, y, width, height);
		animationR.setBound(x, y, width, height);
		animationU.setBound(x, y, width, height);
		animationD.setBound(x, y, width, height);
	}

	private void updateBound() {
		this.bound.setBounds(x, y, width, height);;
	}

	//Currently not used (dont work with Camera)
	private void boundStop() {
		if(x<0) {
			x = 0;
		}
		if(x>Handler.getWidth()) {
			x = Handler.getWidth();
		}
		if(y<0) {
			y = 0;
		}
		if(y>Handler.getHeight()) {
			y = Handler.getHeight();
		}
	}

	//Use to verify if there is a collision with the given object
	public boolean collision(GameObject object) {
		collided = this.bound.intersects(object.x, object.y, object.width, object.height);
		if(collided) {
			pushBack(walkingSpeed);
			moving = false;
		}

		return collided;
	}

	private void pushBack(int force) {
		switch(direction) {
		case Down:
			y -= force;
			break;
		case Up:
			y += force;
			break;
		case Left:
			x += force;
			break;
		case Rigth:
			x -= force;
			break;
		default:
			break;
		}
	}

}

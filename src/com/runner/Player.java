package com.runner;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Player {
	private float y;
	private float yVelocity;
	private Graphics graphics;
	private int windowHeight;
	private int windowWidth;
	private boolean isJumping;
	
	public final float SIZE = 50;
	//The default absolute X position
	public final float X_POSITION = 50;
	public final int COLOR  = 0xff42ff;
	public final float ACCELERATION = .0014f;
	public final float INITIAL_VELOCITY = -0.8f;
	
	public Player(Graphics graphics, int windowWidth, int windowHeight) {
		this.graphics = graphics;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		y = windowHeight*.75f - SIZE;
	}
	
	public void render() {
		//TODO: Create a real image
		Rectangle rect = new Rectangle(X_POSITION, y, SIZE, SIZE);
		graphics.setColor(new Color(COLOR));
		graphics.fill(rect);
		
	}
	
	public void update(int delta) {
		//TODO: Fiddle with magic numbers to make a better jumping animation
		if(isJumping){
			yVelocity += ACCELERATION*delta;
			y+=yVelocity*delta;
			if(y > windowHeight * .75f - SIZE){
				isJumping = false;
				y = windowHeight * .75f - SIZE;
			}
		}
	}
	
	public void jump() {
		if(!isJumping){
			yVelocity = INITIAL_VELOCITY;
			isJumping = true;
		}
	}
	
	public float getY(){
		return y;
	}
}

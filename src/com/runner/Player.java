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
	
	public Player(Graphics graphics, int windowWidth, int windowHeight) {
		this.graphics = graphics;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		y = 0;
	}
	
	public void render() {
		//TODO: Create a real image
		Rectangle rect = new Rectangle(80, windowHeight*.75f - y+ -50, 50, 50);
		graphics.setColor(new Color(0xff42ff));
		graphics.fill(rect);
		
	}
	
	public void update(int delta) {
		//TODO: Fiddle with magic numbers to make a better jumping animation
		if(isJumping){
			yVelocity -= .0008*delta;
			y+=yVelocity;
			if(y < 0){
				isJumping = false;
				y = 0;
			}
		}
	}
	
	public void jump() {
		if(!isJumping){
			yVelocity = 0.6f;
			isJumping = true;
		}
	}
	
	public float getY(){
		return windowHeight*.75f - y;
	}
}

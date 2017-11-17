package com.runner;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Obstacle {

	private float x;
	private Graphics graphics;
	private int windowHeight;
	private int windowWidth;
	private int width;
	private int height;
	
	public final float SPEED = .2f;
	public final int COLOR = 0xe9ceed;

	public Obstacle(Graphics graphics, int windowWidth, int windowHeight, int width, int height) {
		this.graphics = graphics;
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		x = windowWidth-width;
		this.width = width;
		this.height = height;
	}
	
	public Obstacle(Graphics graphics, int windowWidth, int windowHeight) {
		this(graphics, windowWidth, windowHeight, (int)(Math.random() * 40 +20),(int) (Math.random() * 80 + 20));
	}

	public void render() {
		//System.out.println("X : "+x + "Width: " + width + "height" +height);
		Rectangle rect = new Rectangle(x, windowHeight * .75f -height, width, height);
		graphics.setColor(new Color(COLOR));
		graphics.fill(rect);
	}

	public void update(int delta) {
		x-= SPEED * delta;  
	}
	
	public boolean isOffScreen(){
		return x < -width;
	}
	
	public boolean isTouching(float otherX, float otherY, float otherWidth, float otherHeight) {
		return otherX + otherWidth >= x && otherX <= x + width &&
				otherY<= windowHeight*.75f && otherY + otherHeight >= windowHeight *.75f - height;
	}
}

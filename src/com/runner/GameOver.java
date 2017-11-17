package com.runner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {
	public static final int ID = 1;
	//time elapsed in milliseconds
	private int timeElapsed;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		timeElapsed = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("GAME OVER", gc.getWidth()/2-40, gc.getHeight()/2-7);
		g.drawString("Press Space to play again", gc.getWidth()/2-115, gc.getHeight()/2+7);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timeElapsed+=delta;
		if(timeElapsed > 10000)
			sbg.enterState(0);
		if(gc.getInput().isKeyDown(Input.KEY_SPACE)){
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		return GameOver.ID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		
		timeElapsed  = 0;
	}
}

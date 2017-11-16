package com.runner;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;

public class Game extends BasicGameState {
	public static final int ID = 0;
	private Player player;
	private List<Obstacle> obstacles;
	private int score;

	public Game() {
		super();
		System.out.println("Constructor");
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		setupNewGame(gc, sbg);
	}

	public void update(GameContainer gc, StateBasedGame arg1, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
			player.jump();
		}
		player.update(delta);
		for (int j = obstacles.size() - 1; j >= 0; j--) {
			obstacles.get(j).update(delta);
			if (obstacles.get(j).isOffScreen()) {
				obstacles.remove(j);
				score++;
			}
		}
		// TODO: Create a better system to generate obstacles
		if (Math.random() < .0006) {
			obstacles.add(new Obstacle(gc.getGraphics(), gc.getWidth(), gc.getHeight()));
		}
	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(new Color(0x444043));
		Rectangle rect = new Rectangle(0f, .75f * gc.getHeight(), gc.getWidth(), .25f * gc.getHeight());
		g.fill(rect);
		player.render();
		for (Obstacle o : obstacles) {
			o.render();
			if (o.isTouching(player.X_POSITION, player.getY(), player.SIZE, player.SIZE))
				endGame(gc, arg1);
		}
		g.setColor(new Color(0xff00bf));
		g.drawString("Score: " + score, gc.getWidth() - 100, 3);
	}

	/*
	 * public static void main(String[] args) { try{ AppGameContainer appgc;
	 * appgc = new AppGameContainer(new Game("Runner"));
	 * appgc.setDisplayMode(640, 480, false); appgc.start(); } catch
	 * (SlickException ex){
	 * Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex); } }
	 */

	public void endGame(GameContainer gc, StateBasedGame sbg) {
		System.out.println("Should end");
		sbg.enterState(1);
	}

	public void setupNewGame(GameContainer gc, StateBasedGame sbg) {
		gc.getGraphics().clear();
		gc.getGraphics().setBackground(new Color(0x3d0560));
		player = new Player(gc.getGraphics(), gc.getWidth(), gc.getHeight());
		obstacles = new ArrayList<Obstacle>();
		score = 0;
	}

	@Override
	public int getID() {
		return Game.ID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg){
		setupNewGame(gc, sbg);
	}

}
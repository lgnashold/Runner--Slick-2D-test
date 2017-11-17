package com.runner;

import java.util.*;
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
	private Score score;
	private int timeUntilNextObstacle;

	//Declaring Constant values
	private final static String SCORE_FILE = "scores.txt";
	//TODO: Think of better names
	private final int MIN_TIME_BETWEEN_OBSTACLES = 1000;
	private final int MAX_TIME_BETWEEN_OBSTACLES = 2000;
	
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
				score.increment();
				score.updateHighScore();
			}
		}
		timeUntilNextObstacle -= delta;
		if(timeUntilNextObstacle <= 0){
			obstacles.add(new Obstacle(gc.getGraphics(), gc.getWidth(), gc.getHeight()));
			timeUntilNextObstacle = (int)(Math.random() * MIN_TIME_BETWEEN_OBSTACLES + MAX_TIME_BETWEEN_OBSTACLES - MIN_TIME_BETWEEN_OBSTACLES);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(new Color(0x444043));
		Rectangle rect = new Rectangle(0f, .75f * gc.getHeight(), gc.getWidth(), .25f * gc.getHeight());
		g.fill(rect);
		player.render();
		for (Obstacle o : obstacles) {
			o.render();
			if (o.isTouching(player.X_POSITION, player.getY(), player.SIZE, player.SIZE))
				endGame(gc, sbg);
		}
		score.render(gc, g);
	}

	public void endGame(GameContainer gc, StateBasedGame sbg) {
		System.out.println("Game Ended");
		score.saveScore(SCORE_FILE);
		sbg.enterState(1);
	}

	public void setupNewGame(GameContainer gc, StateBasedGame sbg) {
		score = Score.loadScore(SCORE_FILE);
		gc.getGraphics().clear();
		gc.getGraphics().setBackground(new Color(0x3d0560));
		player = new Player(gc.getGraphics(), gc.getWidth(), gc.getHeight());
		obstacles = new ArrayList<Obstacle>();
		score.reset();
		timeUntilNextObstacle = 0;
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
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
import org.newdawn.slick.*;

public class Main extends BasicGame
{
	private Player player;
	private List<Obstacle> obstacles;
	private int score;
	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getGraphics().setBackground(new Color(0x3d0560));
		player = new Player(gc.getGraphics(), gc.getWidth(), gc.getHeight());
		obstacles = new ArrayList<Obstacle>();
		score = 0;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(gc.getInput().isKeyDown(Input.KEY_SPACE)){
			player.jump();
		}
		player.update(delta);
		for(int j=obstacles.size()-1;j>=0;j--){
			obstacles.get(j).update(delta);
			if(obstacles.get(j).isOffScreen()) {
				obstacles.remove(j);
				score ++;
			}
		}
		//TODO: Create a better system to generate obstacles
		if(Math.random()<.0006){
			obstacles.add( new Obstacle(gc.getGraphics(), gc.getWidth(), gc.getHeight()));
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.setColor(new Color(0x444043));
		Rectangle rect = new Rectangle(0f, .75f*gc.getHeight(), gc.getWidth(), .25f*gc.getHeight());		
		g.fill(rect);		
		player.render();
		for(Obstacle o : obstacles){
			o.render();
		}
		g.setColor(new Color(0xff00bf));
		g.drawString("Score: " + score, gc.getWidth() - 100, 3);
	}

	public static void main(String[] args)
	{
		try{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Runner"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex){
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
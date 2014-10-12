package ld26.phased.screen;

import ld26.phased.Game;
import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.input.Input;
import ld26.phased.util.Button;
import ld26.phased.util.Settings;
import ld26.phased.world.World;

public class GameScreen extends Screen{

	private SpriteBatcher spriteBatcher;
	private Game game;
	
	private Button play, quit, options;
	
	private World world;
	
	public GameScreen(SpriteBatcher spriteBatcher, Game game){
		this.spriteBatcher = spriteBatcher;
		this.game = game;
		world = new World(spriteBatcher, game);
	}
	
	public GameScreen(SpriteBatcher spriteBatcher, Game game, boolean continueGame){
		this.spriteBatcher = spriteBatcher;
		this.game = game;
		world = new World(spriteBatcher, game, Settings.levelSave);
	}
	
	public void render() {
		spriteBatcher.begin();
		
		world.render();
		
		spriteBatcher.end();
	}

	public void update() {
		world.update();
		
		if(Input.escape){
			game.getScreenManager().removeAllScreens();
			game.getScreenManager().addScreen(new MenuScreen(spriteBatcher, game));
		}
		
		//System.out.println("hi");
	}

}

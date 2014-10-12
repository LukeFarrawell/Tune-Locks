package ld26.phased.screen;

import org.lwjgl.opengl.Display;

import ld26.phased.Game;
import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.util.Button;

public class MenuScreen extends Screen{
	
	private SpriteBatcher spriteBatcher;
	private Game game;
	
	private Button play, newGame, quit, options;
	
	public MenuScreen(SpriteBatcher spriteBatcher, Game game){
		this.spriteBatcher = spriteBatcher;
		this.game = game;
		
		
		play = new Button(spriteBatcher,0, 7, 1, 7, Display.getWidth() / 2  - 128, (Display.getHeight() / 2) / 4 + 160 - 65, 256, 128);
		newGame = new Button(spriteBatcher, 0, 6, 1, 6, Display.getWidth() / 2 - 128, (Display.getHeight() / 2 ) / 4 - 65, 256, 128);
		options = new Button(spriteBatcher, 2, 7, 3, 7, Display.getWidth() / 2 - 128, (Display.getHeight() / 2 ) / 4 + 320 - 65, 256, 128);
		quit = new Button(spriteBatcher, 2, 6, 3, 6, Display.getWidth() / 2 - 128, (Display.getHeight() / 2 ) / 4 + 480 - 65, 256, 128);
		
	}
	
	public void render() {
		spriteBatcher.begin();
		play.render();
		newGame.render();
		options.render();
		quit.render();
		spriteBatcher.end();
	}

	public void update() {
		if(newGame.isPressed()){
			game.getScreenManager().removeAllScreens();
			game.getScreenManager().addScreen(new GameScreen(spriteBatcher, game));

		}else if(play.isPressed()){
			game.getScreenManager().removeAllScreens();
			game.getScreenManager().addScreen(new GameScreen(spriteBatcher, game, true));
		}else if(options.isPressed()){
			game.getScreenManager().removeAllScreens();
			game.getScreenManager().addScreen(new OptionsScreen(spriteBatcher, game));
		}else if(quit.isPressed()){
			game.stop();
		}
	}
	
}

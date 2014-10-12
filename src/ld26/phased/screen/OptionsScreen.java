package ld26.phased.screen;

import org.lwjgl.opengl.Display;

import ld26.phased.Game;
import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.input.Input;
import ld26.phased.util.Button;
import ld26.phased.util.Settings;

public class OptionsScreen extends Screen{

	private SpriteBatcher spriteBatcher;
	private Game game;

	private Button music;

	private boolean pressed = false;

	public OptionsScreen(SpriteBatcher spriteBatcher, Game game){
		this.spriteBatcher = spriteBatcher;
		this.game = game;

		music = new Button(spriteBatcher,0, 5, 1, 5, Display.getWidth() / 2  - 128, (Display.getHeight() / 2) / 2, 256, 128);

	}

	public void render() {
		spriteBatcher.begin();
		music.render();

		spriteBatcher.end();
	}

	public void update() {
		
		if(Input.escape){
			game.getScreenManager().removeAllScreens();
			game.getScreenManager().addScreen(new MenuScreen(spriteBatcher, game));
		}
		
		if(music.isPressed()){
			if(!pressed){
				if(Settings.music){
					Settings.music = false;
					game.getMusicDecider().stopSong();
				}else{
					Settings.music = true;
					game.getMusicDecider().chooseSong();
				}
				
				System.out.println(Settings.music);
				pressed = true;
			}
		}else{
			pressed = false;
		}
	}

}

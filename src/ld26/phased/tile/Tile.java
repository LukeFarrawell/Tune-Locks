package ld26.phased.tile;

import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.sound.SoundEngine;
import ld26.phased.util.Timer;

public class Tile {

	public static int TILE_SIZE = 32;

	public boolean solid;

	protected int xSprite, ySprite;

	public int x, y;
	private int sfxNum = 4;

	private long oldTime;

	public Tile(int xSprite, int ySprite, int x, int y, boolean solid, int sfxNum){
		this.xSprite = xSprite;
		this.ySprite = ySprite;
		this.x = x;
		this.y = y;
		this.solid = solid;
		this.sfxNum = sfxNum;
		oldTime = Timer.getTime();
	}

	public Tile(int xSprite, int ySprite, int x, int y, boolean solid){
		this.xSprite = xSprite;
		this.ySprite = ySprite;
		this.x = x;
		this.y = y;
		this.solid = solid;
		this.sfxNum = sfxNum;
	}

	public void playSound(SoundEngine soundEngine){
		if(sfxNum != -1){
			if(Timer.getTime() > oldTime + 400){
				soundEngine.quickPlay("sfx/sfx_"+sfxNum+".ogg");
				oldTime = Timer.getTime();
			}
		}
	}

	public void render(SpriteBatcher spriteBatcher){
		spriteBatcher.draw(xSprite, ySprite, x, y, TILE_SIZE, TILE_SIZE);
	}

	public void update(int frame){

	}
}

package ld26.phased.tile;

import ld26.phased.util.Timer;

public class TileBackground extends Tile{


	public TileBackground(int xSprite, int ySprite, int x, int y, boolean solid) {
		super(xSprite, ySprite, x, y, solid);
		//animationOldTime = Timer.getTime();
	}

	@Override
	public void update(int frame){
			ySprite = frame;
	}


}

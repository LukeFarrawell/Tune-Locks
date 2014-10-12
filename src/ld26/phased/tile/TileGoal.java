package ld26.phased.tile;

public class TileGoal extends Tile{
	
	public TileGoal(int xSprite, int ySprite, int x, int y, boolean solid) {
		super(xSprite, ySprite, x, y, solid, 1);
	}

	public TileGoal(int xSprite, int ySprite, int x, int y, boolean solid, int sfx) {
		super(xSprite, ySprite, x, y, solid, sfx);
	}
}

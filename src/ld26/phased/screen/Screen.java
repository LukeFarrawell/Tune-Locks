package ld26.phased.screen;

public abstract class Screen {
	
	public boolean destroyScreen = false;
	
	public abstract void render();
	public abstract void update();
}

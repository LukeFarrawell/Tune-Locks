package ld26.phased.screen;

import java.util.ArrayList;

import ld26.phased.Game;

public class ScreenManager {

	private Game game;

	private ArrayList<Screen> screen;

	public ScreenManager(Game game) {
		this.game = game;
		screen = new ArrayList<Screen>();
	}

	public void addScreen(Screen s) {
		this.screen.add(s);
	}

	public void removeScreen(Screen s) {
		this.screen.remove(s);
		this.screen.trimToSize();
	}

	public void removeAllScreens() {
		this.screen.clear();
		this.screen.trimToSize();
	}

	public void render() {
		for (int i = 0; i < screen.size(); i++) {
			screen.get(i).render();
		}
	}

	public void update() {
		for (int i = 0; i < screen.size(); i++) {
			screen.get(i).update();
		}
	}
}
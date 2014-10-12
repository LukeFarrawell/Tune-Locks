package ld26.phased;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.input.Input;
import ld26.phased.screen.MenuScreen;
import ld26.phased.screen.ScreenManager;
import ld26.phased.sound.MusicDecider;
import ld26.phased.sound.SoundEngine;
import ld26.phased.util.Settings;
import ld26.phased.util.Timer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game implements Runnable{

	public static final int WIDTH = 1024, HEIGHT = 640;

	private Thread thread;
	private boolean running = false;
	
	private ScreenManager screenManager;
	private SpriteBatcher spriteBatcher;
	private Input input;
	
	private SoundEngine soundEngine;
	private MusicDecider musicDecider;
	
	private long oldTime;
	
	@Override
	public void run() {
		initGL();
		init();
		while(!Display.isCloseRequested() && running){

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

			if(!Settings.paused){
				update();
			}
			
			render();

			Display.update();
			Display.setVSyncEnabled(true);
			Display.sync(60);
			Timer.updateFPS();
		}

		stop();
	}

	public void update(){
		input.update();
		screenManager.update();
		
		if(Timer.getTime() > oldTime + 10000){
			oldTime = Timer.getTime();
			musicDecider.chooseSong();
		}
	}

	public void render(){
		screenManager.render();
		
		//spriteBatcher.begin();
		//spriteBatcher.draw(0, 0, 100, 100, 100, 100);
		//spriteBatcher.end();
	}

	public void init(){
		oldTime = Timer.getTime();
		Settings.load();
		spriteBatcher = new SpriteBatcher("res/images/tiles.png", 32);
		screenManager = new ScreenManager(this);
		input = new Input();
		
		soundEngine = new SoundEngine();
		soundEngine.init();
		musicDecider = new MusicDecider(soundEngine);
		
		screenManager.addScreen(new MenuScreen(spriteBatcher, this));
	}

	public void initGL(){
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Display.setTitle("Tune Locks");
		} catch (LWJGLException e) {
			System.out.println("failed to create screen");
			e.printStackTrace();
			System.exit(1);
		}

		//GL11.glEnable(GL11.GL_TEXTURE_2D);               

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          

		// enable alpha blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glViewport(0,0,WIDTH,HEIGHT);
		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D); 

	}

	public void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop(){
		thread = null;
		running = false;
		soundEngine.dispose();
		System.exit(0);
	}

	public Game(){
		start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public SpriteBatcher getSpriteBatcher() {
		return spriteBatcher;
	}
	
	public SoundEngine getSoundEngine(){
		return soundEngine;
	}
	public MusicDecider getMusicDecider(){
		return musicDecider;
	}
}

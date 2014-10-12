package ld26.phased.util;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Timer {
	
	private static long lastFrame = 0;
	private static long lastFPS = getTime();
	
	public static int fps = 0;
	public static int currentFPS = 0;
	
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public static int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}
	
	public static void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("Tune Locks :: FPS: " + fps); 
	        currentFPS = fps;
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
}

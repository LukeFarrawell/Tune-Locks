package ld26.phased.sound;


public class Sound {
	private SoundEngine soundEngine;
	private String name;
	private boolean loop;
	
	public Sound(SoundEngine soundEngine, String name){
		this.soundEngine = soundEngine;
		this.name = name;
	}
	
	public void playAsBackgroundMusic(){
		soundEngine.getSoundSystem().play(name);
	}
	
	public void stop(){
		soundEngine.getSoundSystem().stop(name);
	}
	
	public void setLoop(boolean flag){
		this.loop = flag;
	}
	
	public void setVolume(float volume){
		soundEngine.getSoundSystem().setVolume(name, volume);
	}
	
	public void setPosition(int x, int y, int z){
		soundEngine.getSoundSystem().setPosition(name, x, y, z);
	}
	
	public void setPitch(float pitch){
		soundEngine.getSoundSystem().setPitch(name, pitch);
	}
	
	public void fade(long duration){
		soundEngine.getSoundSystem().fadeOut(name, null, duration);
	}
	
	public void dispose(){
		if(soundEngine.getSoundSystem().playing(name)){
			stop();
		}
		soundEngine.getSoundSystem().removeSource(name);
	}
	
	public String getName(){
		return name;
	}
}

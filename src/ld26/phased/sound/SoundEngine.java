package ld26.phased.sound;

import ld26.phased.util.Settings;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.codecs.CodecWav;
import paulscode.sound.libraries.LibraryJavaSound;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundEngine {
	private SoundSystem soundSystem;
	private boolean initialized = false;
	
	public Sound music;
	
	public void init(){
		if(!initialized){
			SoundSystemConfig.setSoundFilesPackage("sound/");
			try {
				SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class );
				SoundSystemConfig.addLibrary( LibraryJavaSound.class );
				SoundSystemConfig.setCodec( "wav", CodecWav.class );
				SoundSystemConfig.setCodec( "ogg", CodecJOrbis.class );
			} catch (SoundSystemException e) {
				System.out.println("failed");
				e.printStackTrace();
			}

			soundSystem = new SoundSystem();
			
			soundSystem.setMasterVolume((float)Settings.volume);

			try {
				soundSystem.switchLibrary(LibraryLWJGLOpenAL.class);
			} catch (SoundSystemException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("You have already initialized the SounEngine!");
		}
	}
	
	public Sound newSource(boolean priority, String name, String location, boolean loop, float x, float y, float z, int attmodel, float distOrRoll){
		Sound sound;
		sound = new Sound(this, name);
		soundSystem.newSource(priority, name, location, loop, x, y, z, attmodel, distOrRoll);
		return sound;
		
	}
	
	public void updateSound(){
		soundSystem.setMasterVolume((float)Settings.volume);
	}
	
	public void quickPlay(String filename){
		soundSystem.quickPlay(false, filename, false, 0, 0, 0, 0, 0);
	}
	
	public void playSound(String name){
		if(!soundSystem.playing(name)){
			soundSystem.play(name);
		}
		
	}
	
	public void stopSound(String name){
			soundSystem.stop(name);
	}
	
	public void dispose(){
		if(soundSystem != null){
			soundSystem.cleanup();
		}
	}
	
	public SoundSystem getSoundSystem(){
		return soundSystem;
	}
}


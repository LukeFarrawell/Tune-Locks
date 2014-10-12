package ld26.phased.sound;

import java.util.Random;

import ld26.phased.util.Settings;

public class MusicDecider {
	private Random random;
	private SoundEngine soundEngine;

	private Sound[] music = new Sound[3];


	public MusicDecider(SoundEngine soundEngine){
		this.soundEngine = soundEngine;
		random = new Random();
		
		Thread thread = new Thread(){
			public void run(){
				loadSongs();
			}
		};
		thread.start();
		thread = null;
	}

	public void stopSong(){
		for(int i = 0; i < music.length; i++){
			music[i].stop();
		}
	}


	public void loadSongs(){
		for(int i = 0; i < music.length; i++){
			String name = "music_"+(i+1);
			System.out.println("music_"+(i+1));
			music[i] = this.soundEngine.newSource(true, name, "music/" + name + ".ogg", false, 0, 0, 0, 0, 0);
			music[i].setVolume(0.04f);
		}
		chooseSong();
	}

	public void chooseSong(){
		if(Settings.music){
			for(int i = 0; i < music.length; i++){
				if(soundEngine.getSoundSystem().playing(music[i].getName())){
					System.out.println("song already chosen and playing");
					return;
				}
			}
			int musicToPlay = random.nextInt(music.length);
			System.out.println("playing music: "+music[musicToPlay].getName());
			music[musicToPlay].playAsBackgroundMusic();
		}
	}
}

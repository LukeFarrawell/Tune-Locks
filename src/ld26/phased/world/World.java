package ld26.phased.world;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import ld26.phased.Game;
import ld26.phased.entity.EntityPlayer;
import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.input.Input;
import ld26.phased.tile.Tile;
import ld26.phased.tile.TileBackground;
import ld26.phased.tile.TileColours;
import ld26.phased.tile.TileDoor;
import ld26.phased.tile.TileGoal;
import ld26.phased.tile.TileNote;
import ld26.phased.tile.TileWall;
import ld26.phased.util.Settings;
import ld26.phased.util.Timer;

import org.lwjgl.opengl.Display;

public class World {
	private Tile[][] tiles;
	private int[] levelPixels;
	private int width, height;
	private SpriteBatcher spriteBatcher;

	private EntityPlayer player;
	private Game game;

	private Random random;


	private int doorTileX, doorTileY, goalTileY, goalTileX;

	private int currentLevel;
	private int maxLevel = Settings.maxLevels;

	int yFrames = 3;
	private long animationOldTime;
	private long frameDelay = 800;
	private int frame = 0;
	private boolean reverse = false;

	public World(SpriteBatcher spriteBatcher, Game game){
		this.spriteBatcher = spriteBatcher;
		this.game = game;
		random = new Random();

		currentLevel = 1;

		player = new EntityPlayer(spriteBatcher, game.getSoundEngine(), 64 + 5, Display.getHeight() - 100, this);

		loadLevel(currentLevel);
	}

	public World(SpriteBatcher spriteBatcher, Game game, int levelToStart){
		this.spriteBatcher = spriteBatcher;
		this.game = game;
		random = new Random();

		currentLevel = levelToStart;

		player = new EntityPlayer(spriteBatcher, game.getSoundEngine(), 64 + 5, Display.getHeight() - 100, this);

		loadLevel(currentLevel);
	}

	public void render(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(tiles[x][y] != null){
					tiles[x][y].render(spriteBatcher);
				}
			}
		}

		player.render();
	}

	public void update(){
		
		if(Input.o){
			previousLevel();
			Input.o = false;
		}
		if(Input.p){
			nextLevel();
			Input.p = false;
		}
		if(Input.enter){
			resetLevel();
			Input.enter = false;
		}

		if(Timer.getTime() > animationOldTime + frameDelay){
			animationOldTime = Timer.getTime();
			if(frame < yFrames - 1 && !reverse){
				frame += 1;
			}else
				if(frame == 0){
					reverse = false;
					frame += 1;
				}else{
					reverse = true;
					frame -= 1;
				}
		}

		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(tiles[x][y] != null){
					tiles[x][y].update(frame);
				}
			}
		}
		player.update();
	}

	public void loadLevel(int level){

		try{
			BufferedImage image = ImageIO.read(Game.class.getResource("/images/level/level_"+level+".png"));
			width= image.getWidth();
			height = image.getHeight();

			tiles = new Tile[width][height];
			//image.getRGB(0, 0, width, height, levelPixels, 0, w);

			int goalSFX = random.nextInt(9) + 1;

			boolean notTheSame = false;
			int sfxnum = 1;
			for(int x = 0; x < image.getWidth(); x++){
				for(int y = 0; y < image.getHeight(); y++){

					while(!notTheSame){
						sfxnum = random.nextInt(9) + 1;
						if(sfxnum != goalSFX){
							notTheSame = true;
						}
					}


					if(image.getRGB(x, y) == TileColours.sideWall){

						tiles[x][y] = new TileWall(0, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, true);

					}else if(image.getRGB(x, y) == TileColours.normaTile){

						tiles[x][y] = new TileWall(2, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, true);

					}else if(image.getRGB(x, y) == TileColours.goal){

						goalTileX = x;
						goalTileY = y;
						sfxnum = 1 * level;
						tiles[x][y] = new TileGoal(4, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, true, goalSFX);
						System.out.println("gooalsfx: "+goalSFX);

					}else if(image.getRGB(x, y) == TileColours.door){

						doorTileX = x;
						doorTileY = y;
						sfxnum = 1 * level;
						tiles[x][y] = new TileDoor(3, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, true, goalSFX);
						System.out.println("doorsfx: "+goalSFX);
					}else if(image.getRGB(x, y) == TileColours.playerSpawn){

						player.setXY(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
						tiles[x][y] = new TileBackground(15, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, false);

					}else if(image.getRGB(x, y) == TileColours.noteTile){

						tiles[x][y] = new TileNote(4, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, true, sfxnum);
						System.out.println("nteTile: "+sfxnum);
					}else{

						tiles[x][y] = new TileBackground(15, 0, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, false);

					}

					notTheSame = false;

				}
			}


		}catch(Exception e){
			System.out.println("level: "+level);
			e.printStackTrace();
			game.stop();
		}

	}

	public void nextLevel(){
		if(currentLevel == maxLevel){
			currentLevel = 1;
		}else{
			currentLevel += 1;
		}

		loadLevel(currentLevel);
		Settings.levelSave = currentLevel;
		Settings.save();
		//player.setXY(69, Display.getHeight() - 100);
	}

	public void previousLevel(){
		if(currentLevel == 1){
			currentLevel = 1;
		}else{
			currentLevel -= 1;
		}

		loadLevel(currentLevel);
		Settings.levelSave = currentLevel;
		Settings.save();
	}
	
	public void resetLevel(){
		loadLevel(currentLevel);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || x > width - 1 || y < 0 || y > height - 1){
			return null;
		}
		return tiles[x][y];
	}

	public int getDoorTileX() {
		return doorTileX;
	}

	public int getDoorTileY() {
		return doorTileY;
	}

	public int getGoalTileY() {
		return goalTileY;
	}

	public int getGoalTileX() {
		return goalTileX;
	}
}

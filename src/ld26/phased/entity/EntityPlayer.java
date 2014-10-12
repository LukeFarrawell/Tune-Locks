package ld26.phased.entity;

import java.util.ArrayList;

import ld26.phased.graphics.Animator;
import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.input.Input;
import ld26.phased.math.Vector2;
import ld26.phased.sound.SoundEngine;
import ld26.phased.tile.Tile;
import ld26.phased.tile.TileDoor;
import ld26.phased.tile.TileGoal;
import ld26.phased.tile.TileNote;
import ld26.phased.world.World;

public class EntityPlayer extends Entity {

	private SpriteBatcher spriteBatcher;
	private World world;
	private SoundEngine soundEngine;
	private int width = 28, height = 28;

	private float speed = 3.5f;

	private boolean jumping = false;
	private boolean falling = true;

	private float beginY;
	private float maxHeight = 40;
	private int jumpTime = 0;

	private long oldJumpTime = 0;
	private boolean canJump = true;

	private int lastTouchedWallX = 0, lastTouchedWallY = 0;

	private boolean isGoalActivated = false;

	private Animator animation;

	private int direction;
	
	public EntityPlayer(SpriteBatcher spriteBatcher, SoundEngine soundEngine, int x, int y, World world) {
		this.spriteBatcher = spriteBatcher;
		this.soundEngine = soundEngine;
		this.x = x;
		this.y = y;
		this.world = world;

		animation = new Animator(spriteBatcher, 200, 32, 2, x, y);

		ArrayList<Vector2[]> animated = new ArrayList<Vector2[]>();

		Vector2[] set1 = new Vector2[2]; //walking left
		set1[0] = new Vector2(13, 0); 
		set1[1] = new Vector2(13, 1);

		Vector2[] set2 = new Vector2[2]; //flying left
		set2[0] = new Vector2(12, 0);
		set2[1] = new Vector2(12, 1);

		Vector2[] set3 = new Vector2[2]; //walking right
		set3[0] = new Vector2(13, 2); 
		set3[1] = new Vector2(13, 3);

		Vector2[] set4 = new Vector2[2]; //flying right
		set4[0] = new Vector2(12, 2);
		set4[1] = new Vector2(12, 3);

		animated.add(set1);
		animated.add(set2);
		animated.add(set3);
		animated.add(set4);

		animation.setAnimation(animated);
		animation.setPingPong(true);

		soundEngine.newSource(false, "jetpack", "sfx/jetpack.ogg", false, 0, 0, 0, 0, 0);
		soundEngine.getSoundSystem().setVolume("jetpack", 0.1f);
	}

	public void render() {

		animation.render(width + 2, height);

		//spriteBatcher.draw(3, 0, x, y, width + 2, height);
	}

	@Override
	public void update() {

		/*if(Input.a && Input.space){
			animation.setAnimation(1);
		}
		if(Input.d && Input.space){
			animation.setAnimation(3);
		}*/

		if(!Input.a && !Input.d && !Input.space){
			animation.setMoving(false);
		}
		if (Input.a) {
			direction = 1;
			animation.setAnimation(0);
			move(-speed, 0);
		}
		if (Input.d) {
			direction = 2;
			animation.setAnimation(2);
			move(speed, 0);
		}
		if(Input.space){
			jumping = true;
			if(jumpTime < 60){
				if(!colliding(0, 0)){
					soundEngine.playSound("jetpack");
					//System.out.println(jumpTime);
					if(Input.d ){
						moveJump(1, -2.4f);
						
						//moveJump(1,(float) (-0.75 * Math.sqrt(4 - jumpTime) * 9.8));
					}else if(Input.a){
						moveJump(-1, -2.4f);
						
						//moveJump(-1,(float) (-0.75 * Math.sqrt(4 - jumpTime) * 9.8));
					}else if(direction == 2){
						moveJump(-1, -2.4f);
					}else if(direction == 1){
						moveJump(1, -2.4f);
					}
				}

			}else{
				move(0, 1.0f * 3.4f);
				soundEngine.stopSound("jetpack");
			}
			jumpTime += 1;
		}else{
			move(0, 1.0f * 4.4f);
			jumpTime = 0;
			jumping = false;
		}

		animation.setXY(x, y);
	}

	public void move(float xa, float ya) {
		if (!colliding(xa, ya)) {
			animation.setMoving(true);
			
			/*if(xa > 0){
				direction = 2;
			}
			else if(xa < 0){
				direction = 1;
			}*/
			x += xa;
			y += ya;
		}
	}

	public void moveJump(float xa, float ya) {
		if(xa > 0){
			animation.setAnimation(3);
		}
		if(xa < 0){
			animation.setAnimation(1);
		}
		if (!colliding(0, ya)) {
			animation.setMoving(true);
			//x += xa;
			y += ya;
		}
	}

	public boolean colliding(float xa, float ya) {
		boolean solid = false;
		for (int i = 0; i < 4; i++) {
			int xt = ((int)(x + xa) + i % 2 * 28  + 0) / Tile.TILE_SIZE;
			int yt = ((int)(y + ya) + i / 2 * 26 - 0) / Tile.TILE_SIZE;

			if (world.getTile(xt, yt).solid){ 
				world.getTile(xt, yt).playSound(soundEngine);

				if(world.getTile(xt, yt) instanceof TileGoal){
					isGoalActivated = true;
				}else if(world.getTile(xt, yt) instanceof TileDoor && isGoalActivated){
					//System.out.println("WINNER");
					world.nextLevel();
					isGoalActivated = false;
				}else if(world.getTile(xt, yt) instanceof TileNote){
					isGoalActivated = false;
				}

				this.lastTouchedWallX = xt;
				this.lastTouchedWallY = yt;
				//}
				solid = true;
			}
		}
		return solid;
	}

	public boolean collidingFloor(){
		boolean solid = false;
		for (int i = 2; i < 4; i++) {
			int xt = ((int)(x) + i % 2 * 28  + 0) / Tile.TILE_SIZE;
			int yt = ((int)(y ) + i / 2 * 26 - 0) / Tile.TILE_SIZE;

			if (world.getTile(xt, yt).solid){ 
				solid = true;
			}
		}
		return solid;
	}

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

}

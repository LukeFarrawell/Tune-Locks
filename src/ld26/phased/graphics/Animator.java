package ld26.phased.graphics;

import java.util.ArrayList;

import ld26.phased.math.Vector2;
import ld26.phased.util.Timer;

public class Animator {

	private SpriteBatcher spriteBatcher;

	private ArrayList<Vector2[]> sprites;

	private long frameDelay;
	private long oldTime;
	private int spriteSize;
	private int frame = 0;
	private int animationSet = 0;
	private int animationLength;

	private float xPos, yPos;

	private boolean pingpong = false, reverse = false;

	private boolean moving = false, movingRight = false;
	
	private int direction = 0;

	public Animator(SpriteBatcher spriteBatcher, long frameDelay, int spriteSize, int animationLength, int xPos, int yPos){
		this.spriteBatcher = spriteBatcher;
		this.frameDelay = frameDelay;
		this.spriteSize = spriteSize;
		this.animationLength = animationLength;
		this.xPos = xPos;
		this.yPos = yPos;
		oldTime = Timer.getTime();
	}

	public void setAnimation(ArrayList<Vector2[]> sprites){
		this.sprites = sprites;
	}

	public void setPingPong(boolean flag){
		this.pingpong = flag;
	}

	public void setAnimation(int i){
		animationSet = i;
	}

	public void setMoving(boolean flag){
		this.moving = flag;
	}

	public void setMovingRight(boolean flag){
		this.movingRight = flag;
	}
	
	public void setDirection(){
		
	}

	public void setXY(float x, float y){
		this.xPos = x;
		this.yPos = y;
	}

	public void render(){

		if(Timer.getTime() > oldTime + frameDelay){
			oldTime = Timer.getTime();
			if(frame < animationLength - 1 && !reverse){
				frame += 1;
			}else
				if(frame == 0){
					reverse = false;
					frame += 1;
				}else if(pingpong){
					reverse = true;
					frame -= 1;
				}else{
					frame = 0;
				}
		}
		
		spriteBatcher.draw(sprites.get(animationSet)[frame].x, sprites.get(animationSet)[frame].y, xPos , yPos, 32, 32);
	}

	public void render(int width, int height){

		if(Timer.getTime() > oldTime + frameDelay  && moving){
			oldTime = Timer.getTime();
			if(frame < animationLength - 1 && !reverse){
				frame += 1;
			}else
				if(frame == 0){
					reverse = false;
					frame += 1;
				}else if(pingpong){
					reverse = true;
					frame -= 1;
				}else{
					frame = 0;
				}
		}
		
		//System.out.println("animation set: "+animationSet);
		//System.out.println("x = "+sprites.get(animationSet)[frame].x+"  y = "+sprites.get(animationSet)[frame].y);
		
		spriteBatcher.draw(sprites.get(animationSet)[frame].x, sprites.get(animationSet)[frame].y, xPos , yPos, width, height);
	}
}

package ld26.phased.util;

import ld26.phased.graphics.SpriteBatcher;
import ld26.phased.input.Input;

public class Button {
	private SpriteBatcher spriteBatcher;
	
	private int defaultX, defaultY, hoverX, hoverY;
	private int spriteX, spriteY;
	private int x, y, width, height;
	
	private boolean pressed = false;
	
	private int sizeWidth, sizeHeight;;
	
	
	
	public Button(SpriteBatcher spriteBatcher, int defaultX, int defaultY, int hoverX, int hoverY, int x, int y, int width, int height){
		this.spriteBatcher = spriteBatcher;
		this.defaultX = defaultX;
		this.defaultY = defaultY;
		this.hoverX = hoverX;
		this.hoverY = hoverY;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.spriteX = defaultX;
		this.spriteY = defaultY;
		sizeWidth = 128;
		sizeHeight = 64;
	}
	
	public Button(SpriteBatcher spriteBatcher, int defaultX, int defaultY, int hoverX, int hoverY, int x, int y, int width, int height, int size){
		this.spriteBatcher = spriteBatcher;
		this.defaultX = defaultX;
		this.defaultY = defaultY;
		this.hoverX = hoverX;
		this.hoverY = hoverY;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.spriteX = defaultX;
		this.spriteY = defaultY;
		this.sizeWidth = size;
		this.sizeHeight = size;
	}
	
	public void checkMouse(){
		if( (Input.mouseX >= x && Input.mouseX <= (x + width)) && (Input.mouseY >= y && Input.mouseY <= (y + height)) ){
			if(Input.leftMouse){
				pressed = true;
			}else{
				pressed = false;
			}
			spriteX = hoverX;
			spriteY = hoverY;
		}else{
			pressed = false;
			spriteX = defaultX;
			spriteY = defaultY;
		}
	}
	
	public void render(){
		checkMouse();
		spriteBatcher.draw(spriteX, spriteY, sizeWidth, sizeHeight, x, y, width, height);
	}
	
	public boolean isPressed(){
		return pressed;
	}
	
	public void setPressed(boolean flag){
		pressed = flag;
	}
}
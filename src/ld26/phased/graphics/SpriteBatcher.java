package ld26.phased.graphics;

import java.io.IOException;

import mdesl.graphics.Texture;

import org.lwjgl.opengl.GL11;

public class SpriteBatcher {
	private Texture texture;
	private int spriteSize;
	
	public SpriteBatcher(String filename, int spriteSize){
		try {
			this.texture = new Texture(filename);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		this.spriteSize = spriteSize;
	}
	
	public void begin(){
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
	}
	
	public void end(){
		GL11.glEnd();
		
	}
	
	public void changeTexture(String filename){
		try {
			this.texture = new Texture(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void changeTexture(Texture texture){
		this.texture = texture;
	}
	
	public void setSpriteSize(int spriteSize){
		this.spriteSize = spriteSize;
	}
	
	public void draw(int spriteX, int spriteY, float x, float y, float width, float height){
		
		float srcX = spriteX * spriteSize;
		float srcY = spriteY * spriteSize;
		float srcWidth = spriteSize;
		float srcHeight = spriteSize;

		float u = srcX / texture.getWidth();
		float v = srcY / texture.getHeight();
		float u2 = (srcX + srcWidth) / (texture.getWidth());
		float v2 = (srcY + srcHeight) / (texture.getHeight());
		
		GL11.glTexCoord2f(u, v);
		GL11.glVertex2f(x, y);
		
		GL11.glTexCoord2f(u2, v);
		GL11.glVertex2f(x + width, y);
		
		GL11.glTexCoord2f(u2, v2);
		GL11.glVertex2f(x + width, y + height);
		
		GL11.glTexCoord2f(u, v2);
		GL11.glVertex2f(x, y + height);

	}
	
	/**
	 * Custom square size from the current texture (greater then the specified size)
	 */
	public void draw(int spriteX, int spriteY, int size, float x, float y, float width, float height){
		float srcX = spriteX * size;
		float srcY = spriteY * size;
		float srcWidth = size;
		float srcHeight = size;

		float u = srcX / texture.getWidth();
		float v = srcY / texture.getHeight();
		float u2 = (srcX + srcWidth) / texture.getWidth();
		float v2 = (srcY + srcHeight) / texture.getHeight();
		
		GL11.glTexCoord2f(u, v);
		GL11.glVertex2f(x, y);
		
		GL11.glTexCoord2f(u2, v);
		GL11.glVertex2f(x + width, y);
		
		GL11.glTexCoord2f(u2, v2);
		GL11.glVertex2f(x + width, y + height);
		
		GL11.glTexCoord2f(u, v2);
		GL11.glVertex2f(x, y + height);
	}
	
	/**
	 * Custom sprite width and height
	 */
	public void draw(int spriteX, int spriteY, int spriteWidth, int spriteHeight, float x, float y, float width, float height){
		float srcX = spriteX * spriteWidth;
		float srcY = spriteY * spriteHeight;
		float srcWidth = spriteWidth;
		float srcHeight = spriteHeight;

		float u = srcX / texture.getWidth();
		float v = srcY / texture.getHeight();
		float u2 = (srcX + srcWidth) / texture.getWidth();
		float v2 = (srcY + srcHeight) / texture.getHeight();
		
		GL11.glTexCoord2f(u, v);
		GL11.glVertex2f(x, y);
		
		GL11.glTexCoord2f(u2, v);
		GL11.glVertex2f(x + width, y);
		
		GL11.glTexCoord2f(u2, v2);
		GL11.glVertex2f(x + width, y + height);
		
		GL11.glTexCoord2f(u, v2);
		GL11.glVertex2f(x, y + height);
	}
}

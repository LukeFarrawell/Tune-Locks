package ld26.phased.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Input {
	public static boolean leftMouse, rightMouse;
	public static int mouseX, mouseY, mouseDX, mouseDY, mouseWheel;
	public static boolean w = false, a = false, s = false, d = false;
	public static boolean left, right;
	public static boolean space = false;
	public static boolean escape = false, enter = false;
	public static boolean o = false, p = false;
	public void update() {
		int width = Display.getWidth();
		int height = Display.getHeight();
		mouseX = Mouse.getX();
		mouseY = height - Mouse.getY();
		leftMouse = Mouse.isButtonDown(0);
		rightMouse = Mouse.isButtonDown(1);
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					space = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					a = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					s = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					d = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
					a = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
					d = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP){
					space = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					space = true;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					escape = true;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_O){
					o = true;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_P){
					p = true;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN){
					enter = true;
				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					space = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					a = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					s = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					d = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
					a = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
					d = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP){
					space = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					space = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					escape = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_O){
					o = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_P){
					p = false;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN){
					enter = false;
				}
			}
		}
	}
}

package ld26.phased.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import ld26.phased.Game;

public class Settings {
	public static boolean paused = false;
	public static boolean music = true;
	public static int levelSave = 1;
	public static int maxLevels = 3;
	
	public static double volume = 1.0; //1.0f
	
	public static void load(){
		
		try {
			File file = new File("res/Settings.txt");
			Scanner scanner = new Scanner(file);
			
			music = scanner.nextBoolean();
			volume = scanner.nextFloat();
			levelSave = scanner.nextInt();
			maxLevels = scanner.nextInt();
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void save(){
		
		/*
true
1
2
		 */
		
		try {
			FileWriter saveFile = new FileWriter("res/Settings.txt");
			
			if(music){
				saveFile.write("true\n");
			}else{
				saveFile.write("false\n");
			}
			String str = ""+volume+"\n";
			saveFile.write(str);
			
			str = ""+levelSave+"\n";
			saveFile.write(str);
			
			str = ""+maxLevels+"\n";
			saveFile.write(str);
			
			saveFile.write(" THE LINE ABOVE IS THE MAXIMUM AMOUNT OF LEVELS CHANGE IT IF YOU ADD EXTRA LEVELS, LEVELS START AT 1, SO EITHER, 1 , 2 , 3 , 100\n");
			
			saveFile.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
		}
	}
}

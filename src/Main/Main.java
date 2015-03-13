package Main;

import Controller.Game;

/**
 * 
 * @author Jacob
 * Runs game.
 */
public class Main{
	public static void main(String[] args){
		System.setProperty("sun.java2d.opengl","True");
		Game myGame = new Game();
	}
}
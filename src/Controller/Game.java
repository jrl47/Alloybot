package Controller;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import Model.Model;
import View.View;

/**
 * 
 * @author Jacob
 * Keeps track of universal values and sets up Game framework.
 */
public class Game implements Runnable{
	private final double TICK_LENGTH = 1000000000.0/30.0;
	
	private Thread thread;
	private boolean exitGame;
	private View view;
	private Model model;
	private InputListener listener;
	public static long ticks;
	public Game(){
		listener = new InputListener();
		view = new View(listener);
		model = new Model();
		init();
	}
	public synchronized void init(){
		thread = new Thread(this, "Alloybot");
		exitGame = false;
		thread.start();
	}
	public void run() {
		long firstTime = System.nanoTime();
		while(exitGame==false){
			step();
			render();
			useInput();
			long now = System.nanoTime();
			while(now - (firstTime+ticks*TICK_LENGTH) < TICK_LENGTH){
				Thread.yield();
				try{
					Thread.sleep(1);
				} catch(Exception e) {}
				now = System.nanoTime();
			}
			ticks++;
		}
	}
	public synchronized void exit(){
		exitGame = true;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void step() {
		model.step();
	}
	public void render() {
		view.render(model.getScreenData());
	}
	public void useInput() {
		listener.step(view.getScreen().getComponents());
	}
}

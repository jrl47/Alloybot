package View;

import java.awt.Canvas;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import Controller.InputListener;
import ModelComponents.ModelComponent;

public class View {
	public static int width = 800;
	public static int height = (width / 16) * 9;
	public static double scale = 2;
	
	private JFrame frame;
	private Display display;
	private Canvas canvas;
	public View(InputListener l){
		Dimension size = new Dimension((int)(width*scale), (int)(height*scale));
		canvas = new Canvas();
		canvas.setPreferredSize(size);
		canvas.addKeyListener(l);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Alloybot");
		frame.add(canvas);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
		frame.setFocusable(true);
		display = new Display(width, height, canvas);
	}
	public void render(List<ModelComponent> s) {
		display.render(s);
	}
}

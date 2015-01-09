package View;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import Controller.InputListener;
import Model.ModelData;
import Model.ScreenData;
import ModelComponents.ModelComponent;

public class View {
	public static int width = 800;
	public static int height = (width / 16) * 9;
	public static double scale = 1;
	
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private PixelArray pixels;
	private ScreenBuilder myScreen;
	private String state;
	public View(InputListener l){
		Dimension size = new Dimension((int)(width*scale), (int)(height*scale));
		myScreen = new ScreenBuilder(null, null);
		canvas = new Canvas();
		canvas.setPreferredSize(size);
		canvas.addMouseListener(l);
		canvas.addMouseMotionListener(l);
		state = "";
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Alloybot");
		frame.add(canvas);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
		frame.setFocusable(true);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int [] pixel = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		pixels = new PixelArray(pixel, width);
//		pixels = new PixelArray(image);
	}
	public void render(ScreenData data) {
    	BufferStrategy bs = canvas.getBufferStrategy();
    	if(bs==null){
    		canvas.createBufferStrategy(3);
    		return;
    	}
    	if(!state.equals(data.getID())){
    		myScreen = new ScreenBuilder(data, pixels);
    		state = data.getID();
    	}
    	myScreen.drawComponents();
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	public ScreenBuilder getScreen(){
		return myScreen;
	}
}

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
	public static double scale = 2;
	
	private JFrame frame;
	private Canvas canvas;
	private BufferedImage image;
	private PixelArray pixels;
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
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int [] pixel = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		pixels = new PixelArray(pixel, width);
	}
	public void render(ScreenData data) {
    	BufferStrategy bs = canvas.getBufferStrategy();
    	if(bs==null){
    		canvas.createBufferStrategy(3);
    		return;
    	}
    	clear();
		ScreenBuilder s = new ScreenBuilder(data);
    	setPixels(s.getPixels());
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}
	private void setPixels(PixelArray p) {
		pixels.setPixels(p);
	}

	public void clear(){
		pixels.clear();
	}
}

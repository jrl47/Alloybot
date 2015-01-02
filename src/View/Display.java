package View;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;
import java.util.List;

import ModelComponents.ModelComponent;

/**
 * 
 * @author Jacob
 * Takes GameObjects on a given stage and displays them.
 */
public class Display {
	
	private int width;
	private int height;
	private static final double WIDTH_SCALAR = .05;
	private static final double HEIGHT_SCALAR = .05;
	private Canvas canvas;
	private BufferedImage image;
	private PixelArray pixels;
	public Display(int w, int h, Canvas c){
		width = w;
		height = h;
		canvas = c;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int [] pixel = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		pixels = new PixelArray(pixel, width);
	}
	
	public void render(List<ModelComponent> components){
    	BufferStrategy bs = canvas.getBufferStrategy();
    	if(bs==null){
    		canvas.createBufferStrategy(3);
    		return;
    	}
    	clear();
    	// setPixels(p.getPixels());
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

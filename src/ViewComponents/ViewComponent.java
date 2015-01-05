package ViewComponents;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.PixelArray;

public class ViewComponent {
	
	private ModelComponent myComponent;
	private PixelArray myPixels;
	private Shape myBounds;
	private int x;
	private int y;
	public ViewComponent(ModelComponent c, BufferedImage b, int xx, int yy){
		myPixels = new PixelArray(b);
		x = xx;
		y = yy;
	}
	public PixelArray getPixels(){
		return myPixels;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

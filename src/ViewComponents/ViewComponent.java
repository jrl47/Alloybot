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
		myComponent = c;
		x = xx;
		y = yy;
		myBounds = new Rectangle(x, y, b.getWidth(), b.getHeight());
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
	public Shape getBounds(){
		return myBounds;
	}
	public void respond(){
		if(myComponent!=null){
			myComponent.respond();
			System.out.println("Dsd");
		}
	}
}

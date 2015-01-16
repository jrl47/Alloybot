package ViewComponents;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.PixelArray;

public abstract class ViewComponent {
	
	private ModelComponent myComponent;
	private PixelArray myPixels;
	private PixelArray myHoverPixels;
	private Shape myBounds;
	private BufferedImage myImage;
	private BufferedImage myHover;
	private boolean isHover;
	private boolean isLoaded;
	private int x;
	private int y;
	public ViewComponent(ModelComponent c, int xx, int yy){
		isHover = false;
		myComponent = c;
		x = xx;
		y = yy;
	}
	public PixelArray getPixels(){
		if(!isLoaded){
			myImage = loadImage();
			myPixels = new PixelArray(myImage);
			myHover = loadHover();
			myHoverPixels = new PixelArray(myHover);
			myBounds = new Rectangle(x, y, myImage.getWidth(), myImage.getHeight());
		}
		if(!isHover)
			return myPixels;
		return myHoverPixels;
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
		}
	}
	public abstract BufferedImage loadImage();
	public abstract BufferedImage loadHover();
	public void setHover(boolean b){
		isHover = b;
	}
	public void setLoaded(boolean b){
		isLoaded = b;
	}
}

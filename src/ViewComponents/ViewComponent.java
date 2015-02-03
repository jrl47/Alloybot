package ViewComponents;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;

public abstract class ViewComponent {
	
	protected ModelComponent myComponent;
	protected BufferedImage myImage;
	protected BufferedImage myHoverImage;
	private Shape myBounds;
	protected boolean isHover;
	private int x;
	private int y;
	public ViewComponent(ModelComponent c, int xx, int yy){
		isHover = false;
		myComponent = c;
		x = xx;
		y = yy;
	}
	public ViewComponent(ModelComponent c, int xx, int yy, int width, int height){
		isHover = false;
		myComponent = c;
		x = xx;
		y = yy;
		myBounds = new Rectangle(x, y, width, height);
	}
	public BufferedImage getImage(){
		myImage = loadImage();
		myHoverImage = loadHover();
		myBounds = new Rectangle(x, y, myImage.getWidth(), myImage.getHeight());
		if(!isHover){
			return myImage;
		}
		return myHoverImage;
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
}

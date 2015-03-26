package ViewComponents;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ModelComponents.ModelComponent;

public abstract class ViewComponent {
	
	protected List<ViewComponent> myComponents;
	protected ViewComponent parentComponent;
	protected ModelComponent myComponent;
	protected BufferedImage myImage;
	protected BufferedImage myHoverImage;
	private Shape myBounds;
	protected boolean isHover;
	protected int x;
	protected int y;
	private int absoluteX;
	private int absoluteY;
	public ViewComponent(ModelComponent c, int xx, int yy){
		isHover = false;
		myComponent = c;
		myComponents = new ArrayList<ViewComponent>();
		x = xx;
		absoluteX = x;
		y = yy;
		absoluteY = y;
	}
	public ViewComponent(ModelComponent c, int xx, int yy, int width, int height){
		isHover = false;
		myComponent = c;
		x = xx;
		absoluteX = x;
		y = yy;
		absoluteY = y;
		myComponents = new ArrayList<ViewComponent>();
		myBounds = new Rectangle(x, y, width, height);
	}
	public BufferedImage getImage(){
		myImage = loadImage();
		myHoverImage = loadHover();
		myBounds = new Rectangle(absoluteX, absoluteY, myImage.getWidth(), myImage.getHeight());
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
	public int getAbsoluteX(){
		return absoluteX;
	}
	public void setAbsoluteX(int newX){
		absoluteX = newX;
	}
	public int getAbsoluteY(){
		return absoluteY;
	}
	public void setAbsoluteY(int newY){
		absoluteY = newY;
	}
	public Shape getBounds(){
		return myBounds;
	}
	public void respond(){
		if(myComponent!=null){
			myComponent.respond();
		}
	}
	private void addParentComponent(ViewComponent v){
		if(v==null){
			absoluteX =x;
			absoluteY =y;
			parentComponent = null;
		}
		else{
			parentComponent = v;
			absoluteX = x + v.getAbsoluteX();
			absoluteY = y + v.getAbsoluteY();
		}
	}
	public void addComponent(ViewComponent v){
		v.addParentComponent(this);
		myComponents.add(v);
	}
	public void removeComponent(ViewComponent v){
		v.addParentComponent(null);
		myComponents.remove(v);
	}
	public abstract BufferedImage loadImage();
	public abstract BufferedImage loadHover();
	public void drawComponents() {
		for(int i=0; i<myComponents.size(); i++){
			drawComponent(myComponents.get(i));
		}
	}
	public void drawComponent(ViewComponent v){
		if(myImage!=null){
			Graphics2D g = (Graphics2D) myImage.getGraphics();
			g.drawImage(v.getImage(), v.getX(), v.getY(), null);
		}
	}
	public ModelComponent getComponent(){
		return myComponent;
	}
	public List<ViewComponent> getComponents(){
		return myComponents;
	}
	public void setHover(boolean b){
		isHover = b;
	}
}

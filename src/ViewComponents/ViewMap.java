package ViewComponents;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import ModelComponents.ModelComponent;
import ModelComponents.Robot;

public class ViewMap extends ViewComponent implements InputSensitive{

	public static final int WIDTH = 31;
	public static final int HEIGHT = 27;
	public static final int BORDER_WIDTH = 9;
	
	private ViewMapAnimationHandler animation;
	private ViewMapGraphicsHandler graphics;
	private ModelMap myMap;
	private Map<MapCellObject, ViewMapObject> myViewMapObjects;
	private List<ViewMapObject> myMapObjects;
	private MapCellObject currentlySelectedObject;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy, 2*BORDER_WIDTH + 16*WIDTH, 2*BORDER_WIDTH + 16*HEIGHT);
		currentlySelectedObject = null;
		animation = new ViewMapAnimationHandler();
		myMap = (ModelMap)myComponent;
		myMapObjects = new ArrayList<ViewMapObject>();
		myViewMapObjects = new HashMap<MapCellObject, ViewMapObject>();
		for(Robot r: myMap.getRobots()){
			ViewRobot v = new ViewRobot(r, myMap);
			myViewMapObjects.put(r, v);
			myMapObjects.add(v);
		}
		graphics = new ViewMapGraphicsHandler(animation, myMap, myViewMapObjects);
	}

	@Override
	public BufferedImage loadImage() {
		animation.setX(((ModelMap)myComponent).getX() - WIDTH/2);
		animation.setY(((ModelMap)myComponent).getY() - HEIGHT/2);
		if(!graphics.isLoaded()){
			graphics.loadMap();
		}
		
		if(!isHover){
			myMap.undoHighlight();
		}
		animation.handleAnimation();
		return graphics.drawVisibleMapRegion(isHover, myMapObjects);
	}
	
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

	@Override
	public void useInput(int xx, int yy, boolean b) {
		if(!animation.inAnimation()){
			graphics.setXHover(xx);
			graphics.setYHover(yy);
			((ModelMap)myComponent).setXTile(animation.getX() + ((xx-BORDER_WIDTH)/16));
			((ModelMap)myComponent).setYTile(animation.getY() + ((yy-BORDER_WIDTH)/16));
			int newX = ((ModelMap)myComponent).getCell(animation.getX() + ((xx-BORDER_WIDTH)/16), animation.getY() + ((yy-BORDER_WIDTH)/16)).getX();
			int newY = ((ModelMap)myComponent).getCell(animation.getX() + ((xx-BORDER_WIDTH)/16), animation.getY() + ((yy-BORDER_WIDTH)/16)).getY();
			
			// If we've got a click and an object
			if(b && currentlySelectedObject != null){
				myViewMapObjects.get(currentlySelectedObject).trigger(newX, newY);
				return;
			}
			
			// If we've got a click and no object, we might be selecting one
			else if(b){
				if(((ModelMap)myComponent).getCurrentHighlightedCell().getObjects().size()!=0){
					myViewMapObjects.get(((ModelMap)(myComponent)).getRobot(((ModelMap)myComponent).getCurrentHighlightedCell().getX(),
							((ModelMap)myComponent).getCurrentHighlightedCell().getY())).trigger(newX, newY);
					return;
				}
			}
			// If we've got a click, and we haven't already returned due to object schenanigans, move screen
			if(b && !animation.inAnimation() && xx >= 9 && yy >= 9 &&
					xx < 9 + WIDTH*16 && yy < 9 + HEIGHT*16){
				if (newX + WIDTH/2 >= ((ModelMap)myComponent).getWidth())
					newX = ((ModelMap)myComponent).getWidth() - WIDTH/2 - 1;
				if (newX - WIDTH/2 < 0)
					newX = WIDTH/2;
				if (newY + HEIGHT/2 >= ((ModelMap)myComponent).getHeight())
					newY = ((ModelMap)myComponent).getHeight() - HEIGHT/2 - 1;
				if (newY - HEIGHT/2 < 0)
					newY = HEIGHT/2;
				((ModelMap)myComponent).setX(newX);
				((ModelMap)myComponent).setY(newY);
				graphics.setXHover(-100);
				graphics.setYHover(-100);
			}
		}
	}
}

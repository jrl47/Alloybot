package ViewComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ModelComponents.Robot;

public class ViewMap extends ViewComponent implements InputSensitive{

	public static final int WIDTH = 31;
	public static final int HEIGHT = 27;
	public static final int BORDER_WIDTH = 9;
	
	private ViewMapAnimationHandler animation;
	private ViewMapGraphicsHandler graphics;
	private ModelMap myMap;
	private Map<MapCell, List<Character>> myPaths;
	private List<Character> currentPath;
	private boolean moveLoaded;
	private Robot movementRobot;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy, 2*BORDER_WIDTH + 16*WIDTH, 2*BORDER_WIDTH + 16*HEIGHT);
		animation = new ViewMapAnimationHandler();
		myPaths = new HashMap<MapCell, List<Character>>();
		myMap = (ModelMap)myComponent;
		myMap.setXSelect(-1);
		myMap.setYSelect(-1);
		graphics = new ViewMapGraphicsHandler(animation, myMap);
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
		
		if(animation.isDone() && movementRobot!=null){
			movementRobot.setDirection('d');
		}
		
		if(animation.getMovementStatus()){
			if(currentPath.get(0)=='u'){
				movementRobot.setDirection('u');
				movementRobot.move(movementRobot.getX(), movementRobot.getY()-1);
			}
			if(currentPath.get(0)=='d'){
				movementRobot.setDirection('d');
				movementRobot.move(movementRobot.getX(), movementRobot.getY()+1);
			}
			if(currentPath.get(0)=='l'){
				movementRobot.setDirection('l');
				movementRobot.move(movementRobot.getX()-1, movementRobot.getY());
			}
			if(currentPath.get(0)=='r'){
				movementRobot.setDirection('r');
				movementRobot.move(movementRobot.getX()+1, movementRobot.getY());
			}
			currentPath.remove(0);
		}
		
		if(currentlySelectedRobot()!=null && currentlySelectedRobot().movable() && !moveLoaded){
			moveLoaded = true;
			loadPaths();
			graphics.setMoves(myPaths.keySet());
		}
		
		return graphics.drawVisibleMapRegion(isHover, moveLoaded && currentlySelectedRobot()!=null && currentlySelectedRobot().movable(), currentlySelectedRobot());
	}
	
	private Robot currentlySelectedRobot(){
		if(myMap.getXSelect() == -1 || myMap.getYSelect() == -1)
			return null;
		return ((ModelMap)(myComponent)).getRobot(myMap.getXSelect(), myMap.getYSelect());
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
			
			// If we've got a click and a robot
			if(b && currentlySelectedRobot() != null){
				if(currentlySelectedRobot().movable()){
					if(currentPath == null && myPaths.containsKey(myMap.getCell(newX, newY)) && myPaths.get(myMap.getCell(newX, newY)).size()!=0){
						currentPath = myPaths.get(myMap.getCell(newX, newY));
						if(currentPath!=null && currentPath.size()!=0){
							animation.setMovementCounter(currentPath.size()*32+32);
							animation.setRobot(currentlySelectedRobot());
							movementRobot = currentlySelectedRobot();
							moveLoaded = false;
						}
					}
				}
				if(currentlySelectedRobot()!=null){
					currentlySelectedRobot().deselect();
					moveLoaded = false;
				}
				myMap.setXSelect(-1);
				myMap.setYSelect(-1);
				return;
			}
			
			// If we've got a click and no robot, we might be selecting one
			else if(b){
				for(MapCellObject m: ((ModelMap)myComponent).getCurrentHighlightedCell().getObjects()){
					if(m instanceof Robot){
						myMap.setXSelect(((ModelMap)myComponent).getCurrentHighlightedCell().getX());
						myMap.setYSelect(((ModelMap)myComponent).getCurrentHighlightedCell().getY());
						return;
					}
				}
			}
			// If we've got a click, and we haven't already returned due to robot schenanigans, move screen
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

	private void loadPaths() {
		myPaths.clear();
		myMap.loadPaths(myPaths, currentlySelectedRobot().getX(), currentlySelectedRobot().getY(), Math.min(10, myMap.getResources().getOil()/1000));
	}
}

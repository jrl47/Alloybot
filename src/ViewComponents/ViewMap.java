package ViewComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ModelComponents.Robot;
import View.DeciduousTileManager;
import View.ScreenBuilder;

public class ViewMap extends ViewComponent implements InputSensitive{

	public static final int WIDTH = 31;
	public static final int HEIGHT = 27;
	public static final int BORDER_WIDTH = 9;
	
	private ViewMapAnimationHandler animation;
	private ViewMapGraphicsHandler graphics;
	private ModelMap myMap;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy, 2*BORDER_WIDTH + 16*WIDTH, 2*BORDER_WIDTH + 16*HEIGHT);
		animation = new ViewMapAnimationHandler();
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
		
		return graphics.drawVisibleMapRegion(isHover, currentlySelectedRobot());
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
			if(b && currentlySelectedRobot() != null){
				if(currentlySelectedRobot().movable()){
					if(Math.abs(newX - currentlySelectedRobot().getX()) + 
							Math.abs(newY - currentlySelectedRobot().getY()) <= Math.min(5, myMap.getResources().getOil()/1000) &&
							Math.abs(newX - currentlySelectedRobot().getX()) + 
							Math.abs(newY - currentlySelectedRobot().getY()) > 0 && myMap.getCell(newX, newY).isPassable()){
						List<Character> path = myMap.getPath(currentlySelectedRobot().getX(), currentlySelectedRobot().getY(), newX, newY);
						if(path!=null && path.size()!=0){
							currentlySelectedRobot().move(newX, newY);
							graphics.setMoveLoaded(false);
						}
					}
				}
				if(currentlySelectedRobot()!=null){
					currentlySelectedRobot().stopMove();
					currentlySelectedRobot().deselect();
				}
				myMap.setXSelect(-1);
				myMap.setYSelect(-1);
				return;
			}
			else if(b){
				for(MapCellObject m: ((ModelMap)myComponent).getCurrentHighlightedCell().getObjects()){
					if(m instanceof Robot){
						myMap.setXSelect(((ModelMap)myComponent).getCurrentHighlightedCell().getX());
						myMap.setYSelect(((ModelMap)myComponent).getCurrentHighlightedCell().getY());
						return;
					}
				}
			}
			if(b && !animation.inAnimation() && xx >= 9 && yy >= 9 &&
					xx < 9 + WIDTH*16 && yy < 9 + HEIGHT*16){
				if (newX + WIDTH/2 >= ((ModelMap)myComponent).getWidth()){
					newX = ((ModelMap)myComponent).getWidth() - WIDTH/2 - 1;
				}
				if (newX - WIDTH/2 < 0){
					newX = WIDTH/2;
				}
				if (newY + HEIGHT/2 >= ((ModelMap)myComponent).getHeight()){
					newY = ((ModelMap)myComponent).getHeight() - HEIGHT/2 - 1;
				}
				if (newY - HEIGHT/2 < 0){
					newY = HEIGHT/2;
				}
				((ModelMap)myComponent).setX(newX);
				((ModelMap)myComponent).setY(newY);
				graphics.setXHover(-100);
				graphics.setYHover(-100);
			}
		}
	}
}

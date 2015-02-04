package ModelComponents;

import java.util.ArrayList;
import java.util.List;

import Model.MapCellFactory;

public abstract class ModelMap extends ModelComponent{
	protected MapCellArray myCells;
	protected MapCellFactory myFactory;
	protected List<Robot> myRobots;
	protected ResourceManager myManager;
	protected int x;
	protected int y;
	
	protected int xTile;
	protected int yTile;
	
	protected int myWidth;
	protected int myHeight;
	public ModelMap(int width, int height, ResourceManager m){
		List<List<MapCell>> myList = new ArrayList<List<MapCell>>();
		for(int i=0; i<width; i++){
			List<MapCell> newList = new ArrayList<MapCell>();
			for(int j=0; j<height; j++){
				newList.add(null);
			}
			myList.add(newList);
		}
		myWidth = width;
		myHeight = height;
		myCells = new MapCellArray(myList);
		myFactory = new MapCellFactory();
		myManager = m;
		myRobots = new ArrayList<Robot>();
	}
	public MapCell getCell(int i, int j){
		return myCells.getCell(i, j);
	}
	public ResourceManager getResources(){
		return myManager;
	}
	public void addRobot(Robot r, int x, int y){
		myRobots.add(r);
		r.addToMap(this, x, y);
	}
	public int getX(){
		return x;
	}
	public void setX(int xx){
		x = xx;
	}
	public void incrementX(int xinc){
		x += xinc;
	}
	public int getY(){
		return y;
	}
	public void setY(int yy){
		y = yy;
	}
	public void incrementY(int yinc){
		y += yinc;
	}
	public int getXTile(){
		return xTile;
	}
	public void setXTile(int x){
		xTile = x;
	}
	public int getYTile(){
		return yTile;
	}
	public void setYTile(int y){
		yTile = y;
	}
	public MapCell getCurrentHighlightedCell(){
		return myCells.getCell(xTile, yTile);
	}
	public int getWidth(){
		return myWidth;
	}
	public int getHeight(){
		return myHeight;
	}
}

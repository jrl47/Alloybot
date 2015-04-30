package ModelComponents;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Model.MapCellFactory;
import Model.State;

public abstract class ModelMap extends ModelComponent{
	protected MapCellArray myCells;
	protected MapCellFactory myFactory;
	protected List<MapCellObject> myMapCellObjects;
	protected List<Robot> myRobots;
	protected List<RobotFactory> myFactories;
	protected ResourceManager myManager;
	protected int x;
	protected int y;
	
	protected int xHover;
	protected int yHover;
	
	protected int xSelect;
	protected int ySelect;
	
	protected int myWidth;
	protected int myHeight;
	
	protected boolean wasAdded;
	protected State myState;
	public ModelMap(int width, int height, ResourceManager m, State s){
		myManager = m;
		List<List<MapCell>> myList = new ArrayList<List<MapCell>>();
		for(int i=0; i<width; i++){
			List<MapCell> newList = new ArrayList<MapCell>();
			for(int j=0; j<height; j++){
				newList.add(null);
			}
			myList.add(newList);
		}
		myState = s;
		myWidth = width;
		myHeight = height;
		myCells = new MapCellArray(myList);
		myFactory = new MapCellFactory();
		myFactories = new ArrayList<RobotFactory>();
		myMapCellObjects = new ArrayList<MapCellObject>();
		myRobots = new ArrayList<Robot>();
	}
	public void step() {
		for(MapCellObject m: myMapCellObjects){
			m.step();
		}
	}
	public MapCell getCell(int i, int j){
		return myCells.getCell(i, j);
	}
	public ResourceManager getResources(){
		return myManager;
	}
	public void addObject(MapCellObject m, int x, int y){
		wasAdded = true;
		myMapCellObjects.add(m);
		if(m instanceof Robot) myRobots.add((Robot)m);
		if(m instanceof RobotFactory) myFactories.add((RobotFactory)m);
		m.addToMap(this, x, y);
	}
	protected void setResources(int i, int j){
		myCells.getCell(i, j).setOil(getOilImage().getRGB(i, j)>>16 & 255);
		int counter = 0;
		for(BufferedImage b: getOreImages()){
			if(b==null) break;
			int value = getOreImages()[counter].getRGB(i, j)>>16 & 255;
			if(value>250){ //common
				myCells.getCell(i,j).setOre(8, counter);
			}
			else if(value>200) {// uncommon
				myCells.getCell(i,j).setOre(4,counter);
			}
			else if(value>150){ //rare
				myCells.getCell(i,j).setOre(1,counter);
			}
			else if(value>100){ // super rare
				myCells.getCell(i,j).setOre(.2,counter);
			}
			else if(value>50){ // ultra rare
				myCells.getCell(i,j).setOre(.05,counter);
			}
			else if(value>10){ // mega rare
				myCells.getCell(i,j).setOre(.01,counter);
			}
			else if (value > 1){// mythical
				myCells.getCell(i,j).setOre(.002, counter);
			}
			counter++;
		}
		myCells.getCell(i,j).setGems(getGemImage().getRGB(i, j)>>16 & 255);
	}
	protected abstract BufferedImage getGemImage();
	
	protected abstract BufferedImage getOilImage();
	
	protected abstract BufferedImage[] getOreImages();
	
	
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
		return xHover;
	}
	public void setXTile(int x){
		xHover = x;
	}
	public int getYTile(){
		return yHover;
	}
	public void setYTile(int y){
		yHover = y;
	}
	public int getXSelect(){
		return xSelect;
	}
	public void setXSelect(int x){
		xSelect = x;
	}
	public int getYSelect(){
		return ySelect;
	}
	public void setYSelect(int y){
		ySelect = y;
	}
	public List<MapCellObject> getObjects(){
		return myMapCellObjects;
	}
	public MapCellObject getObject(int x, int y){
		for(MapCellObject m: myCells.getCell(x, y).getObjects()){
			return m;
		}
		return null;
	}
	public MapCell getCurrentHighlightedCell(){
		if(xHover==-1 || yHover == -1)
			return null;
		return myCells.getCell(xHover, yHover);
	}
	public MapCell getSelectedCell(){
		if(xSelect==-1 || ySelect == -1)
			return null;
		return myCells.getCell(xSelect, ySelect);
	}
	public MapCellObject getSelectedObject(){
		if(xSelect==-1 || ySelect == -1)
			return null;
		return getObject(xSelect, ySelect);
	}
	public int getWidth(){
		return myWidth;
	}
	public int getHeight(){
		return myHeight;
	}
	public void undoHighlight() {
		xHover = -1;
		yHover = -1;
	}
	public void loadPaths(Map<MapCell, List<Character>> myPaths, int x, int y, int cap, int maxDistCost) {
		LinkedList<Character> u = new LinkedList<Character>();
		u.add('u');
		loadPaths(myPaths, x, y-1, cap, maxDistCost, u);
		LinkedList<Character> d = new LinkedList<Character>();
		d.add('d');
		loadPaths(myPaths, x, y+1, cap, maxDistCost, d);
		LinkedList<Character> l = new LinkedList<Character>();
		l.add('l');
		loadPaths(myPaths, x-1, y, cap, maxDistCost, l);
		LinkedList<Character> r = new LinkedList<Character>();
		r.add('r');
		loadPaths(myPaths, x+1, y, cap, maxDistCost, r);
	}
	public void loadPaths(Map<MapCell, List<Character>> myPaths, int x, int y, int cap, int maxDistCost, List<Character> c){
		if(x < 0 || y < 0 || x >= myWidth || y >= myHeight){
			return;
		}
		MapCell currentCell = myCells.getCell(x, y);
		if(!currentCell.isPassable()){
			return;
		}
		if(getFactoryCost(x,y) > maxDistCost){
			return;
		}
		if(c.size() > cap){
			return;
		}
		if(!myPaths.containsKey(currentCell)){
			myPaths.put(currentCell, c);
		}
		else{
			if(myPaths.get(currentCell).size() > c.size()){
				myPaths.put(currentCell, c);
			}
			else{
				return;
			}
		}
		LinkedList<Character> u = new LinkedList<Character>();
		u.addAll(c);
		u.add('u');
		loadPaths(myPaths, x, y-1, cap, maxDistCost, u);
		LinkedList<Character> d = new LinkedList<Character>();
		d.addAll(c);
		d.add('d');
		loadPaths(myPaths, x, y+1, cap, maxDistCost, d);
		LinkedList<Character> l = new LinkedList<Character>();
		l.addAll(c);
		l.add('l');
		loadPaths(myPaths, x-1, y, cap, maxDistCost, l);
		LinkedList<Character> r = new LinkedList<Character>();
		r.addAll(c);
		r.add('r');
		loadPaths(myPaths, x+1, y, cap, maxDistCost, r);
	}
	public List<Robot> getRobots() {
		return myRobots;
	}
	public void removeObject(MapCellObject m) {
		myMapCellObjects.remove(m);
		if(m instanceof Robot) myRobots.remove((Robot)m);
		m.removeFromMap(this);
	}
	public int getFactoryDistance(int x, int y){
		int minDist = Integer.MAX_VALUE;
		for(RobotFactory f : myFactories){
			if(Math.abs(x - f.getX()) + Math.abs(y - f.getY()) < minDist)
				minDist = Math.abs(x - f.getX()) + Math.abs(y - f.getY());
		}
		return minDist;
	}
	public int getFactoryCost(int x, int y){
		double dist = (double) getFactoryDistance(x,y);
		int cost = (int)Math.pow(dist/10, 4);
		return cost;
	}
	public State getState(){
		return myState;
	}
	public boolean acknowledgeAdded() {
		if(wasAdded==true){
			wasAdded = false;
			return true;
		}
		return false;
	}
}

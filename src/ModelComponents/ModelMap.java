package ModelComponents;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Model.MapCellFactory;

public abstract class ModelMap extends ModelComponent{
	protected MapCellArray myCells;
	protected MapCellFactory myFactory;
	protected List<MapCellObject> myMapCellObjects;
	protected ResourceManager myManager;
	protected int x;
	protected int y;
	
	protected int xHover;
	protected int yHover;
	
	protected int xSelect;
	protected int ySelect;
	
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
		myMapCellObjects = new ArrayList<MapCellObject>();
	}
	public MapCell getCell(int i, int j){
		return myCells.getCell(i, j);
	}
	public ResourceManager getResources(){
		return myManager;
	}
	public void addObject(MapCellObject m, int x, int y){
		myMapCellObjects.add(m);
		m.addToMap(this, x, y);
	}
	protected void setResources(int i, int j){
		myCells.getCell(i, j).setOil(getOilImage().getRGB(i, j)>>16 & 255);
		myCells.getCell(i, j).setOre(getOreImage().getRGB(i, j)>>16 & 255);
	}
	protected abstract BufferedImage getOilImage();
	
	protected abstract BufferedImage getOreImage();
	
	
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
	public void loadPaths(Map<MapCell, List<Character>> myPaths, int x, int y, int cap) {
		LinkedList<Character> u = new LinkedList<Character>();
		u.add('u');
		loadPaths(myPaths, x, y-1, cap, u);
		LinkedList<Character> d = new LinkedList<Character>();
		d.add('d');
		loadPaths(myPaths, x, y+1, cap, d);
		LinkedList<Character> l = new LinkedList<Character>();
		l.add('l');
		loadPaths(myPaths, x-1, y, cap, l);
		LinkedList<Character> r = new LinkedList<Character>();
		r.add('r');
		loadPaths(myPaths, x+1, y, cap, r);
	}
	public void loadPaths(Map<MapCell, List<Character>> myPaths, int x, int y, int cap, List<Character> c){
		if(x < 0 || y < 0 || x >= myWidth || y >= myHeight){
			return;
		}
		MapCell currentCell = myCells.getCell(x, y);
		if(!currentCell.isPassable()){
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
		loadPaths(myPaths, x, y-1, cap, u);
		LinkedList<Character> d = new LinkedList<Character>();
		d.addAll(c);
		d.add('d');
		loadPaths(myPaths, x, y+1, cap, d);
		LinkedList<Character> l = new LinkedList<Character>();
		l.addAll(c);
		l.add('l');
		loadPaths(myPaths, x-1, y, cap, l);
		LinkedList<Character> r = new LinkedList<Character>();
		r.addAll(c);
		r.add('r');
		loadPaths(myPaths, x+1, y, cap, r);
	}
}

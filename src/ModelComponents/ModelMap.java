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
	public List<Robot> getRobots(){
		return myRobots;
	}
	public Robot getRobot(int x, int y){
		for(MapCellObject m: myCells.getCell(x, y).getObjects()){
			if(m instanceof Robot)
				return (Robot)m;
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
	public Robot getSelectedRobot(){
		if(xSelect==-1 || ySelect == -1)
			return null;
		return getRobot(xSelect, ySelect);
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
	public List<Character> getPath(int startX, int startY, int endX, int endY) {
		List<Character> possibleRoutes = new ArrayList<Character>();
		possibleRoutes.add('u');
		possibleRoutes.add('r');
		possibleRoutes.add('d');
		possibleRoutes.add('l');
		if(endX - startX < 0){
			possibleRoutes.remove((Character)'l');
			possibleRoutes.add(0, 'l');
		}
		if(endY - startY > 0){
			possibleRoutes.remove((Character)'d');
			possibleRoutes.add(0, 'd');
		}
		for(int i=0; i<4; i++){
			char c = possibleRoutes.get(i);
			List<Character> possiblePath = null;
			if(c=='u' && startY - 1 >= 0){
				possiblePath = buildPath(startX, startY-1, endX, endY, possibleRoutes.get(i), 1);
			}
			if(c=='d' && startY + 1 < myHeight){
				possiblePath = buildPath(startX, startY+1, endX, endY, possibleRoutes.get(i), 1);
			}
			if(c=='l' && startX - 1 >= 0){
				possiblePath = buildPath(startX-1, startY, endX, endY, possibleRoutes.get(i), 1);
			}
			if(c=='r' && startX + 1 < myWidth){
				possiblePath = buildPath(startX+1, startY, endX, endY, possibleRoutes.get(i), 1);
			}
			if(possiblePath!=null){
				return possiblePath;
			}
		}
		return null;
	}
	private List<Character> buildPath(int startX, int startY, int endX, int endY, char c, int depth){
		// Base Case
		if(!myCells.getCell(startX, startY).isPassable()){
			return null;
		}
		if(depth > 20){
			return null;
		}
		if(startX == endX && startY == endY){
			List<Character> result = new ArrayList<Character>();
			result.add(c);
			return result;
		}
		
		// Induction
		List<Character> possibleRoutes = new ArrayList<Character>();
		possibleRoutes.add('u');
		possibleRoutes.add('r');
		possibleRoutes.add('d');
		possibleRoutes.add('l');
		if(endX - startX < 0){
			possibleRoutes.remove((Character)'l');
			possibleRoutes.add(0, 'l');
		}
		if(endY - startY > 0){
			possibleRoutes.remove((Character)'d');
			possibleRoutes.add(0, 'd');
		}
		for(int i=0; i<4; i++){
			char cc = possibleRoutes.get(i);
			List<Character> possiblePath = null;
			if(c=='u' && startY - 1 >= 0){
				possiblePath = buildPath(startX, startY-1, endX, endY, possibleRoutes.get(i), depth+1);
			}
			if(c=='d' && startY + 1 < myHeight){
				possiblePath = buildPath(startX, startY+1, endX, endY, possibleRoutes.get(i), depth+1);
			}
			if(c=='l' && startX - 1 >= 0){
				possiblePath = buildPath(startX-1, startY, endX, endY, possibleRoutes.get(i), depth+1);
			}
			if(c=='r' && startX + 1 < myWidth){
				possiblePath = buildPath(startX+1, startY, endX, endY, possibleRoutes.get(i), depth+1);
			}
			if(possiblePath!=null){
				ArrayList<Character> result = new ArrayList<Character>();
				result.add(c);
				result.addAll(possiblePath);
				return result;
			}
		}
		return null;
	}
}

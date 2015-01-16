package ModelComponents;

import java.util.ArrayList;
import java.util.List;

import Model.MapCellFactory;

public abstract class Map extends ModelComponent{
	protected MapCellArray myCells;
	protected MapCellFactory myFactory;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public Map(int width, int height){
		List<List<MapCell>> myList = new ArrayList<List<MapCell>>();
		for(int i=0; i<width; i++){
			List<MapCell> newList = new ArrayList<MapCell>();
			for(int j=0; j<height; j++){
				newList.add(null);
			}
			myList.add(newList);
		}
		myCells = new MapCellArray(myList);
		myFactory = new MapCellFactory();
	}
	public MapCell getCell(int i, int j){
		return myCells.getCell(i, j);
	}
	public int getX(){
		return x;
	}
	public void setX(int xx){
		x = xx;
	}
	public int getY(){
		return y;
	}
	public void setY(int yy){
		y = yy;
	}
}

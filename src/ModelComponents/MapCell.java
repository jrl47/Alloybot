package ModelComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.OreData;

public class MapCell {
	public static final String GRASS = "grass";
	public static final String DIRT = "dirt";
	public static final String SMALL_ROCKS = "srocks";
	public static final String LARGE_ROCKS = "lrocks";
	public static final String WATER = "water";
	public static final String FLOWERS = "flowers";
	public static final String BRICKS = "bricks";
	public static final String SHOALS = "shoals";
	public static final String FOREST = "forest";
	
	private List<MapCellObject> myObjects;
	private boolean impassable;
	private String myID;
	private int myX;
	private int myY;
	private int myOil;
	private int[] myOre;
	public MapCell(String id, int x, int y){
		myX = x;
		myY = y;
		myID = id;
		myObjects = new ArrayList<MapCellObject>();
		myOre = new int[OreData.NUMBER_OF_ORES];
	}
	public String getID(){
		return myID;
	}
	public int getX(){
		return myX;
	}
	public int getY(){
		return myY;
	}
	public int getOil(){
		return myOil;
	}
	public int getOre(int i){
		return myOre[i];
	}
	public void setOil(int o){
		myOil = o;
	}
	public void setOre(int o, int index) {
		myOre[index] = o;
	}
	public List<MapCellObject> getObjects(){
		return myObjects;
	}
	public void addObject(MapCellObject m){
		myObjects.add(m);
	}
	public void removeObject(MapCellObject m){
		myObjects.remove(m);
	}
	public void setImpassable(){
		impassable = true;
	}
	public boolean isPassable(){
		if(impassable){
			return false;
		}
		for(MapCellObject m : myObjects){
			if(!m.isPassable())
				return false;
		}
		return true;
	}
}

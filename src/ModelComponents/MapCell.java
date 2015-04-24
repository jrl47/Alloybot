package ModelComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	private int myGems;
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
	public void incrementOre(ResourceManager r, int scalar){
		Random rand = new Random();
		int random = rand.nextInt(100);
		int index = -1;
		int counter = 0;
		for(int i=0; i<myOre.length; i++){
			if(random >= counter && random < counter + myOre[i])
				index = i;
			counter+=myOre[i];
		}
		if(index!=-1){
			r.setOre(r.getOre(index) + scalar, index);
		}
	}
	public void incrementGems(ResourceManager r, int scalar) {
		Random rand = new Random();
		boolean add = false;
		int random = rand.nextInt(100);
		if(random < myGems)
			add = true;
		if(add){
			r.setGems(r.getGems() + scalar);
		}
	}
	public int getOil(){
		return myOil;
	}
	public void setOil(int o){
		myOil = o;
	}
	public int getOre(int i){
		return myOre[i];
	}
	public void setOre(int o, int index) {
		myOre[index] = o;
	}
	public int getGems(){
		return myGems;
	}
	public void setGems(int i) {
		myGems = i;
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

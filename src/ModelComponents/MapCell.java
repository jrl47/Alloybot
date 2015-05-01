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
	private boolean unbuildable;
	private String myID;
	private int myX;
	private int myY;
	private int myOil;
	private double[] myOre;
	private int myGems;
	public MapCell(String id, int x, int y){
		myX = x;
		myY = y;
		myID = id;
		myObjects = new ArrayList<MapCellObject>();
		myOre = new double[OreData.NUMBER_OF_ORES];
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
	public void incrementOre(ResourceManager r, Ore oreUsed){
		int oreEfficiency = oreUsed.getMyOre();
		int diversity = oreUsed.getMyDiversity();
		int luck = oreUsed.getMyLuck();
		Random rand = new Random();
		double random = rand.nextDouble()*100.0;
		int index = -1;
		double counter = 0;
		System.out.println(Math.log10(luck));
		for(int i=0; i<myOre.length; i++){
//			System.out.println();
			if(random >= counter && random < counter + Math.min(myOre[i] + Math.max(myOre[i]*Math.log10(luck), 0), 10)){
				index = i;
			}
			counter+=Math.min(myOre[i] + myOre[i]*Math.log10(luck), 10);
		}
		if(index!=-1){
			Ore o = OreData.getOreObject(index);
			if((o.getMyPureIndex()/7 == oreUsed.getMyPureIndex()) || (o.getMyPureIndex()/7 != oreUsed.getMyPureIndex() && diversity>10)){
				r.setOre(r.getOre(index) + oreEfficiency, index);
			}
			else{
				r.setOre(r.getOre(index) + (oreEfficiency/2) + 1, index);
			}
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
	public double getOre(int i){
		return myOre[i];
	}
	public void setOre(double d, int index) {
		myOre[index] = d;
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
	public void setUnbuildable(){
		unbuildable = true;
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
	public boolean isBuildable() {
		if(impassable){
			return false;
		}
		for(MapCellObject m : myObjects){
			if(!m.isPassable())
				return false;
		}
		return !unbuildable;
	}
}

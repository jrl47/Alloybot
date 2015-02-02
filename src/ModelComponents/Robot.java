package ModelComponents;

import Model.ResourceManager;

public class Robot extends MapCellObject{
	
	private ModelMap myMap;
	private MapCell myLocation;
	private int myX;
	private int myY;
	private ResourceManager myResources;
	private boolean enabled;
	public Robot(ModelMap map, ResourceManager resources, int x, int y){
		myMap = map;
		myResources = resources;
		myX = x;
		myY = y;
		myLocation = myMap.getCell(myX, myY);
		myLocation.addObject(this);
	}
	public void step(){
		if(enabled){
			myResources.setOil(myResources.getOil() + myLocation.getOil());
		}
	}
	public void enabled() {
		enabled = true;
	}
	public void disabled(){
		enabled = false;
	}
	public void move(int x, int y){
		myLocation.removeObject(this);
		myX = x;
		myY = y;
		myLocation = myMap.getCell(myX, myY);
		myLocation.addObject(this);
		enabled = false;
	}
	public int getX(){
		return myX;
	}
	public int getY(){
		return myY;
	}
}

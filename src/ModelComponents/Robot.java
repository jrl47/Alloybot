package ModelComponents;

import java.util.ArrayList;
import java.util.List;


public class Robot extends MapCellObject{
	
	private ModelMap myMap;
	private MapCell myLocation;
	private int myX;
	private int myY;
	private ResourceManager myResources;
	private List<ModelButton> myManagerButtons;
	private boolean enabled;
	public Robot(){
		myManagerButtons = new ArrayList<ModelButton>();
	}
	public void addToMap(ModelMap m, int x, int y){
		myX = x;
		myY = y;
		myMap = m;
		myResources = m.getResources();
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
	public void addButton(ModelButton b){
		myManagerButtons.add(b);
	}
	public List<ModelButton> getButtons(){
		return myManagerButtons;
	}
	@Override
	public void respond() {

	}
	public void deselect() {
		myMap.setXSelect(-1);
		myMap.setYSelect(-1);
	}
}

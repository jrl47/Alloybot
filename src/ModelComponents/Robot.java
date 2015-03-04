package ModelComponents;

import java.util.ArrayList;
import java.util.List;

import Controller.Game;
import Model.Model;

public class Robot extends MapCellObject{
	
	private MapCell myLocation;
	private int myX;
	private int myY;
	private ResourceManager myResources;
	private List<ModelButton> myManagerButtons;
	private boolean enabled;
	private boolean readyToMove;
	public Robot(){
		super(false);
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
		if(enabled && (Game.ticks % Model.TICK_SCALAR )==0){
			myResources.setOil(myResources.getOil() + myLocation.getOil());
		}
	}
	public void enabled() {
		enabled = true;
	}
	public void disabled(){
		enabled = false;
	}
	public void prepareMove(){
		readyToMove = true;
	}
	public boolean movable(){
		return readyToMove;
	}
	public void stopMove(){
		readyToMove = false;
	}
	public boolean move(int x, int y){
		if(myMap.getCell(x, y).getObjects().size()!=0)
			return false;
		myLocation.removeObject(this);
		myResources.setOil(myResources.getOil() - 1000*(Math.abs(myX - x) + Math.abs(myY - y)));
		myX = x;
		myY = y;
		myLocation = myMap.getCell(myX, myY);
		myLocation.addObject(this);
		enabled = false;
		readyToMove = false;
		return true;
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
		stopMove();
	}
	public void select() {
		myMap.setXSelect(myX);
		myMap.setYSelect(myY);
	}
}

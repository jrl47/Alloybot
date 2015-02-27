package ModelComponents;

import java.util.ArrayList;
import java.util.List;


public class Robot extends MapCellObject{
	
	private ModelMap myMap;
	private MapCell myLocation;
	private int myX;
	private int myY;
	private char myDirection;
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
		myDirection = 'd';
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
	public void setDirection(char c){
		myDirection = c;
	}
	public char getDirection(){
		return myDirection;
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
	public void move(int x, int y){
		myLocation.removeObject(this);
		myResources.setOil(myResources.getOil() - 1000*(Math.abs(myX - x) + Math.abs(myY - y)));
		myX = x;
		myY = y;
		myLocation = myMap.getCell(myX, myY);
		myLocation.addObject(this);
		enabled = false;
		readyToMove = false;
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
}

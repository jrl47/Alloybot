package ModelComponents;

public abstract class MapCellObject extends ModelComponent{

	protected ModelMap myMap;
	protected MapCell myLocation;
	protected ResourceManager myResources;
	boolean impassable;
	protected int myX;
	protected int myY;
	protected boolean readyToMove;
	protected boolean isDestroyed;
	public MapCellObject(boolean b){
		impassable = b;
	}
	public boolean isPassable() {
		return impassable;
	}
	public boolean isSelected(){
		return (!this.isDestroyed() && myMap.getSelectedCell()!=null && myMap.getSelectedCell().getObjects()!=null && myMap.getSelectedCell().getObjects().contains(this));
	}
	public int getX(){
		return myX;
	}
	public int getY(){
		return myY;
	}
	public void addToMap(ModelMap m, int x, int y){
		myX = x;
		myY = y;
		myMap = m;
		myResources = m.getResources();
		myLocation = myMap.getCell(myX, myY);
		myLocation.addObject(this);
	}
	public void removeFromMap(ModelMap m){
		myX = -1;
		myY = -1;
		myResources = null;
		myLocation.removeObject(this);
		myLocation = null;
		myMap = null;
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
	public void prepareMove(){
		readyToMove = true;
	}
	public boolean movable(){
		return readyToMove;
	}
	public void stopMove(){
		readyToMove = false;
	}
	public void destroy() {
		readyToMove = false;
		myMap.removeObject(this);
		isDestroyed = true;
	}
	public boolean isDestroyed(){
		return isDestroyed;
	}
}

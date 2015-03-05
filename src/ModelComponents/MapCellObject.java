package ModelComponents;

public abstract class MapCellObject extends ModelComponent{

	protected ModelMap myMap;
	protected MapCell myLocation;
	protected ResourceManager myResources;
	boolean impassable;
	protected int myX;
	protected int myY;
	public MapCellObject(boolean b){
		impassable = b;
	}
	public boolean isPassable() {
		return impassable;
	}
	public boolean isSelected(){
		return (myMap.getSelectedCell()!=null && myMap.getSelectedCell().getObjects()!=null && myMap.getSelectedCell().getObjects().contains(this));
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
}

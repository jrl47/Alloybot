package ModelComponents;

public abstract class MapCellObject extends ModelComponent{

	protected ModelMap myMap;
	boolean impassable;
	public MapCellObject(boolean b){
		impassable = b;
	}
	public boolean isPassable() {
		return !impassable;
	}
	public boolean isSelected(){
		return (myMap.getSelectedCell()!=null && myMap.getSelectedCell().getObjects()!=null && myMap.getSelectedCell().getObjects().contains(this));
	}

}

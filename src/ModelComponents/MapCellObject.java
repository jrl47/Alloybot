package ModelComponents;

public abstract class MapCellObject extends ModelComponent{

	boolean impassable;
	public MapCellObject(boolean b){
		impassable = b;
	}
	public boolean isPassable() {
		return !impassable;
	}

}

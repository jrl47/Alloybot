package ViewComponents;

import java.awt.Graphics2D;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import View.DeciduousTileManager;

public abstract class ViewMapObject {
	
	protected MapCellObject myMapObject;
	protected ModelMap myMap;
	public ViewMapObject(MapCellObject m, ModelMap mm){
		myMapObject = m;
		myMap = mm;
	}
	public abstract void trigger(int newX, int newY);
	
	public abstract void draw(Graphics2D g, DeciduousTileManager manager, ViewMapAnimationHandler animation);
	public MapCellObject getMapObject(){
		return myMapObject;
	}
}

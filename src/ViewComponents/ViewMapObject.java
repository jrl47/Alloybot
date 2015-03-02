package ViewComponents;

import java.awt.Graphics2D;

import ModelComponents.MapCellObject;
import View.DeciduousTileManager;

public abstract class ViewMapObject {
	
	protected MapCellObject myMapObject;
	public ViewMapObject(MapCellObject m){
		myMapObject = m;
	}
	public abstract void trigger(int newX, int newY);
	
	public abstract void draw(Graphics2D g, DeciduousTileManager manager, ViewMapAnimationHandler animation);
}

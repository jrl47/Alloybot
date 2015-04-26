package ViewComponents;

import java.awt.Graphics2D;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import View.DeciduousTileManager;

public class ViewRobotFactory extends ViewMapObject{

	private boolean triggered;
	private int timer;
	public ViewRobotFactory(MapCellObject m, ModelMap mm) {
		super(m, mm);
	}

	@Override
	public void trigger(int newX, int newY) {
		myMapObject.respond();
	}

	@Override
	public void draw(Graphics2D g, DeciduousTileManager manager,
			ViewMapAnimationHandler animation) {
		if(myMapObject.isSelected() && !triggered){
//			myMapObject.deselect();
			triggered = true;
			trigger(0,0);
		}
		else if(triggered){
			timer++;
			if(timer>2) {myMapObject.deselect(); triggered = false; timer = 0;}
		}
		
		g.drawImage(manager.generateRobotFactory(), myMapObject.getX()*16 - animation.getOriginX(),
				myMapObject.getY()*16 - animation.getOriginY(), null);
	}
}

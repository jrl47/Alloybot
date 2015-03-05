package ViewComponents;

import java.awt.Graphics2D;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import View.DeciduousTileManager;

public class ViewRobotFactory extends ViewMapObject{

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
		g.drawImage(manager.generateRobotFactory(), myMapObject.getX()*16 - animation.getOriginX(),
				myMapObject.getY()*16 - animation.getOriginY(), null);
	}
}

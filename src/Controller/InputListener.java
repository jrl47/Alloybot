package Controller;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;



import View.ScreenBuilder;
import ViewComponents.ViewComponent;

/**
 * 
 * @author Jacob
 * 
 * Keeps track of all currently pressed keys.
 */
public class InputListener implements MouseListener{
	
	MouseEvent mostRecentEvent = null;
	boolean clicked = false;
	boolean entered = false;
	boolean exited = false;
	public void step(List<ViewComponent> list) {
		if(clicked){
			for(ViewComponent v: list){
				Shape s = v.getBounds();
				if(s.contains(mostRecentEvent.getX(), mostRecentEvent.getY())){
					v.respond();
				}
			}
		}
		mostRecentEvent = null;
		clicked = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mostRecentEvent = arg0;
		clicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		mostRecentEvent = arg0;
		entered = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		mostRecentEvent = arg0;
		exited = true;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}

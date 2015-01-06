package Controller;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;





import View.ScreenBuilder;
import ViewComponents.StartButton;
import ViewComponents.ViewComponent;

/**
 * 
 * @author Jacob
 * 
 * Keeps track of all currently pressed keys.
 */
public class InputListener implements MouseListener, MouseMotionListener{
	
	MouseEvent mostRecentEvent = null;
	boolean clicked = false;
	boolean moved = false;
	public void step(List<ViewComponent> list) {
		if(clicked){
			for(ViewComponent v: list){
				Shape s = v.getBounds();
				if(s.contains(mostRecentEvent.getX(), mostRecentEvent.getY())){
					v.respond();
				}
			}
		}
		if(moved){
			for(ViewComponent v: list){
				Shape s = v.getBounds();
				if(s.contains(mostRecentEvent.getX(), mostRecentEvent.getY())){
					v.setHover(true);
				}
				else{
					v.setHover(false);
				}
			}
		}
		mostRecentEvent = null;
		clicked = false;
		moved = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mostRecentEvent = arg0;
		clicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mostRecentEvent = arg0;
		moved = true;
	}
}

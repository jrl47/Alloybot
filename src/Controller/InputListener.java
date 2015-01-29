package Controller;

import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;



import View.ScreenBuilder;
import View.View;
import ViewComponents.InputSensitive;
import ViewComponents.ViewComponent;

/**
 * 
 * @author Jacob
 * 
 * Keeps track of all currently pressed keys.
 */
public class InputListener implements MouseListener, MouseMotionListener{
	
	private MouseEvent mostRecentEvent = null;
	private boolean clicked = false;
	private boolean moved = false;
	public void step(List<ViewComponent> list) {
		if(clicked){
			for(ViewComponent v: list){
				Shape s = v.getBounds();
				if(s.contains(mostRecentEvent.getX()/(View.scale), mostRecentEvent.getY()/(View.scale))){
					v.respond();
					if(v instanceof InputSensitive){
						((InputSensitive) v).useInput((int)(mostRecentEvent.getX()/View.scale)-(int)(s.getBounds().x),
								(int)(mostRecentEvent.getY()/View.scale)-(int)(s.getBounds().y), true);
					}
				}
			}
		}
		if(moved){
			for(ViewComponent v: list){
				Shape s = v.getBounds();
				if(s.contains(mostRecentEvent.getX()/(View.scale), mostRecentEvent.getY()/(View.scale))){
					v.setHover(true);
					if(v instanceof InputSensitive){
						((InputSensitive) v).useInput((int)(mostRecentEvent.getX()/View.scale)-(int)(s.getBounds().x),
								(int)(mostRecentEvent.getY()/View.scale)-(int)(s.getBounds().y), false);
					}
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
		mostRecentEvent = arg0;
		clicked = true;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		mostRecentEvent = arg0;
		moved = true;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		mostRecentEvent = arg0;
		moved = true;
	}
}

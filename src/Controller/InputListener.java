package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Jacob
 * 
 * Keeps track of all currently pressed keys.
 */
public class InputListener implements KeyListener, MouseListener{
	
	private Set<Integer> myKeys = new HashSet<Integer>();
	public void keyPressed(KeyEvent e) {
		myKeys.add(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		myKeys.remove(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {

	}
	public Set<Integer> getKey(){
		return myKeys;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

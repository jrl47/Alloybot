package ViewComponents;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ModelComponents.MapCell;
import ModelComponents.ModelMap;
import ModelComponents.Robot;
import View.DeciduousTileManager;

public class ViewRobot extends ViewMapObject{

	private Robot myRobot;
	private char myDirection;
	private Map<MapCell, List<Character>> myPaths;
	private List<Character> currentPath;
	private ModelMap myMap;
	private boolean selected;
	private int idleCounter;
	private int movementCounter;
	private Set<MapCell> myMoves;
	public ViewRobot(Robot r, ModelMap map) {
		super(r);
		myRobot = (Robot)myMapObject;
		myDirection = 'd';
		myMap = map;
		myMoves = new HashSet<MapCell>();
		myPaths = new HashMap<MapCell, List<Character>>();
	}
	public Robot getRobot(){
		return myRobot;
	}
	public void setDirection(char c){
		myDirection = c;
	}
	public char getDirection(){
		return myDirection;
	}
	public int getX(){
		return myRobot.getX();
	}
	public int getY(){
		return myRobot.getY();
	}
	private void loadPaths() {
		if(movementCounter <= 0){
			myPaths.clear();
			myMap.loadPaths(myPaths, myRobot.getX(), myRobot.getY(), Math.min(10, myMap.getResources().getOil()/1000));
		}
	}
	public void trigger(int newX, int newY) {
		if(!selected){
			myPaths.clear();
			myMap.loadPaths(myPaths, myRobot.getX(), myRobot.getY(), Math.min(10, myMap.getResources().getOil()/1000));
			selected = true;
			return;
		}
		if(myRobot.movable()){
			if(currentPath == null && myPaths.containsKey(myMap.getCell(newX, newY)) && myPaths.get(myMap.getCell(newX, newY)).size()!=0){
				currentPath = myPaths.get(myMap.getCell(newX, newY));
				if(currentPath!=null && currentPath.size()!=0){
					movementCounter = currentPath.size()*32+32;
				}
			}
		}
	}
	public void draw(Graphics2D g, DeciduousTileManager manager, ViewMapAnimationHandler animation) {
		g.drawImage(manager.generateRobot(idleCounter, myDirection), getRobotX() - animation.getOriginX(),
				getRobotY() - animation.getOriginY(), null);
		if(selected)
			g.drawImage(manager.getHighlightTransparency(), myRobot.getX()*16 - animation.getOriginX(),myRobot.getY()*16 - animation.getOriginY(), null);
		drawMoveRange(g, manager, animation);
		// If it's one of the times the robot is supposed to move, make it move here!
		// aka
		if(movementCounter % 32 == 0 && movementCounter>0)
			doMovement();
		idleCounter++;
		idleCounter = idleCounter % 80;
		if(movementCounter > 0)
			movementCounter--;
//		// If i have an object and it's moveable but we haven't started, load the move
//		for(ViewMapObject m: myMapObjects){
//			if(m instanceof ViewRobot)
//				((ViewRobot)m).loadMove();
//		}
	}
	private void drawMoveRange(Graphics2D g, DeciduousTileManager manager, ViewMapAnimationHandler animation){
		for(MapCell m: myMoves){
			g.drawImage(manager.getHighlightTransparency(),(m.getX())*16 - animation.getOriginX(),
				(m.getY())*16 - animation.getOriginY(), null);
		}
	}
	public void doMovement(){
		if(currentPath.get(0)=='u'){
			myDirection = 'u';
			myRobot.move(myRobot.getX(), myRobot.getY()-1);
		}
		if(currentPath.get(0)=='d'){
			myDirection = 'd';
			myRobot.move(myRobot.getX(), myRobot.getY()+1);
		}
		if(currentPath.get(0)=='l'){
			myDirection = 'l';
			myRobot.move(myRobot.getX(), myRobot.getY()-1);
		}
		if(currentPath.get(0)=='r'){
			myDirection = 'r';
			myRobot.move(myRobot.getX(), myRobot.getY()+1);
		}
		currentPath.remove(0);
	}
	int getRobotX(){
		if(myDirection=='l'){
			return 16*myRobot.getX() + ((movementCounter % 32) / 2);
		}
		if(myDirection=='r'){
			return 16*myRobot.getX() - ((movementCounter % 32) / 2);
		}
		return 16*myRobot.getX();
	}
	int getRobotY(){
		if(myDirection=='u'){
			return 16*myRobot.getY() + ((movementCounter % 32) / 2);
		}
		if(myDirection=='d'){
			return 16*myRobot.getY() - ((movementCounter % 32) / 2);
		}
		return 16*myRobot.getY();
	}
}
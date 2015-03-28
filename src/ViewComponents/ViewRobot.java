package ViewComponents;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Controller.Game;
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
	private int movementCounter;
	private Set<MapCell> myMoves;
	public ViewRobot(Robot r, ModelMap map) {
		super(r, map);
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
		myPaths.clear();
		myMap.loadPaths(myPaths, myRobot.getX(), myRobot.getY(), Math.min(9, myMap.getResources().getOil()/1000));
		myMoves = myPaths.keySet();
	}
	public void trigger(int newX, int newY) {
		if(movementCounter > 0){
			return;
		}
		if(!myRobot.isSelected()){
			loadPaths();
		}
		if(myRobot.movable()){
			if(myPaths.containsKey(myMap.getCell(newX, newY)) && myPaths.get(myMap.getCell(newX, newY)).size()!=0){
				currentPath = myPaths.get(myMap.getCell(newX, newY));
				if(currentPath!=null && currentPath.size()!=0){
					movementCounter = currentPath.size()*32;
				}
			}
			myRobot.deselect();
		}
	}
	public void draw(Graphics2D g, DeciduousTileManager manager, ViewMapAnimationHandler animation) {
		if(myRobot.isSelected()){
			loadPaths();
		}
		g.drawImage(manager.generateRobot((int)Game.ticks % 80, myDirection), getRobotX() - animation.getOriginX(),
				getRobotY() - animation.getOriginY(), null);
		if(myRobot.isSelected()){
			g.drawImage(manager.getHighlightTransparency(), myRobot.getX()*16 - animation.getOriginX(),myRobot.getY()*16 - animation.getOriginY(), null);
			if(myRobot.movable())
				drawMoveRange(g, manager, animation);
		}
		if(movementCounter % 32 == 0 && movementCounter>0)
			doMovement();
		if(movementCounter > 0)
			movementCounter--;
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
			if(!myRobot.move(myRobot.getX(), myRobot.getY()-1)){
				endMove();
				return;
			}
		}
		if(currentPath.get(0)=='d'){
			myDirection = 'd';
			if(!myRobot.move(myRobot.getX(), myRobot.getY()+1)){
				endMove();
				return;
			}
		}
		if(currentPath.get(0)=='l'){
			myDirection = 'l';
			if(!myRobot.move(myRobot.getX()-1, myRobot.getY())){
				endMove();
				return;
			}
		}
		if(currentPath.get(0)=='r'){
			myDirection = 'r';
			if(!myRobot.move(myRobot.getX()+1, myRobot.getY())){
				endMove();
				return;
			}
		}
		currentPath.remove(0);
	}
	
	private void endMove() {
		currentPath.clear();
		movementCounter = 0;
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
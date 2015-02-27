package ViewComponents;

import java.util.List;

import ModelComponents.Robot;

public class ViewMapAnimationHandler {
	
	private double xDirection;
	private double yDirection;
	private double animateXCounter;
	private double animateYCounter;
	private double xCounterDecrement;
	private double yCounterDecrement;
	private int xOrigin;
	private int yOrigin;
	private int prevXOrigin;
	private int prevYOrigin;
	private int robotIdleCounter;
	private int robotMovementCounter;
	private Robot myRobot;
	
	public ViewMapAnimationHandler(){
		
	}
	
	void handleAnimation() {
		robotIdleCounter++;
		robotIdleCounter = robotIdleCounter % 80;
		if(robotMovementCounter > 0)
			robotMovementCounter--;
		if(animateXCounter>0){
			animateXCounter-=xCounterDecrement;
		}
		if(animateYCounter>0){
			animateYCounter-=yCounterDecrement;
		}
		if(animateXCounter==0 && animateYCounter==0){
		xDirection = 0;
		yDirection = 0;
		}
		if(prevXOrigin!=xOrigin){
			animateXCounter = 16;
			xDirection = (int)((prevXOrigin - xOrigin)*1);
		}
		if(prevYOrigin!=yOrigin){
			animateYCounter = 16;
			yDirection = (int)((prevYOrigin - yOrigin)*1);
		}
		if(animateXCounter != animateYCounter){
			if(animateXCounter > animateYCounter){
				xCounterDecrement = 1;
				yCounterDecrement = 1.0*(animateYCounter/animateXCounter);
			}
			else{
				yCounterDecrement = 1;
				xCounterDecrement = 1.0*(animateXCounter/animateYCounter);
			}
		}
		else{
			xCounterDecrement = 1;
			yCounterDecrement = 1;
		}
		prevXOrigin = xOrigin;
		prevYOrigin = yOrigin;
	}
	
	int getOriginX(){
		return xOrigin*16 + (int)(animateXCounter*xDirection);
	}
	int getOriginY(){
		return yOrigin*16 + (int)(animateYCounter*yDirection);
	}
	int pixelsToCellX(int xx){
		return xOrigin + ((xx-ViewMap.BORDER_WIDTH)/16);
	}
	int pixelsToCellY(int yy){
		return yOrigin + ((yy-ViewMap.BORDER_WIDTH)/16);
	}
	boolean inAnimation(){
		if(animateXCounter==0 && animateYCounter==0){
			return false;
		}
		return true;
	}
	int getX(){
		return xOrigin;
	}
	void setX(int xx){
		xOrigin = xx;
	}
	int getPrevX(){
		return prevXOrigin;
	}
	void setPrevX(int xx){
		prevXOrigin = xx;
	}
	int getY(){
		return yOrigin;
	}
	void setY(int yy){
		yOrigin = yy;
	}
	int getPrevY(){
		return prevYOrigin;
	}
	void setPrevY(int yy){
		prevYOrigin = yy;
	}
	int getRobotX(Robot r){
		if(r!=myRobot){
			return 16*r.getX();
		}
		if(myRobot.getDirection()=='l'){
			return 16*myRobot.getX() - ((robotMovementCounter % 32) / 2);
		}
		if(myRobot.getDirection()=='r'){
			return 16*myRobot.getX() + ((robotMovementCounter % 32) / 2);
		}
		return 16*myRobot.getX();
	}
	int getRobotY(Robot r){
		if(r!=myRobot){
			return 16*r.getY();
		}
		if(myRobot.getDirection()=='u'){
			return 16*myRobot.getY() - ((robotMovementCounter % 32) / 2);
		}
		if(myRobot.getDirection()=='d'){
			return 16*myRobot.getY() + ((robotMovementCounter % 32) / 2);
		}
		return 16*myRobot.getY();
	}

	public int getRobotAnimCounter() {
		return robotIdleCounter;
	}
	public void setMovementCounter(int m){
		robotMovementCounter = m;
	}
	public boolean getMovementStatus(){
		return (robotMovementCounter % 32 == 0 && robotMovementCounter!=0);
	}
	public boolean isDone(){
		return (robotMovementCounter<=0);
	}

	public void setRobot(Robot currentlySelectedRobot) {
		myRobot = currentlySelectedRobot;
	}
}

package ViewComponents;

public class ViewMapAnimationHandler {
	
	private double xDirection;
	private double yDirection;
	private double animateXCounter;
	private double animateYCounter;
	private double xCounterDecrement;
	private double yCounterDecrement;
	private int x;
	private int y;
	private int prevX;
	private int prevY;
	private int robotAnimCounter;
	
	public ViewMapAnimationHandler(){
		
	}
	
	void handleAnimation() {
		robotAnimCounter++;
		robotAnimCounter = robotAnimCounter % 100;
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
		if(prevX!=x){
			animateXCounter = 16;
			xDirection = (int)((prevX - x)*1);
		}
		if(prevY!=y){
			animateYCounter = 16;
			yDirection = (int)((prevY - y)*1);
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
		prevX = x;
		prevY = y;
	}
	
	int getAnimateX(){
		return x*16 + (int)(animateXCounter*xDirection);
	}
	int getAnimateY(){
		return y*16 + (int)(animateYCounter*yDirection);
	}
	int pixelsToCellX(int xx){
		return x + ((xx-ViewMap.BORDER_WIDTH)/16);
	}
	int pixelsToCellY(int yy){
		return y + ((yy-ViewMap.BORDER_WIDTH)/16);
	}
	boolean inAnimation(){
		if(animateXCounter==0 && animateYCounter==0){
			return false;
		}
		return true;
	}
	int getX(){
		return x;
	}
	void setX(int xx){
		x = xx;
	}
	int getPrevX(){
		return prevX;
	}
	void setPrevX(int xx){
		prevX = xx;
	}
	int getY(){
		return y;
	}
	void setY(int yy){
		y = yy;
	}
	int getPrevY(){
		return prevY;
	}
	void setPrevY(int yy){
		prevY = yy;
	}

	public int getRobotAnimCounter() {
		return robotAnimCounter;
	}
}

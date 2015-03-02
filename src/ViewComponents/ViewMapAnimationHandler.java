package ViewComponents;

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

	void handleAnimation() {
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
}

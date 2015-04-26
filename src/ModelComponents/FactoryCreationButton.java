package ModelComponents;

public class FactoryCreationButton extends ModelButton{

	private Robot myRobot;
	private ModelMap myMap;
	private ResourceManager myManager;
	private int mySize;
	private int myOreCost;
	private boolean wasTriggered;
	public FactoryCreationButton(Robot r){
		myMap = r.getMap();
		myRobot = r;
		myManager = myMap.getResources();
	}
	public void respond() {
		wasTriggered = true;
		if(myManager.getGems()>=1
				&& myMap.getCell(myRobot.getX(), myRobot.getY() + 1).getObjects().size()==0){
			myManager.setGems(myManager.getGems() - 1);
			RobotFactory f = new RobotFactory(myMap.getState());
			myMap.addObject(f, myRobot.getX(), myRobot.getY() + 1);
		}
	}
	public boolean wasTriggered(){
		return wasTriggered;
	}
	
}
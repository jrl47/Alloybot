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
		if(myManager.getGems()>=5 && myManager.getOil()>=5000
				&& myMap.getCell(myRobot.getX(), myRobot.getY() + 1).getObjects().size()==0
				&& myMap.getCell(myRobot.getX(), myRobot.getY() + 1).isBuildable()){
			myManager.setGems(myManager.getGems() - 5);
			myManager.setOil(myManager.getOil() - 5000);
			RobotFactory f = new RobotFactory(myMap.getState());
			myMap.addObject(f, myRobot.getX(), myRobot.getY() + 1);
		}
	}
	public boolean wasTriggered(){
		return wasTriggered;
	}
	
}

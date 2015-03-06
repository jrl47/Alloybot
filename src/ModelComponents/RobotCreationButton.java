package ModelComponents;

public class RobotCreationButton extends ModelButton{

	RobotFactory myFactory;
	ModelMap myMap;
	ResourceManager myManager;
	int oilCost;
	int oreCost;
	int gemCost;
	int newOilE;
	int newOreE;
	public RobotCreationButton(ModelMap m, int oilC, int oreC, int gemC, int newOil, int newOre){
		myMap = m;
		myManager = myMap.getResources();
		oilCost = oilC;
		oreCost = oreC;
		gemCost = gemC;
		newOilE = newOil;
		newOreE = newOre;
	}
	public void respond() {
		myFactory = (RobotFactory) myMap.getSelectedObject();
		if(myManager.getOil()>=oilCost && myManager.getOre()>=oreCost && myManager.getGems()>=gemCost
				&& myMap.getCell(myFactory.getX(), myFactory.getY() + 1).getObjects().size()==0){
			myManager.setOil(myManager.getOil() - oilCost);
			myManager.setOre(myManager.getOre() - oreCost);
			myManager.setGems(myManager.getGems() - gemCost);
			Robot r = new Robot(newOilE, newOreE);
			myMap.addObject(r, myFactory.getX(), myFactory.getY() + 1);
		}
	}
	
}

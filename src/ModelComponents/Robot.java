package ModelComponents;

import java.util.ArrayList;
import java.util.List;

import Controller.Game;
import Model.Model;
import Model.OreData;
import ViewComponents.ViewRobot;

public class Robot extends MapCellObject implements Comparable<Robot>{
	
	private List<ModelButton> myManagerButtons;
	private boolean enabled;
	private int oilEfficiency;
	private int oreEfficiency;
	private int gemEfficiency;
	private int diversity;
	private int distance;
	private int luck;
	private int power;
	private int durability;
	private int magic;
	private Ore myOre;
	private int mySize;
	private String myName;
	public Robot(int oreType, int size, ModelMap m){
		super(false);
		myManagerButtons = new ArrayList<ModelButton>();
		mySize = size;
		myOre = OreData.getOreObject(oreType);
		oilEfficiency = processStat(myOre.getMyOil(), size);
		oreEfficiency = processStat(myOre.getMyOre(), size);
		gemEfficiency = processStat(myOre.getMyGems(), size);
		diversity = processStat(myOre.getMyDiversity(), size);
		distance = processStat(myOre.getMyDistance(), size);
		luck = processStat(myOre.getMyLuck(), size);
		power = processStat(myOre.getMyPower(), size);
		durability = processStat(myOre.getMyDurability(), size);
		magic = processStat(myOre.getMyMagic(), size);
		myName = myOre.getMyName();
		addButton(new RobotMoveButton(this));
		addButton(new RobotEnableButton(this));
		addButton(new RobotDeselectButton(this));
		addButton(new RobotStopButton(this));
		addButton(new RobotDestroyButton(this, m.getResources()));
	}
	public void step(){
		if(enabled && (Game.ticks % Model.TICK_SCALAR )==0){
			myResources.setOil(myResources.getOil() + myLocation.getOil()*oilEfficiency);
			for(int i=0; i<OreData.NUMBER_OF_ORES; i++){
				myResources.setOre(myResources.getOre(i) + myLocation.getOre(i)*oreEfficiency, i);
			}
		}
	}
	public int processStat(int base, int size){
		int std = (int)Math.pow(2, base);
		int actualStat = (int) (Math.pow(size, size)*std*size);
		return actualStat;
	}
	public void enabled() {
		enabled = true;
	}
	public void disabled(){
		enabled = false;
	}
	public boolean move(int x, int y){
		if(myMap.getCell(x, y).getObjects().size()!=0)
			return false;
		myLocation.removeObject(this);
		myResources.setOil(myResources.getOil() - 1000*(Math.abs(myX - x) + Math.abs(myY - y)));
		myX = x;
		myY = y;
		myLocation = myMap.getCell(myX, myY);
		myLocation.addObject(this);
		enabled = false;
		readyToMove = false;
		return true;
	}
	public void addButton(ModelButton b){
		myManagerButtons.add(b);
	}
	public List<ModelButton> getButtons(){
		return myManagerButtons;
	}
	@Override
	public void respond() {

	}
	@Override
	public int compareTo(Robot r) {
		if(oreEfficiency > r.oreEfficiency) return -1;
		if(oreEfficiency < r.oreEfficiency) return 1;
		if(oilEfficiency > r.oilEfficiency) return -1;
		if(oilEfficiency < r.oilEfficiency) return 1;
		return 0;
	}
	public int getOreEfficiency() {
		return oreEfficiency;
	}
	public String getName() {
		return myName;
	}
	public int getSize() {
		return mySize;
	}
}

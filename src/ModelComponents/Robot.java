package ModelComponents;

import java.util.ArrayList;
import java.util.List;

import Controller.Game;
import Model.Model;

public class Robot extends MapCellObject implements Comparable<Robot>{
	
	private List<ModelButton> myManagerButtons;
	private boolean enabled;
	private int oilEfficiency;
	private int oreEfficiency;
	public Robot(int oilE, int oreE, ModelMap m){
		super(false);
		myManagerButtons = new ArrayList<ModelButton>();
		oilEfficiency = oilE;
		oreEfficiency = oreE;
		addButton(new RobotMoveButton(this));
		addButton(new RobotEnableButton(this));
		addButton(new RobotDeselectButton(this));
		addButton(new RobotStopButton(this));
		addButton(new RobotDestroyButton(this, m.getResources()));
	}
	public void step(){
		if(enabled && (Game.ticks % Model.TICK_SCALAR )==0){
			myResources.setOil(myResources.getOil() + myLocation.getOil()*oilEfficiency);
			myResources.setOre(myResources.getOre() + myLocation.getOre()*oreEfficiency);
		}
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
}

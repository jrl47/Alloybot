package ModelComponents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controller.Game;
import Model.Model;


public class ResourceManager extends ModelComponent{
	
	private int myOil;
	private int[] myOre;
	private int myGems; 
	private long currentTick;
	private int myOldOilDif;
	private Map<Integer, Integer> myOldOreDif;
	private int myOldGemDif;
	private int myOilDif;
	private Map<Integer, Integer> myOreDif;
	private int myGemDif;
	public ResourceManager(){
		myOil = 150000;
		myOre = new int[100];
		myGems = 1;
		myOldOreDif = new HashMap<Integer, Integer>();
		myOreDif = new HashMap<Integer, Integer>();
	}
	public int getOil() {
		return myOil;
	}
	public int getOre(int index){
		return myOre[index];
	}
	public int getGems(){
		return myGems;
	}
	public void setOil(int oil) {
		updateTick();
		myOilDif += oil - myOil;
		myOil = oil;
	}
	public void setOre(int ore, int index) {
		updateTick();
		if(!myOreDif.containsKey(index)){
			myOreDif.put(index, ore - myOre[index]);
		}
		else{
			myOreDif.put(index, myOreDif.get(index) +  ore - myOre[index]);
		}
		myOre[index] = ore;
	}
	public void setGems(int gems) {
		updateTick();
		myGemDif += gems - myGems;
		myGems = gems;
	}
	private void updateTick() {
		if(currentTick!=Game.ticks - (Game.ticks % Model.TICK_SCALAR)){
			myOldOilDif = myOilDif;
			myOilDif = 0;
			myOldOreDif.clear();
			for(int i : myOreDif.keySet()){
				myOldOreDif.put(i, myOreDif.get(i));
			}
			myOreDif.clear();
			myOldGemDif = myGemDif;
			myGemDif = 0;
			currentTick = Game.ticks - (Game.ticks % Model.TICK_SCALAR);
		}
	}
	public int getOilDif(){
		updateTick();
		return myOldOilDif;
	}
	public Map<Integer, Integer> getOreDif(){
		updateTick();
		return myOldOreDif;
	}
	public int getGemDif(){
		updateTick();
		return myOldGemDif;
	}
	@Override
	public void step() {
		
	}
	@Override
	public void respond() {

	}
}

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
	private int myOilDif;
	private Map<Integer, Integer> myOreDif;
	private int myGemDif;
	public ResourceManager(){
		myOil = 150000;
		myOre = new int[100];
		myGems = 1;
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
		if(currentTick!=Game.ticks - (Game.ticks % Model.TICK_SCALAR)){
			myOilDif = 0;
			myOreDif.clear();
			myGemDif = 0;
			currentTick = Game.ticks;
		}
		myOilDif += oil - myOil;
		myOil = oil;
	}
	public void setOre(int ore, int index) {
		if(currentTick!=Game.ticks - (Game.ticks % Model.TICK_SCALAR)){
			myOilDif = 0;
			myOreDif.clear();
			myGemDif = 0;
			currentTick = Game.ticks;
		}
		if(!myOreDif.containsKey(index)){
			myOreDif.put(index, ore - myOre[index]);
		}
		else{
			myOreDif.put(index, myOreDif.get(index) +  ore - myOre[index]);
		}
		myOre[index] = ore;
	}
	public void setGems(int gems) {
		if(currentTick!=Game.ticks - (Game.ticks % Model.TICK_SCALAR)){
			myOilDif = 0;
			myOreDif.clear();
			myGemDif = 0;
			currentTick = Game.ticks;
		}
		myGemDif += gems - myGems;
		myGems = gems;
	}
	public int getOilDif(){
		return myOilDif;
	}
	public Map<Integer, Integer> getOreDif(){
		return myOreDif;
	}
	public int getGemDif(){
		return myGemDif;
	}
	@Override
	public void step() {
		
	}
	@Override
	public void respond() {

	}
}

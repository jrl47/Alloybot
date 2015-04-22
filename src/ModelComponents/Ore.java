package ModelComponents;

import java.util.ArrayList;
import java.util.List;

import Model.OreData;

public class Ore {

	protected int myOil;
	protected int myOre;
	protected int myGems;
	protected int myDiversity;
	protected int myDistance;
	protected int myLuck;
	protected int myPower;
	protected int myDurability;
	protected int myMagic;
	protected int myIndex;
	protected String myType;
	protected String myName;
	protected String[] parents;
	protected List<Ore> myParents;
	public Ore(int i){
		String dataLine = OreData.oreData[i];
		// Breaks up at colons
		String[] data = dataLine.split(":");
		// This splits up the parameters which are separated by spaces into their own little values, then turns them into floats from strings
		myName = data[0];
		String[] rankIndex = data[1].split("-");
		myType = rankIndex[0];
		myIndex = Integer.parseInt(rankIndex[1]);
		parents = data[2].split("-");
		myOil = Integer.parseInt(data[3]);
		myOre = Integer.parseInt(data[4]);
		myGems = Integer.parseInt(data[5]);
		myDiversity = Integer.parseInt(data[6]);
		myDistance = Integer.parseInt(data[7]);
		myLuck = Integer.parseInt(data[8]);
		myPower = Integer.parseInt(data[9]);
		myDurability = Integer.parseInt(data[10]);
		myMagic = Integer.parseInt(data[11]);
	}
	public void init(){
		myParents = new ArrayList<Ore>();
		for(String s: parents){
			myParents.add(OreData.getOreObject(s));
		}
	}
	public int getMyOil() {
		return myOil;
	}
	public void setMyOil(int myOil) {
		this.myOil = myOil;
	}
	public int getMyOre() {
		return myOre;
	}
	public void setMyOre(int myOre) {
		this.myOre = myOre;
	}
	public int getMyGems() {
		return myGems;
	}
	public void setMyGems(int myGems) {
		this.myGems = myGems;
	}
	public int getMyDiversity() {
		return myDiversity;
	}
	public void setMyDiversity(int myDiversity) {
		this.myDiversity = myDiversity;
	}
	public int getMyDistance() {
		return myDistance;
	}
	public void setMyDistance(int myDistance) {
		this.myDistance = myDistance;
	}
	public int getMyLuck() {
		return myLuck;
	}
	public void setMyLuck(int myLuck) {
		this.myLuck = myLuck;
	}
	public int getMyPower() {
		return myPower;
	}
	public void setMyPower(int myPower) {
		this.myPower = myPower;
	}
	public int getMyDurability() {
		return myDurability;
	}
	public void setMyDurability(int myDurability) {
		this.myDurability = myDurability;
	}
	public int getMyMagic() {
		return myMagic;
	}
	public void setMyMagic(int myMagic) {
		this.myMagic = myMagic;
	}
	public int getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(int myIndex) {
		this.myIndex = myIndex;
	}
	public String getMyType() {
		return myType;
	}
	public void setMyType(String myType) {
		this.myType = myType;
	}
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public List<Ore> getMyParents(){
		return myParents;
	}
}
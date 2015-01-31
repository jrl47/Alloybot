package ModelComponents;

public class MapCell {
	public static final String GRASS = "grass";
	public static final String DIRT = "dirt";
	public static final String SMALL_ROCKS = "srocks";
	public static final String LARGE_ROCKS = "lrocks";
	public static final String WATER = "water";
	public static final String FLOWERS = "flowers";
	public static final String BRICKS = "bricks";
	public static final String SHOALS = "shoals";
	public static final String FOREST = "forest";
	private String myID;
	private int myX;
	private int myY;
	private int myOil;
	public MapCell(String id, int x, int y){
		myX = x;
		myY = y;
		myID = id;
	}
	public String getID(){
		return myID;
	}
	public int getX(){
		return myX;
	}
	public int getY(){
		return myY;
	}
	public int getOil(){
		return myOil;
	}
	public void setOil(int o){
		myOil = o;
	}
}

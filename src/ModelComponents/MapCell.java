package ModelComponents;

public class MapCell {
	public static final String GRASS = "grass";
	public static final String DIRT = "dirt";
	public static final String SMALL_ROCKS = "srocks";
	public static final String LARGE_ROCKS = "lrocks";
	public static final String WATER = "water";
	private String myID;
	private int myX;
	private int myY;
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
}

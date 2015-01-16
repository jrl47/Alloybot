package ModelComponents;

public class MapCell {
	public static final String GRASS = "grass";
	public static final String DIRT = "dirt";
	private String myID;
	public MapCell(String id){
		myID = id;
	}
	public String getID(){
		return myID;
	}
}

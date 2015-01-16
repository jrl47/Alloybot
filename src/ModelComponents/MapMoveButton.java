package ModelComponents;

public class MapMoveButton extends ModelButton{

	private int myXInc;
	private int myYInc;
	private ModelMap myMap;
	public MapMoveButton(ModelMap map, int xinc, int yinc){
		myXInc = xinc;
		myYInc = yinc;
		myMap = map;
	}
	@Override
	public void respond() {
		myMap.incrementX(myXInc);
		myMap.incrementY(myYInc);
	}

}

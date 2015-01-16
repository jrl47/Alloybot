package ModelComponents;

import java.util.List;

public class MapCellArray {
	
	private List<List<MapCell>> myCells;
	public MapCellArray(List<List<MapCell>> cells){
		myCells = cells;
	}
	public MapCell getCell(int i, int j){
		return myCells.get(i).get(j);
	}
	public void setCell(int i, int j, MapCell m){
		myCells.get(i).set(j, m);
	}
}

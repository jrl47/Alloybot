package Model;

import ModelComponents.MapCell;

public class MapCellFactory {

	public MapCell makeGrassCell(int x, int y) {
		return new MapCell(MapCell.GRASS, x, y);
	}

	public MapCell makeDirtCell(int x, int y) {
		return new MapCell(MapCell.DIRT, x, y);
	}

}

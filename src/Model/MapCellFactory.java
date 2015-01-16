package Model;

import ModelComponents.MapCell;

public class MapCellFactory {

	public MapCell makeGrassCell() {
		return new MapCell(MapCell.GRASS);
	}

	public MapCell makeDirtCell() {
		return new MapCell(MapCell.DIRT);
	}

}

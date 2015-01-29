package Model;

import ModelComponents.MapCell;

public class MapCellFactory {

	public MapCell makeGrassCell(int x, int y) {
		return new MapCell(MapCell.GRASS, x, y);
	}

	public MapCell makeDirtCell(int x, int y) {
		return new MapCell(MapCell.DIRT, x, y);
	}
	
	public MapCell makeSmallRocksCell(int x, int y) {
		return new MapCell(MapCell.SMALL_ROCKS, x, y);
	}
	
	public MapCell makeLargeRocksCell(int x, int y) {
		return new MapCell(MapCell.LARGE_ROCKS, x, y);
	}
	public MapCell makeWaterCell(int x, int y) {
		return new MapCell(MapCell.WATER, x, y);
	}

}

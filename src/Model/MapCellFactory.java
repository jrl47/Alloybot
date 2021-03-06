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
		MapCell m = new MapCell(MapCell.SMALL_ROCKS, x, y);
		m.setUnbuildable();
		return m;
	}
	
	public MapCell makeLargeRocksCell(int x, int y) {
		MapCell m = new MapCell(MapCell.LARGE_ROCKS, x, y);
		m.setImpassable();
		return m;
	}
	
	public MapCell makeWaterCell(int x, int y) {
		MapCell m = new MapCell(MapCell.WATER, x, y);
		m.setImpassable();
		return m;
	}

	public MapCell makeFlowersCell(int x, int y) {
		return new MapCell(MapCell.FLOWERS, x, y);
	}

	public MapCell makeBricksCell(int x, int y) {
		return new MapCell(MapCell.BRICKS, x, y);
	}
	
	public MapCell makeShoalsCell(int x, int y) {
		MapCell m = new MapCell(MapCell.SHOALS, x, y);
		m.setImpassable();
		return m;
	}
	
	public MapCell makeForestCell(int x, int y) {
		return new MapCell(MapCell.FOREST, x, y);
	}

}

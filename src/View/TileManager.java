package View;

import java.awt.image.BufferedImage;

import ModelComponents.MapCell;
import ModelComponents.ModelMap;

public abstract class TileManager {

	protected BufferedImage myImage;
	protected ModelMap myMap;
	public TileManager(BufferedImage b, ModelMap m){
		myImage = b;
		myMap = m;
	}
	public abstract BufferedImage getImage(MapCell m);
}

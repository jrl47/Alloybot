package View;

import java.awt.image.BufferedImage;

public abstract class TileManager {

	protected BufferedImage myImage;
	public TileManager(BufferedImage b){
		myImage = b;
	}
	public abstract BufferedImage getImage(String s);
}

package View;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.MapCell;

public class DeciduousTileManager extends TileManager{

	public DeciduousTileManager() throws IOException {
		super(ImageIO.read(ScreenBuilder.class.getResource("/lightdeciduousspritesheet.png")));
	}

	@Override
	public BufferedImage getImage(String s) {
		if(s.equals(MapCell.GRASS))
			return myImage.getSubimage(1, 0, 16, 16);
		if(s.equals(MapCell.DIRT))
			return myImage.getSubimage(0, 0, 16, 16);
		return null;
	}

}

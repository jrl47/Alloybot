package View;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.MapCell;
import ModelComponents.ModelMap;

public class DeciduousTileManager extends TileManager{

	public DeciduousTileManager(ModelMap m) throws IOException {
		super(ImageIO.read(ScreenBuilder.class.getResource("/deciduousspritesheet.png")), m);
	}

	@Override
	public BufferedImage getImage(MapCell m) {
		if(m.getID().equals(MapCell.GRASS))
			return generateGrass();
		if(m.getID().equals(MapCell.DIRT))
			return generateDirt(m);
		return null;
	}

	private BufferedImage generateDirt(MapCell m) {
		String up = "";
		String down = "";
		String left = "";
		String right = "";
		if(m.getY()-1 >= 0)
			up = myMap.getCell(m.getX(), m.getY()-1).getID();
		if(m.getY()+1 < myMap.getWidth())
			down = myMap.getCell(m.getX(), m.getY()+1).getID();
		if(m.getX()-1 >= 0)
			left = myMap.getCell(m.getX()-1, m.getY()).getID();
		if(m.getX()+1 < myMap.getWidth())
			right = myMap.getCell(m.getX()+1, m.getY()).getID();
		boolean u = up.equals(MapCell.GRASS);
		boolean d = down.equals(MapCell.GRASS);
		boolean l = left.equals(MapCell.GRASS);
		boolean r = right.equals(MapCell.GRASS);
		if(u&&d&&l&&r)
			return myImage.getSubimage(1*16, 1*16, 16, 16);
		if(!u&&d&&l&&r)
			return myImage.getSubimage(3*16, 1*16, 16, 16);
		if(u&&!d&&l&&r)
			return myImage.getSubimage(2*16, 1*16, 16, 16);
		if(u&&d&&!l&&r)
			return myImage.getSubimage(5*16, 1*16, 16, 16);
		if(u&&d&&l&&!r)
			return myImage.getSubimage(4*16, 1*16, 16, 16);
		if(!u&&!d&&l&&r)
			return myImage.getSubimage(6*16, 1*16, 16, 16);
		if(!u&&d&&!l&&r)
			return myImage.getSubimage(9*16, 1*16, 16, 16);
		if(!u&&d&&l&&!r)
			return myImage.getSubimage(8*16, 1*16, 16, 16);
		if(u&&!d&&!l&&r)
			return myImage.getSubimage(9*16, 0*16, 16, 16);
		if(u&&!d&&l&&!r)
			return myImage.getSubimage(8*16, 0*16, 16, 16);
		if(u&&d&&!l&&!r)
			return myImage.getSubimage(7*16, 1*16, 16, 16);
		if(!u&&!d&&!l&&r)
			return myImage.getSubimage(7*16, 0*16, 16, 16);
		if(!u&&!d&&l&&!r)
			return myImage.getSubimage(6*16, 0*16, 16, 16);
		if(!u&&d&&!l&&!r)
			return myImage.getSubimage(5*16, 0*16, 16, 16);
		if(u&&!d&&!l&&!r)
			return myImage.getSubimage(4*16, 0*16, 16, 16);
		return myImage.getSubimage(3*16, 0, 16, 16);
	}

	private BufferedImage generateGrass() {
		return myImage.getSubimage(0*16, 0, 16, 16);
	}

}

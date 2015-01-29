package View;

import java.awt.Graphics;
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
		if(m.getID().equals(MapCell.SMALL_ROCKS))
			return generateSmallRocks(m);
		if(m.getID().equals(MapCell.LARGE_ROCKS))
			return generateLargeRocks(m);
		return null;
	}
	
	public BufferedImage getHoverTransparency(){
		return myImage.getSubimage(3*16, 2*16, 16, 16);
	}

	private BufferedImage generateLargeRocks(MapCell m) {
		return myImage.getSubimage(2*16, 0, 16, 16);
	}

	private BufferedImage generateSmallRocks(MapCell m) {
		return myImage.getSubimage(1*16, 0, 16, 16);
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
		boolean u = up.equals(MapCell.GRASS)||up.equals(MapCell.SMALL_ROCKS)||up.equals(MapCell.LARGE_ROCKS);
		boolean d = down.equals(MapCell.GRASS)||down.equals(MapCell.SMALL_ROCKS)||down.equals(MapCell.LARGE_ROCKS);
		boolean l = left.equals(MapCell.GRASS)||left.equals(MapCell.SMALL_ROCKS)||left.equals(MapCell.LARGE_ROCKS);
		boolean r = right.equals(MapCell.GRASS)||right.equals(MapCell.SMALL_ROCKS)||right.equals(MapCell.LARGE_ROCKS);
		
		
		BufferedImage bb = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		g.drawImage(myImage.getSubimage(3*16, 0, 16, 16), 0, 0, null);
		if(u){
			g.drawImage(myImage.getSubimage(0, 1*16, 16, 16), 0, 0, null);
		}
		if(d){
			g.drawImage(myImage.getSubimage(0, 2*16, 16, 16), 0, 0, null);
		}
		if(l){
			g.drawImage(myImage.getSubimage(1*16, 2*16, 16, 16), 0, 0, null);
		}
		if(r){
			g.drawImage(myImage.getSubimage(2*16, 2*16, 16, 16), 0, 0, null);
		}
		return bb;
	}

	private BufferedImage generateGrass() {
		return myImage.getSubimage(0*16, 0, 16, 16);
	}

}

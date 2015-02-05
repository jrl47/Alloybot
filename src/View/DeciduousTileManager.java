package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.MapCell;
import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import ModelComponents.Robot;

public class DeciduousTileManager extends TileManager{

	private BufferedImage myRobotSprite;
	public DeciduousTileManager(ModelMap m) throws IOException {
		super(ImageIO.read(ScreenBuilder.class.getResource("/deciduousspritesheet.png")), m);
		myRobotSprite = ImageIO.read(ScreenBuilder.class.getResource("/genericrobot.png"));
	}

	@Override
	public BufferedImage getImage(MapCell m) {
		BufferedImage bb = doBackground(m);
		return bb;
	}
	public BufferedImage doBackground(MapCell m){
		if(m.getID().equals(MapCell.GRASS))
			return generateGrass();
		if(m.getID().equals(MapCell.DIRT))
			return generateDirt(m);
		if(m.getID().equals(MapCell.SMALL_ROCKS))
			return generateSmallRocks(m);
		if(m.getID().equals(MapCell.LARGE_ROCKS))
			return generateLargeRocks(m);
		if(m.getID().equals(MapCell.WATER))
			return generateWater(m);
		if(m.getID().equals(MapCell.FLOWERS))
			return generateFlowers(m);
		if(m.getID().equals(MapCell.BRICKS))
			return generateBricks(m);
		if(m.getID().equals(MapCell.SHOALS))
			return generateShoals();
		if(m.getID().equals(MapCell.FOREST))
			return generateForest(m);
		return null;
	}
	
	public BufferedImage generateRobot(int robotAnimCounter) {
		if(robotAnimCounter >= 75)
			return myRobotSprite.getSubimage(1*16, 0, 16, 16);
		return myRobotSprite.getSubimage((robotAnimCounter/25)*16, 0, 16, 16);
//		return myImage.getSubimage(9*16, 1*16, 16, 16);
	}

	private BufferedImage generateFlowers(MapCell m) {
		BufferedImage bb = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		g.drawImage(myImage.getSubimage(0*16, 1*16, 16, 16), 0, 0, null);
		fuzzBorderWithGrass(m, g);
		g.dispose();
		return bb;
	}
	
	private BufferedImage generateForest(MapCell m) {
		BufferedImage bb = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		g.drawImage(myImage.getSubimage(3*16, 1*16, 16, 16), 0, 0, null);
		fuzzBorderWithForest(m, g);
		g.dispose();
		return bb;
	}
	
	private BufferedImage generateBricks(MapCell m) {
		BufferedImage bb = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		g.drawImage(myImage.getSubimage(1*16, 1*16, 16, 16), 0, 0, null);
		fuzzBorderWithGrass(m, g);
		g.dispose();
		return bb;
	}
	
	private BufferedImage generateWater(MapCell m) {
		BufferedImage bb = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		g.drawImage(myImage.getSubimage(9*16, 0, 16, 16), 0, 0, null);
		fuzzBorderWithGrass(m, g);
		g.dispose();
		return bb;
	}

	public BufferedImage getHoverTransparency(){
		return myImage.getSubimage(8*16, 1*16, 16, 16);
	}
	public BufferedImage getHighlightTransparency(){
		return myImage.getSubimage(7*16, 0*16, 16, 16);
	}

	private BufferedImage generateLargeRocks(MapCell m) {
		return myImage.getSubimage(2*16, 0, 16, 16);
	}

	private BufferedImage generateSmallRocks(MapCell m) {
		return myImage.getSubimage(1*16, 0, 16, 16);
	}

	private BufferedImage generateDirt(MapCell m) {
		BufferedImage bb = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		g.drawImage(myImage.getSubimage(8*16, 0, 16, 16), 0, 0, null);
		fuzzBorderWithGrass(m, g);
		g.dispose();
		return bb;
	}

	private void fuzzBorderWithGrass(MapCell m, Graphics g) {
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
		if(u){
			g.drawImage(myImage.getSubimage(3*16, 0, 16, 16), 0, 0, null);
		}
		if(d){
			g.drawImage(myImage.getSubimage(5*16, 0, 16, 16), 0, 0, null);
		}
		if(l){
			g.drawImage(myImage.getSubimage(4*16, 0, 16, 16), 0, 0, null);
		}
		if(r){
			g.drawImage(myImage.getSubimage(6*16, 0, 16, 16), 0, 0, null);
		}
	}
	
	private void fuzzBorderWithForest(MapCell m, Graphics g) {
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
		if(u){
			g.drawImage(myImage.getSubimage(4*16, 1*16, 16, 16), 0, 0, null);
		}
		if(d){
			g.drawImage(myImage.getSubimage(6*16, 1*16, 16, 16), 0, 0, null);
		}
		if(l){
			g.drawImage(myImage.getSubimage(5*16, 1*16, 16, 16), 0, 0, null);
		}
		if(r){
			g.drawImage(myImage.getSubimage(7*16, 1*16, 16, 16), 0, 0, null);
		}
		g.dispose();
	}

	private BufferedImage generateGrass() {
		return myImage.getSubimage(0*16, 0, 16, 16);
	}
	private BufferedImage generateShoals() {
		return myImage.getSubimage(2*16, 1*16, 16, 16);
	}

}

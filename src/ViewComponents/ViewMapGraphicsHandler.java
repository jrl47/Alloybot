package ViewComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import ModelComponents.MapCell;
import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;
import ModelComponents.Robot;
import View.DeciduousTileManager;
import View.ScreenBuilder;

public class ViewMapGraphicsHandler {
	private BufferedImage map;
	private BufferedImage frame;
	private ViewMapAnimationHandler animation;
	private ModelMap myMap;
	private int xHover;
	private int yHover;
	private boolean loaded;
	private boolean isHover;
	private boolean moveLoaded;
	private Robot currentRobot;
	private List<MapCellObject> myObjects;
	private Set<MapCell> myMoves;
	
	public ViewMapGraphicsHandler(ViewMapAnimationHandler a, ModelMap m){
		animation = a;
		myMap = m;
		myMoves = new HashSet<MapCell>();
		myObjects = new ArrayList<MapCellObject>();
	}
	
	void loadMap() {
		DeciduousTileManager manager = null;
		try {
			manager = new DeciduousTileManager(myMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		map = new BufferedImage((myMap).getWidth()*16, (myMap).getHeight()*16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();
		for(int i=0; i<(myMap).getWidth(); i++){
			for(int j=0; j<(myMap).getHeight(); j++){
				g.drawImage(manager.getImage((myMap).getCell(i, j)),
					i*16, j*16, null);
				myObjects.addAll((myMap).getCell(i, j).getObjects());
			}
		}
		animation.setPrevX(animation.getX());
		animation.setPrevY(animation.getY());
	}
	
	BufferedImage drawVisibleMapRegion(boolean hover, boolean moveload, Robot selectedRobot) {
		isHover = hover;
		moveLoaded = moveload;
		currentRobot = selectedRobot;
		BufferedImage tempMap = new BufferedImage(16*ViewMap.WIDTH, 16*ViewMap.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics tempG = tempMap.createGraphics();
		tempG.drawImage(map.getSubimage(animation.getAnimateX(),  animation.getAnimateY(), 16*ViewMap.WIDTH, 16*ViewMap.HEIGHT), 0, 0, null);
		tempG.dispose();
		Graphics2D g = tempMap.createGraphics();
		DeciduousTileManager manager = null;
		try {
			manager = new DeciduousTileManager(myMap);
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		drawHoverTile(g, manager);
		drawMoveRange(g, manager);
		drawRobot(g, manager);
		generateFrame();
		
		g.dispose();
		g = frame.createGraphics();
		g.drawImage(tempMap, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH, null);
		return frame;
	}
	
	private void drawHoverTile(Graphics2D g, DeciduousTileManager manager) {
		if(!animation.inAnimation() && isHover){
			g.drawImage(manager.getHoverTransparency(),	animation.pixelsToCellX(xHover)*16 - animation.getAnimateX(),
					animation.pixelsToCellY(yHover)*16 -animation.getAnimateY(), null);
		}
	}
	
	private void drawMoveRange(Graphics2D g, DeciduousTileManager manager){
		if(moveLoaded){
		for(MapCell m: myMoves){
			g.drawImage(manager.getHighlightTransparency(),(m.getX())*16 - animation.getAnimateX(),
					(m.getY())*16 - animation.getAnimateY(), null);
		}
		}
	}
	
	private void drawRobot(Graphics2D g, DeciduousTileManager manager) {
		if(currentRobot!=null){
			g.drawImage(manager.getHighlightTransparency(),currentRobot.getX()*16 - animation.getAnimateX(),
					currentRobot.getY()*16 - animation.getAnimateY(), null);
		}
		for(MapCellObject m: myObjects){
			if(m instanceof Robot){
				Robot r = (Robot)m;
				g.drawImage(manager.generateRobot(animation.getRobotAnimCounter()),r.getX()*16 - animation.getAnimateX(),r.getY()*16 - animation.getAnimateY(), null);
			}
		}
	}
	
	private void generateFrame() {
		if(!loaded){
		try {
			frame = new BufferedImage(16*ViewMap.WIDTH + ViewMap.BORDER_WIDTH*2, 16*ViewMap.HEIGHT + ViewMap.BORDER_WIDTH*2, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = frame.createGraphics();
			BufferedImage myBacking = ImageIO.read(ScreenBuilder.class.getResource("/mapbacking.png"));
			int farside = myBacking.getHeight() - ViewMap.BORDER_WIDTH;
			g.drawImage(myBacking.getSubimage(0, 0, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH), 0, 0, null);
			g.drawImage(myBacking.getSubimage(farside, farside, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH),
					ViewMap.BORDER_WIDTH + 16*ViewMap.WIDTH, ViewMap.BORDER_WIDTH + 16*ViewMap.HEIGHT, null);
			g.drawImage(myBacking.getSubimage(farside, 0, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH),
					ViewMap.BORDER_WIDTH + 16*ViewMap.WIDTH, 0, null);
			g.drawImage(myBacking.getSubimage(0, farside, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH),
					0, ViewMap.BORDER_WIDTH + 16*ViewMap.HEIGHT, null);
			for(int i=0; i<ViewMap.WIDTH; i++){
				g.drawImage(myBacking.getSubimage(ViewMap.BORDER_WIDTH, 0, 16, ViewMap.BORDER_WIDTH),
						ViewMap.BORDER_WIDTH + 16*i, 0, null);
			}
			for(int i=0; i<ViewMap.WIDTH; i++){
				g.drawImage(myBacking.getSubimage(ViewMap.BORDER_WIDTH, farside, 16, ViewMap.BORDER_WIDTH),
						ViewMap.BORDER_WIDTH + 16*i, ViewMap.BORDER_WIDTH + 16*ViewMap.HEIGHT, null);
			}
			for(int i=0; i<ViewMap.HEIGHT; i++){
				g.drawImage(myBacking.getSubimage(0, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH, 16),
						0, ViewMap.BORDER_WIDTH + 16*i, null);
			}
			for(int i=0; i<ViewMap.HEIGHT; i++){
				g.drawImage(myBacking.getSubimage(farside, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH, 16),
						ViewMap.BORDER_WIDTH + 16*ViewMap.WIDTH, ViewMap.BORDER_WIDTH + 16*i, null);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
		loaded = true;
	}

	public void setXHover(int x){
		xHover = x;
	}
	public void setYHover(int y){
		yHover = y;
	}

	public boolean isLoaded() {
		return loaded;
	}
	public void setMoves(Set<MapCell> m){
		myMoves = m;
	}
}

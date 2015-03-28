package ViewComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import ModelComponents.MapCellObject;
import ModelComponents.ModelMap;

import View.DeciduousTileManager;
import View.ViewFrameGenerator;

public class ViewMapGraphicsHandler {
	private BufferedImage map;
	private BufferedImage frame;
	private ViewMapAnimationHandler animation;
	private ModelMap myMap;
	private int xHover;
	private int yHover;
	private boolean loaded;
	private boolean isHover;
	private List<ViewMapObject> myActiveObjects;
	
	public ViewMapGraphicsHandler(ViewMapAnimationHandler a, ModelMap m){
		animation = a;
		myMap = m;
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
			}
		}
		animation.setPrevX(animation.getX());
		animation.setPrevY(animation.getY());
	}
	
	BufferedImage drawVisibleMapRegion(boolean hover, List<ViewMapObject> activeObjects) {
		myActiveObjects = activeObjects;
		isHover = hover;
		BufferedImage tempMap = new BufferedImage(16*ViewMap.WIDTH, 16*ViewMap.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics tempG = tempMap.createGraphics();
		tempG.drawImage(map.getSubimage(animation.getOriginX(),  animation.getOriginY(), 16*ViewMap.WIDTH, 16*ViewMap.HEIGHT), 0, 0, null);
		tempG.dispose();
		Graphics2D g = tempMap.createGraphics();
		DeciduousTileManager manager = null;
		try {
			manager = new DeciduousTileManager(myMap);
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		drawHoverTile(g, manager);
		drawMapObjects(g, manager);
		if(!loaded){
			ViewFrameGenerator generator = new ViewFrameGenerator();
			frame = generator.generateFrame();
			loaded = true;
		}
		
		g.dispose();
		g = frame.createGraphics();
		g.drawImage(tempMap, ViewMap.BORDER_WIDTH, ViewMap.BORDER_WIDTH, null);
		return frame;
	}
	private void drawHoverTile(Graphics2D g, DeciduousTileManager manager) {
		if(!animation.inAnimation() && isHover){
			g.drawImage(manager.getHoverTransparency(),	animation.pixelsToCellX(xHover)*16 - animation.getOriginX(),
					animation.pixelsToCellY(yHover)*16 -animation.getOriginY(), null);
		}
	}
	private void drawMapObjects(Graphics2D g, DeciduousTileManager manager) {
		for(ViewMapObject v: myActiveObjects){
			v.draw(g, manager, animation);
		}
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
}

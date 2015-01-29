package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelMap;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ViewComponents.InputSensitive;
import ViewComponents.ViewComponent;

public class ViewMap extends ViewComponent implements InputSensitive{

	public static final int WIDTH = 23;
	public static final int HEIGHT = 23;
	public static final int BORDER_WIDTH = 10;
	private int prevX;
	private int prevY;
	private int xHover;
	private int yHover;
	private BufferedImage map;
	
	private boolean loaded;
	
	private boolean animateFinished;
	private int xDirection;
	private int yDirection;
	private int animateCounter;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy, 2*BORDER_WIDTH + 16*WIDTH, 2*BORDER_WIDTH + 16*HEIGHT);
	}

	@Override
	public BufferedImage loadImage() {
		if(!loaded){
			loadMap();
		}
		int x = ((ModelMap)myComponent).getX() - WIDTH/2 + 1;
		int y = ((ModelMap)myComponent).getY() - HEIGHT/2 + 1;
		if(animateFinished){
			if(prevX!=x){
				animateCounter = 8;
				xDirection = -1;
				if(prevX>x)
					xDirection = 1;
			}
			if(prevY!=y){
				animateCounter = 8;
				yDirection = -1;
				if(prevY>y)
					yDirection = 1;
			}
		}
		prevX = x;
		prevY = y;
		BufferedImage frame = new BufferedImage(16*WIDTH + BORDER_WIDTH*2, 16*HEIGHT + BORDER_WIDTH*2, BufferedImage.TYPE_INT_ARGB);
		System.out.println((x + animateCounter*xDirection*2) + " " + (y + animateCounter*yDirection*2));
		BufferedImage tempMap = new BufferedImage(16*WIDTH, 16*HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics tempG = tempMap.createGraphics();
		tempG.drawImage(map.getSubimage(x*16 + animateCounter*xDirection*2,  y*16 + animateCounter*yDirection*2, 16*WIDTH, 16*HEIGHT), 0, 0, null);
		tempG.dispose();
		Graphics2D g = tempMap.createGraphics();
		DeciduousTileManager manager = null;
		try {
			manager = new DeciduousTileManager((ModelMap)myComponent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		drawHoverTile(g, manager);
		g.dispose();
		g = frame.createGraphics();
		try {
			g.drawImage(ImageIO.read(ScreenBuilder.class.getResource("/mapbacking.png")), 0, 0, null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		g.drawImage(tempMap, BORDER_WIDTH, BORDER_WIDTH, null);
		animateFinished = true;
		if(animateCounter>0){
			animateCounter--;
		}
		else{
			xDirection = 0;
			yDirection = 0;
		}
		return frame;
	}

	private void drawHoverTile(Graphics2D g, DeciduousTileManager manager) {
		for(int i=-1; i<WIDTH+2; i++){
			for(int j=-1; j<HEIGHT+2; j++){
				if((isHover && animateCounter==0 && BORDER_WIDTH + (i)*16 <= xHover && BORDER_WIDTH + (i+1)*16 > xHover)
						&& (isHover && animateCounter==0 && BORDER_WIDTH + (j)*16 <= yHover && BORDER_WIDTH + (j+1)*16 > yHover)){
					g.drawImage(manager.getHoverTransparency(),
						i*16, j*16, null);
				}
			}
		}
	}

	private void loadMap() {
		DeciduousTileManager manager = null;
		try {
			manager = new DeciduousTileManager(((ModelMap) myComponent));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = new BufferedImage(((ModelMap) myComponent).getWidth()*16, ((ModelMap) myComponent).getHeight()*16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();
		for(int i=0; i<((ModelMap) myComponent).getWidth(); i++){
			for(int j=0; j<((ModelMap) myComponent).getHeight(); j++){
				g.drawImage(manager.getImage(((ModelMap) myComponent).getCell(i, j)),
					i*16, j*16, null);
			}
		}
		loaded = true;
	}
	
	public void respond(){
		if(myComponent!=null){
			if(animateCounter==0)
				myComponent.respond();
		}
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

	@Override
	public void useInput(int x, int y) {
		xHover = x;
		yHover = y;
	}

}

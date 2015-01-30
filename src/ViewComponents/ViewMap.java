package ViewComponents;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelMap;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import View.DeciduousTileManager;
import View.ScreenBuilder;

public class ViewMap extends ViewComponent implements InputSensitive{

	public static final int WIDTH = 31;
	public static final int HEIGHT = 27;
	public static final int BORDER_WIDTH = 9;
	private int x;
	private int y;
	private int prevX;
	private int prevY;
	private int xHover;
	private int yHover;
	private int hoverCounter;
	private BufferedImage map;
	private BufferedImage frame;
	
	private boolean loaded;
	
	private double xDirection;
	private double yDirection;
	private double animateXCounter;
	private double animateYCounter;
	private double xCounterDecrement;
	private double yCounterDecrement;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy, 2*BORDER_WIDTH + 16*WIDTH, 2*BORDER_WIDTH + 16*HEIGHT);
	}

	@Override
	public BufferedImage loadImage() {
		x = ((ModelMap)myComponent).getX() - WIDTH/2;
		y = ((ModelMap)myComponent).getY() - HEIGHT/2;
		if(!loaded){
			loadMap();
		}
		
		handleAnimation();
		
		return drawVisibleMapRegion();
	}

	private BufferedImage drawVisibleMapRegion() {
		BufferedImage tempMap = new BufferedImage(16*WIDTH, 16*HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics tempG = tempMap.createGraphics();
		tempG.drawImage(map.getSubimage(x*16 + (int)(animateXCounter*xDirection),  y*16 + (int)(animateYCounter*yDirection), 16*WIDTH, 16*HEIGHT), 0, 0, null);
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
		generateFrame();
		g = frame.createGraphics();
		g.drawImage(tempMap, BORDER_WIDTH, BORDER_WIDTH, null);
		return frame;
	}

	private void generateFrame() {
		if(!loaded){
		try {
			frame = new BufferedImage(16*WIDTH + BORDER_WIDTH*2, 16*HEIGHT + BORDER_WIDTH*2, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = frame.createGraphics();
			BufferedImage myBacking = ImageIO.read(ScreenBuilder.class.getResource("/mapbacking.png"));
			int farside = myBacking.getHeight() - BORDER_WIDTH;
			g.drawImage(myBacking.getSubimage(0, 0, BORDER_WIDTH, BORDER_WIDTH), 0, 0, null);
			g.drawImage(myBacking.getSubimage(farside, farside, BORDER_WIDTH, BORDER_WIDTH),
					BORDER_WIDTH + 16*WIDTH, BORDER_WIDTH + 16*HEIGHT, null);
			g.drawImage(myBacking.getSubimage(farside, 0, BORDER_WIDTH, BORDER_WIDTH),
					BORDER_WIDTH + 16*WIDTH, 0, null);
			g.drawImage(myBacking.getSubimage(0, farside, BORDER_WIDTH, BORDER_WIDTH),
					0, BORDER_WIDTH + 16*HEIGHT, null);
			for(int i=0; i<WIDTH; i++){
				g.drawImage(myBacking.getSubimage(BORDER_WIDTH, 0, 16, BORDER_WIDTH),
						BORDER_WIDTH + 16*i, 0, null);
			}
			for(int i=0; i<WIDTH; i++){
				g.drawImage(myBacking.getSubimage(BORDER_WIDTH, farside, 16, BORDER_WIDTH),
						BORDER_WIDTH + 16*i, BORDER_WIDTH + 16*HEIGHT, null);
			}
			for(int i=0; i<HEIGHT; i++){
				g.drawImage(myBacking.getSubimage(0, BORDER_WIDTH, BORDER_WIDTH, 16),
						0, BORDER_WIDTH + 16*i, null);
			}
			for(int i=0; i<HEIGHT; i++){
				g.drawImage(myBacking.getSubimage(farside, BORDER_WIDTH, BORDER_WIDTH, 16),
						BORDER_WIDTH + 16*WIDTH, BORDER_WIDTH + 16*i, null);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		hoverCounter=0;
		}
		loaded = true;
	}

	private void handleAnimation() {
		if(animateXCounter>0){
			animateXCounter-=xCounterDecrement;
		}
		if(animateYCounter>0){
			animateYCounter-=yCounterDecrement;
		}
		if(animateXCounter==0 && animateYCounter==0){
		xDirection = 0;
		yDirection = 0;
		}
		if(prevX!=x){
			animateXCounter = 16;
			xDirection = (int)((prevX - x)*1);
		}
		if(prevY!=y){
			animateYCounter = 16;
			yDirection = (int)((prevY - y)*1);
		}
		if(animateXCounter != animateYCounter){
			if(animateXCounter > animateYCounter){
				xCounterDecrement = 1;
				yCounterDecrement = 1.0*(animateYCounter/animateXCounter);
			}
			else{
				yCounterDecrement = 1;
				xCounterDecrement = 1.0*(animateXCounter/animateYCounter);
			}
		}
		else{
			xCounterDecrement = 1;
			yCounterDecrement = 1;
		}
		prevX = x;
		prevY = y;
		hoverCounter++;
		hoverCounter = hoverCounter % 140;
	}

	private void drawHoverTile(Graphics2D g, DeciduousTileManager manager) {
		for(int i=-1; i<WIDTH+2; i++){
			for(int j=-1; j<HEIGHT+2; j++){
				if((isHover && animateXCounter==0 && animateYCounter==0 && BORDER_WIDTH + (i)*16 <= xHover && BORDER_WIDTH + (i+1)*16 > xHover)
						&& (isHover && animateXCounter==0 && animateYCounter==0  && BORDER_WIDTH + (j)*16 <= yHover && BORDER_WIDTH + (j+1)*16 > yHover)){
					g.drawImage(manager.getHoverTransparency(hoverCounter),
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
		prevX = x;
		prevY = y;
	}
	
	public void respond(){
		if(myComponent!=null){
			if(animateXCounter==0 && animateYCounter==0)
				myComponent.respond();
		}
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

	@Override
	public void useInput(int xx, int yy, boolean b) {
		xHover = xx;
		yHover = yy;
		if(b && animateXCounter==0 && animateYCounter==0 && xx >= 9 && yy >= 9 &&
				xx < 9 + WIDTH*16 && yy < 9 + HEIGHT*16){
			int newX = ((ModelMap)myComponent).getCell(x + ((xx-BORDER_WIDTH)/16), y + ((yy-BORDER_WIDTH)/16)).getX();
			int newY = ((ModelMap)myComponent).getCell(x + ((xx-BORDER_WIDTH)/16), y + ((yy-BORDER_WIDTH)/16)).getY();
			if (newX + WIDTH/2 >= ((ModelMap)myComponent).getWidth()){
				newX = ((ModelMap)myComponent).getWidth() - WIDTH/2 - 1;
			}
			if (newX - WIDTH/2 < 0){
				newX = WIDTH/2;
			}
			if (newY + HEIGHT/2 >= ((ModelMap)myComponent).getHeight()){
				newY = ((ModelMap)myComponent).getHeight() - HEIGHT/2 - 1;
			}
			if (newY - HEIGHT/2 < 0){
				newY = HEIGHT/2;
			}
			((ModelMap)myComponent).setX(newX);
			((ModelMap)myComponent).setY(newY);
		}
	}

}

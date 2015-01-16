package View;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelMap;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ViewComponents.ViewComponent;

public class ViewMap extends ViewComponent {

	public static final int WIDTH = 23;
	public static final int HEIGHT = 23;
	public static final int BORDER_WIDTH = 10;
	private int prevX;
	private int prevY;
	private boolean loaded;
	private int xDirect;
	private int yDirect;
	private int animateCounter;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy);
	}

	@Override
	public BufferedImage loadImage() {
		BufferedImage frame = new BufferedImage(16*WIDTH + BORDER_WIDTH*2, 16*HEIGHT + BORDER_WIDTH*2, BufferedImage.TYPE_INT_ARGB);
		BufferedImage map = new BufferedImage(16*(WIDTH+2), 16*(WIDTH+2), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();
		DeciduousTileManager manager = null;
		int x = ((ModelMap)myComponent).getX() - WIDTH/2 + 1;
		int y = ((ModelMap)myComponent).getY() - HEIGHT/2 + 1;
		if(loaded){
			if(prevX!=x){
				animateCounter = 8;
				xDirect = -1;
				if(prevX>x)
					xDirect = 1;
			}
			if(prevY!=y){
				animateCounter = 8;
				yDirect = -1;
				if(prevY>y)
					yDirect = 1;
			}
		}
		prevX = x;
		prevY = y;
		try {
			manager = new DeciduousTileManager((ModelMap)myComponent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=-1; i<WIDTH+2; i++){
			for(int j=-1; j<HEIGHT+2; j++){
				if(i+x >= 0 && j+y >= 0 && i+x < ((ModelMap) myComponent).getWidth() && j+y < ((ModelMap) myComponent).getHeight())
					g.drawImage(manager.getImage(((ModelMap) myComponent).getCell(i+x, j+y)),
							i*16, j*16, null);
			}
		}
		g.dispose();
		g = frame.createGraphics();
		try {
			g.drawImage(ImageIO.read(ScreenBuilder.class.getResource("/mapbacking.png")), 0, 0, null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		map = map.getSubimage(16 + animateCounter*xDirect*2,  16 + animateCounter*yDirect*2, 16*WIDTH, 16*HEIGHT);
		g.drawImage(map, BORDER_WIDTH, BORDER_WIDTH, null);
		loaded = true;
		if(animateCounter>0){
			animateCounter--;
		}
		else{
			xDirect = 0;
			yDirect = 0;
		}
		return frame;
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

}

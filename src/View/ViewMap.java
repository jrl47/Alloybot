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

	public static final int WIDTH = 11;
	public static final int HEIGHT = 11;
	public static final int BORDER_WIDTH = 10;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy);
	}

	@Override
	public BufferedImage loadImage() {
		BufferedImage frame = new BufferedImage(16*WIDTH + BORDER_WIDTH*2, 16*HEIGHT + BORDER_WIDTH*2, BufferedImage.TYPE_INT_ARGB);
		BufferedImage map = new BufferedImage(16*WIDTH, 16*HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = map.createGraphics();
		try {
			g.drawImage(ImageIO.read(ScreenBuilder.class.getResource("/mapbacking.png")), 0, 0, null);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		DeciduousTileManager manager = null;
		int x = ((ModelMap)myComponent).getX() - WIDTH/2 + 1;
		int y = ((ModelMap)myComponent).getY() - HEIGHT/2 + 1;
		try {
			manager = new DeciduousTileManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<WIDTH; i++){
			for(int j=0; j<HEIGHT; j++){
				if(i+x > 0 && j+y > 0 && i+x < ((ModelMap) myComponent).getWidth() && j+y < ((ModelMap) myComponent).getHeight())
					g.drawImage(manager.getImage(((ModelMap) myComponent).getCell(i+x, j+y).getID()),
							i*16 + BORDER_WIDTH, j*16 + BORDER_WIDTH, null);
			}
		}
		g.dispose();
		g = frame.createGraphics();
		g.drawImage(map, BORDER_WIDTH, BORDER_WIDTH, null);
		return frame;
	}
	
	public void respond(){
		if(myComponent!=null){
				myComponent.respond();
		}
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}

package View;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ViewComponents.ViewComponent;

public class ViewMap extends ViewComponent {

	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	public ViewMap(ModelComponent c, int xx, int yy) {
		super(c, xx, yy);
	}

	@Override
	public BufferedImage loadImage() {
		BufferedImage b = new BufferedImage(16*WIDTH, 16*HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = b.createGraphics();
		DeciduousTileManager manager = null;
		try {
			manager = new DeciduousTileManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i<WIDTH; i++){
			for(int j=0; j<HEIGHT; j++){
				g.drawImage(manager.getImage(MapCell.DIRT), i*16, j*16, null);
			}
		}
		g.dispose();
		return b;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}

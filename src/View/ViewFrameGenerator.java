package View;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ViewComponents.ViewMap;

public class ViewFrameGenerator {

	public BufferedImage generateFrame() {
		BufferedImage frame = null;
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
	return frame;
}
}

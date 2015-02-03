package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.AlloyFont;
import View.ScreenBuilder;

public class RobotActionScreen extends ViewComponent{
	private BufferedImage myBackground;
	public RobotActionScreen(ModelComponent m, int xx, int yy){
		super(null, xx, yy, 100, 300);
	}
	
	@Override
	public BufferedImage loadImage() {
		if(myBackground==null){
		try {
			myBackground = ImageIO.read(ScreenBuilder.class.getResource("/robotactionbackground.png"));
			BufferedImage bb = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bb.getGraphics();
			g.drawImage(myBackground, 0, 0, null);
			myBackground = bb;
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		BufferedImage image = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(myBackground, 0, 0, null);
		drawComponents();
		return image;
	}
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
	public void addComponent(ViewComponent v){
		myComponents.add(v);
	}
}

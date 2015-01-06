package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.ScreenBuilder;

public class StartButton extends ViewComponent{

	public StartButton(ModelComponent c, int xx, int yy) {
		super(c, xx, yy);
	}

	@Override
	public BufferedImage loadImage() {
		try {
			return ImageIO.read(ScreenBuilder.class.getResource("/START.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BufferedImage loadHover() {
		try {
			return ImageIO.read(ScreenBuilder.class.getResource("/STARTh.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

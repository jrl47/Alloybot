package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.ScreenBuilder;

public class AlloyBorderedButton extends BorderedButton{

	public AlloyBorderedButton(ModelComponent c, int xx, int yy, String s,
			int size) throws IOException {
		super(c, xx, yy, s, size, ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")),
				ImageIO.read(ScreenBuilder.class.getResource("/bluefonts.png")),
				ImageIO.read(ScreenBuilder.class.getResource("/textbackground.png")), 
				ImageIO.read(ScreenBuilder.class.getResource("/textbackgroundhover.png")));
	}

}

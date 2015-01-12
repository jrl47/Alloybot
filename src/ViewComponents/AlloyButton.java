package ViewComponents;

import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.ModelComponent;
import View.ScreenBuilder;

public class AlloyButton extends Button{

	public AlloyButton(ModelComponent c, int xx, int yy, String s, int size) throws IOException {
		super(c, xx, yy, s, size, ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")), ImageIO.read(ScreenBuilder.class.getResource("/bluefonts.png")));
	}

}

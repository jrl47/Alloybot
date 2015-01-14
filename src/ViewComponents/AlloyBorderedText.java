package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class AlloyBorderedText extends BorderedText{

	public AlloyBorderedText(int xx, int yy, String s, int size) throws IOException {
		super(xx, yy, s, size, ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")), ImageIO.read(ScreenBuilder.class.getResource("/textbackground.png")));
	}

}

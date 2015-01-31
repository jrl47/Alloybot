package View;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlloyFont extends FixedFont{

	public AlloyFont() throws IOException {
		super(ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")), 6);
	}

}

package ViewComponents;

import java.io.IOException;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class AlloyText extends Text{

	public AlloyText(String s, int size, int xx, int yy) throws IOException {
		super(xx, yy, s, size, ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")));
	}

}

package ViewComponents;

import java.io.IOException;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class AlloyText extends Text{

	public AlloyText(int xx, int yy, String s, int size) throws IOException {
		super(xx, yy, s, size, ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")));
	}

}

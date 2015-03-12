package ViewComponents;

import java.io.IOException;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class AlloyBorderedSelectionMenu extends BorderedSelectionMenu{

	public AlloyBorderedSelectionMenu(int xx, int yy, int size) throws IOException {
		super(xx, yy, size, ImageIO.read(ScreenBuilder.class.getResource("/fonts.png")),
				ImageIO.read(ScreenBuilder.class.getResource("/bluefonts.png")),
				ImageIO.read(ScreenBuilder.class.getResource("/textbackground.png")), 
				ImageIO.read(ScreenBuilder.class.getResource("/textbackgroundhover.png")));
	}

}

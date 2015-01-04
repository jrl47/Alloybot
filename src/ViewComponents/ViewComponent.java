package ViewComponents;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import ModelComponents.ModelComponent;
import View.PixelArray;

public class ViewComponent {
	
	private ModelComponent myComponent;
	private PixelArray myPixels;
	private Shape myBounds;
	public ViewComponent(ModelComponent c, BufferedImage b, int x, int y){
		myPixels = new PixelArray(b);
	}
}

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
	private List<ViewComponent> myComponents;
	public RobotActionScreen(ModelComponent m, int xx, int yy){
		super(null, xx, yy, 100, 300);
		myComponents = new ArrayList<ViewComponent>();
	}
	
	public void drawComponents() {
		for(int i=0; i<myComponents.size(); i++){
//			drawComponent(myComponents.get(i));
		}
	}
	
	@Override
	public BufferedImage loadImage() {
		drawComponents();
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		g.drawImage(font.getStringImage("TILE RESOURCES", 1), 10, 10, null);
//		g.drawImage(font.getStringImage("OIL:", 1), 10, 30, null);
//		String s = ((ModelMap)myComponent).getCurrentHighlightedCell().getOil() + "";
//		g.drawImage(font.getStringImage(s, 1), 40, 30, null);
		return myImage;
	}
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
	public void addComponent(ViewComponent v){
		myComponents.add(v);
	}
}

package View;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Model.ScreenData;
import ModelComponents.StartButton;
import ViewComponents.ViewComponent;

public class ScreenBuilder {

	private PixelArray pixels;
	private List<ViewComponent> myComponents;
	private ScreenData myData;
	public ScreenBuilder(ScreenData data, PixelArray p) {
		myComponents = new ArrayList<ViewComponent>();
		pixels = p;
		myData = data;
		if(myData!=null){
		String myMeth = "build" + data.getID();
		
		// reflection stuff to call the right build method based on the ScreenData's ID
		Class cl = null;
		Method meth = null;
		cl = this.getClass();
		try {
			meth = cl.getDeclaredMethod(myMeth);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			meth.invoke(this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		}
		drawComponents();
		
	}
	private void drawComponents() {
		for(int i=0; i<myComponents.size(); i++){
			drawComponent(myComponents.get(i));
		}
	}
	public PixelArray getPixels() {
		return pixels;
	}
	public List<ViewComponent> getComponents(){
		return myComponents;
	}
	
	public void drawComponent(ViewComponent v){
		PixelArray pix = v.getPixels();
		for(int i=v.getX(); i<pix.getWidth() + v.getX(); i++){
			for(int j=v.getY(); j<pix.getHeight() + v.getY(); j++){
				pixels.setPixel(i, j, pix.getPixel(i-v.getX(), j-v.getY()));
			}
		}
	}
	public void buildStart(){
		BufferedImage back = null;
		BufferedImage start = null;
		try {
			back = ImageIO.read(ScreenBuilder.class.getResource("/awesometitle.png"));
			start = ImageIO.read(ScreenBuilder.class.getResource("/START.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myComponents.add(new ViewComponent(null, back, 0, 0));
		myComponents.add(new ViewComponent(myData.getComponents().get(0), start, 180, 100));
	}
	public void buildEnd(){
		BufferedImage back = null;
		try {
			back = ImageIO.read(ScreenBuilder.class.getResource("/gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myComponents.add(new ViewComponent(null, back, 0, 0));
	}
}

package View;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Model.ScreenData;
import ViewComponents.AlloyBorderedText;
import ViewComponents.AlloyButton;
import ViewComponents.AlloyText;
import ViewComponents.Background;
import ViewComponents.Button;
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
	public void drawComponents() {
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
				if(pix.getPixel(i-v.getX(), j-v.getY())!=0)
				pixels.setPixel(i, j, pix.getPixel(i-v.getX(), j-v.getY()));
			}
		}
	}
	public void buildStart(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/awesometitle.png"))));
			myComponents.add(new AlloyBorderedText(10,10, "ALLOYALITY", 5));
			myComponents.add(new AlloyButton(myData.getComponents().get(0), 480, 240, "START", 4));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void buildEnd(){
		try {
			myComponents.add(new Background(0, 0, ImageIO.read(ScreenBuilder.class.getResource("/gameover.png"))));
			myComponents.add(new AlloyText(10, 10, "GAME OVER", 3));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

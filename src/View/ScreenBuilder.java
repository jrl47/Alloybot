package View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Model.ScreenData;

public class ScreenBuilder {

	private PixelArray pixels;
	public ScreenBuilder(ScreenData data, PixelArray p) {
		pixels = p;
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
	public PixelArray getPixels() {
		return pixels;
	}
	public void buildStart(){
		
		
		for(int i=0; i<pixels.getWidth(); i++){
			for(int j=0; j<pixels.getHeight(); j++){
				pixels.setPixel(i, j, 200);
			}
		}
	}
	public void buildEnd(){
		
	}
}

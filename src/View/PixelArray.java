package View;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Jacob
 * Manages an array of pixels for ease of access and "two-dimensional" treatment.
 */
public class PixelArray {
	private int[] pixels;
	private int width;
	public PixelArray(int[] p, int w){
		pixels = p;
		width = w;
	}
	public PixelArray(BufferedImage b){
		pixels = b.getRGB(0, 0, b.getWidth(), b.getHeight(), null, 0, b.getWidth());
		width = b.getWidth();
	}
	public void setPixel(int x, int y, int newValue){
		pixels[x + y*width] = newValue;
	}
	public void setPixels(PixelArray p){
		pixels = p.getPixels();
	}
	public int getPixel(int x, int y){
		return pixels[x + y*width];
	}
	public int size(){
		return pixels.length;
	}
	public void clear(){
		for(int i=0; i<pixels.length; i++){
			pixels[i]=0;
		}
	}
	public int[] getPixels(){
		return pixels;
	}
	public int getHeight(){
		return (int)pixels.length / width;
	}
	public int getWidth(){
		return width;
	}
}
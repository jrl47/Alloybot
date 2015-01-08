package View;

import java.awt.image.BufferedImage;
import java.util.List;

public class FixedFont {

	private List<BufferedImage> myImages;
	private int myWidth;
	private int myHeight;
	public FixedFont(BufferedImage b, int width){
		myWidth = width;
		myHeight = b.getHeight();
		for(int i=0; i<26; i++){
			BufferedImage bb = b.getSubimage((width+1)*i, b.getHeight(), width, b.getHeight());
			myImages.add(bb);
		}
	}
	public BufferedImage getStringImage(String s){
		BufferedImage bb = new BufferedImage((myWidth+1)*s.length(), myHeight, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<s.length(); i++){
			
		}
		return bb;
	}
}

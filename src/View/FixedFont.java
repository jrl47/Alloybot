package View;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class FixedFont {

	private List<BufferedImage> myImages;
	private int myWidth;
	private int myHeight;
	public FixedFont(BufferedImage b, int width){
		myWidth = width;
		myHeight = b.getHeight();
		myImages = new ArrayList<BufferedImage>();
		for(int i=0; i<26; i++){
			BufferedImage bb = b.getSubimage((width+1)*i, 0, width+1, b.getHeight());
			myImages.add(bb);
		}
	}
	public BufferedImage getStringImage(String s){
		BufferedImage bb = new BufferedImage((myWidth+1)*s.length(), myHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = bb.getGraphics();
		for(int i=0; i<s.length(); i++){
			g.drawImage(myImages.get((int)s.charAt(i)-65), (myWidth+1)*i, 0, null);
		}
		return bb;
	}
}

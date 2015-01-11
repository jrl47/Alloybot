package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
		for(int i=0; i<27; i++){
			BufferedImage bb = b.getSubimage((width+1)*i, 0, width+1, b.getHeight());
			myImages.add(bb);
		}
	}
	public BufferedImage getStringImage(String s, int scale){
		BufferedImage bb = new BufferedImage((myWidth+1)*s.length()-1, myHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bb.getGraphics();
		for(int i=0; i<s.length(); i++){
			if((s.charAt(i)==' ')){
				g.drawImage(myImages.get(26), (myWidth+1)*i, 0, null);
			}
			else{
				g.drawImage(myImages.get((int)s.charAt(i)-65), (myWidth+1)*i, 0, null);
			}
		}
		BufferedImage sc = new BufferedImage(bb.getWidth()*scale, bb.getHeight()*scale,BufferedImage.TYPE_INT_ARGB);
		Graphics2D gg = sc.createGraphics();
		gg.drawImage(bb,  0,  0, bb.getWidth()*scale, bb.getHeight()*scale, null);
		gg.dispose();
		return sc;
	}
}

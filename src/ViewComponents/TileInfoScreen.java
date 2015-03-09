package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import View.AlloyFont;
import View.ScreenBuilder;

public class TileInfoScreen extends ViewComponent{
	
	private BufferedImage myBackground;
	public TileInfoScreen(ModelComponent c, int xx, int yy){
		super(c, xx, yy, 100, 300);
	}

	@Override
	public BufferedImage loadImage() {
		if(myBackground==null){
		try {
			myBackground = ImageIO.read(ScreenBuilder.class.getResource("/tiledatabackground.png"));
			BufferedImage bb = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bb.getGraphics();
			g.drawImage(myBackground, 0, 0, null);
			myBackground = bb;
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		BufferedImage image = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.drawImage(myBackground, 0, 0, null);
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(font.getStringImage("TILE RESOURCES", 1), 10, 10, null);

		MapCell currentCell = null;
		if(((ModelMap)myComponent).getCurrentHighlightedCell()!=null){
			currentCell = ((ModelMap)myComponent).getCurrentHighlightedCell();
		}
		else if(((ModelMap)myComponent).getSelectedCell()!=null){
			currentCell = ((ModelMap)myComponent).getSelectedCell();
		}
		if(currentCell!=null){
			g.drawImage(font.getStringImage("OIL:", 1), 10, 30, null);
			String s = currentCell.getOil() + "";
			g.drawImage(font.getStringImage(s, 1), 40, 30, null);
			int counter = 0;
			for(int i=0; i<5; i++){
				if(currentCell.getOre(i)!=0){
					g.drawImage(font.getStringImage(currentCell.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1), 10, 50 + (20*counter), null);
					s = currentCell.getOre(i) + "";
					g.drawImage(font.getStringImage(s, 1), 110, 50 + (20*counter), null);
					
					counter++;
				}
			}
		}
		else{
			g.drawImage(font.getStringImage("NO TILE SELECTED", 1), 10, 30, null);
		}	
		return image;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
	
}

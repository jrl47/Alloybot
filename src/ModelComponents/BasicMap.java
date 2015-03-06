package ModelComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class BasicMap extends ModelMap{

	private static BufferedImage terrainDataImage;
	private static BufferedImage oilDataImage;
	private static BufferedImage oreDataImage;
	public BasicMap(ResourceManager r){
		super(getImage().getWidth(), getImage().getHeight(), r);
		for(int i=0; i<getImage().getWidth(); i++){
			for(int j=0; j<getImage().getHeight(); j++){
				if(getImage().getRGB(i, j)==-8421505)
					myCells.setCell(i, j, myFactory.makeLargeRocksCell(i, j));
				else if(getImage().getRGB(i, j)==-3947581)
					myCells.setCell(i, j, myFactory.makeSmallRocksCell(i, j));
				else if(getImage().getRGB(i, j)==-4621737)
					myCells.setCell(i, j, myFactory.makeDirtCell(i, j));
				else if(getImage().getRGB(i, j)==-4856291)
					myCells.setCell(i, j, myFactory.makeGrassCell(i, j));
				else if(getImage().getRGB(i, j)==-16735512)
					myCells.setCell(i, j, myFactory.makeWaterCell(i, j));
				else if(getImage().getRGB(i, j)==-20791)
					myCells.setCell(i, j, myFactory.makeFlowersCell(i, j));
				else if(getImage().getRGB(i, j)==-1)
					myCells.setCell(i, j, myFactory.makeBricksCell(i, j));
				else if(getImage().getRGB(i, j)==-12629812)
					myCells.setCell(i, j, myFactory.makeShoalsCell(i, j));
				else if(getImage().getRGB(i, j)==-14503604)
					myCells.setCell(i, j, myFactory.makeForestCell(i, j));
				else{
//					System.out.println(getImage().getRGB(i, j));
				}
				setResources(i, j);
			}
		}
		setX(getImage().getWidth()/2);
		setY(getImage().getHeight()/2);
	}
	public static BufferedImage getImage(){
		if(terrainDataImage==null){
		try {
			terrainDataImage = ImageIO.read(ScreenBuilder.class.getResource("/standardMap.png"));
		} catch (IOException e) {
			return null;
		}
		}
		return terrainDataImage;
	}
	public BufferedImage getOilImage(){
		if(oilDataImage==null){
		try {
			oilDataImage = ImageIO.read(ScreenBuilder.class.getResource("/standardMapOil.png"));
		} catch (IOException e) {
			return null;
		}
		}
		return oilDataImage;
	}

	protected BufferedImage getOreImage() {
		if(oreDataImage==null){
		try {
			oreDataImage = ImageIO.read(ScreenBuilder.class.getResource("/standardMapOre.png"));
		} catch (IOException e) {
			return null;
		}
		}
		return oreDataImage;
	}
	@Override
	public void respond() {
		// TODO Auto-generated method stub
		
	}

}

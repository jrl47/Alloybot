package ModelComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import View.ScreenBuilder;

public class BasicMap extends ModelMap{

	private static BufferedImage dataImage;
	public BasicMap(){
		super(getImage().getWidth(), getImage().getHeight());
		for(int i=0; i<getImage().getWidth(); i++){
			for(int j=0; j<getImage().getHeight(); j++){
				if(getImage().getRGB(i, j)==-8421505)
					myCells.setCell(i, j, myFactory.makeLargeRocksCell(i, j));
				if(getImage().getRGB(i, j)==-3947581)
					myCells.setCell(i, j, myFactory.makeSmallRocksCell(i, j));
				if(getImage().getRGB(i, j)==-4621737)
					myCells.setCell(i, j, myFactory.makeDirtCell(i, j));
				if(getImage().getRGB(i, j)==-4856291)
					myCells.setCell(i, j, myFactory.makeGrassCell(i, j));
				if(getImage().getRGB(i, j)==-16735512)
					myCells.setCell(i, j, myFactory.makeWaterCell(i, j));
			}
		}
		setX(getImage().getWidth()/2);
		setY(getImage().getHeight()/2);
	}
	public static BufferedImage getImage(){
		if(dataImage==null){
		try {
			dataImage = ImageIO.read(ScreenBuilder.class.getResource("/standardMap.png"));
		} catch (IOException e) {
			return null;
		}
		}
		return dataImage;
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void respond() {
		// TODO Auto-generated method stub
		
	}

}

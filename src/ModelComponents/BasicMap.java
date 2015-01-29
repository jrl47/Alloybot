package ModelComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicMap extends ModelMap{

	public BasicMap(){
		super(50, 50);
		for(int i=0; i<50; i++){
			for(int j=0; j<50; j++){
				Random r = new Random();
				int in = r.nextInt(190);
				if(in<140){
					myCells.setCell(i, j, myFactory.makeGrassCell(i, j));
				}
				else if(in<184){
					myCells.setCell(i, j, myFactory.makeDirtCell(i, j));
				}
				else if (in<189){
					myCells.setCell(i, j, myFactory.makeSmallRocksCell(i, j));
				}
				else if (in==189){
					myCells.setCell(i, j, myFactory.makeLargeRocksCell(i, j));
				}
			}
		}
		setX(15);
		setY(15);
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

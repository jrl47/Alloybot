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
				boolean b = r.nextBoolean();
				if(b){
					myCells.setCell(i, j, myFactory.makeGrassCell(i, j));
				}
				else{
					myCells.setCell(i, j, myFactory.makeDirtCell(i, j));
				}
//				if((i + j) % 2 == 1){
//					myCells.setCell(i,j, myFactory.makeGrassCell());
//				}
//				else{
//					myCells.setCell(i,j, myFactory.makeDirtCell());
//				}
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

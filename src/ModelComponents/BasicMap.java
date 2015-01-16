package ModelComponents;

import java.util.ArrayList;
import java.util.List;

public class BasicMap extends ModelMap{

	public BasicMap(){
		super(50, 50);
		for(int i=0; i<50; i++){
			for(int j=0; j<50; j++){
				if((i + j) % 2 == 1){
					myCells.setCell(i,j, myFactory.makeGrassCell());
				}
				else{
					myCells.setCell(i,j, myFactory.makeDirtCell());
				}
			}
		}
		setX(0);
		setY(0);
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

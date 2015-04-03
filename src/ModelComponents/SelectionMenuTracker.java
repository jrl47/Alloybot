package ModelComponents;

public class SelectionMenuTracker extends ModelComponent{
	
	private SelectionMenuOption mySelectedOption;
	private SelectionMenuOption myPrev;
	public void setOption(SelectionMenuOption m){
		myPrev = mySelectedOption;
		mySelectedOption = m;
	}
	public SelectionMenuOption getSelectedOption(){
		return mySelectedOption;
	}
	public void deselect(){
		myPrev = mySelectedOption;
		mySelectedOption = null;
	}
	public int getSelectedIndex(){
		if(mySelectedOption==null)
			return -1;
		return mySelectedOption.getIndex();
	}
	public int getPrevIndex(){
		if(myPrev==null)
			return -1;
		return myPrev.getIndex();
	}
	public void step() {
		
	}
	public void respond() {
		
	}	
}
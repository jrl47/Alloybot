package ModelComponents;

public class SelectionMenuTracker extends ModelComponent{
	
	private SelectionMenuOption mySelectedOption;
	public void setOption(SelectionMenuOption m){
		mySelectedOption = m;
	}
	public SelectionMenuOption getSelectedOption(){
		return mySelectedOption;
	}
	public void deselect(){
		mySelectedOption = null;
	}
	public int getSelectedIndex(){
		if(mySelectedOption==null)
			return -1;
		return mySelectedOption.getIndex();
	}
	public void step() {
		
	}
	public void respond() {
		
	}	
}
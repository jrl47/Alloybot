package ModelComponents;

public class SelectionMenuTracker extends ModelComponent{
	
	private SelectionMenuOption mySelectedOption;
	public void setOption(SelectionMenuOption m){
		mySelectedOption = m;
	}
	public SelectionMenuOption getSelectedOption(){
		return mySelectedOption;
	}
	public void step() {
		
	}
	public void respond() {
		
	}	
}
package ModelComponents;

public class SelectionMenuOption {
	
	private SelectionMenuTracker myTracker;
	private int myIndex;
	public SelectionMenuOption(SelectionMenuTracker tracker, int index){
		myTracker = tracker;
	}
	public int getIndex(){
		return myIndex;
	}
	public void setOption(){
		myTracker.setOption(this);
	}
}
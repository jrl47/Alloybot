package ModelComponents;

public class SelectionMenuOption extends ModelComponent{
	
	private SelectionMenuTracker myTracker;
	private int myIndex;
	public SelectionMenuOption(SelectionMenuTracker tracker, int index){
		myTracker = tracker;
		myIndex = index;
	}
	public int getIndex(){
		return myIndex;
	}
	public void setOption(){
		myTracker.setOption(this);
	}
	@Override
	public void step() {
		
	}
	@Override
	public void respond() {
		setOption();
	}
}
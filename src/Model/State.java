package Model;

public abstract class State {
	
	public abstract void step();
	
	public abstract ViewData getViewData();
}

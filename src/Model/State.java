package Model;

public class State {
	private String myString;
	private boolean myLoadStatus;
	public State(String string){
		myString = string;
	}
	public void setState(String string){
		myString = string;
	}
	public void setState(State state){
		myString = state.getState();
	}
	public String getState(){
		return myString;
	}
	public boolean getLoadStatus(){
		return myLoadStatus;
	}
	public void setLoadStatus(boolean b){
		myLoadStatus = b;
	}
}

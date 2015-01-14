package Model;

public class State {
	private String myString;
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
}

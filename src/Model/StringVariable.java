package Model;

public class StringVariable {
	String myString;
	public StringVariable(String string){
		myString = string;
	}
	public void setString(String string){
		myString = string;
	}
	public void setString(StringVariable string){
		myString = string.getString();
	}
	public String getString(){
		return myString;
	}
}

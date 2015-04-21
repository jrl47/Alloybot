package ModelComponents;

public class SmeltButton extends ModelButton{

	private boolean wasTriggered;
	@Override
	public void respond() {
		wasTriggered = true;
	}

	
	public boolean wasTriggered(){
		return wasTriggered;
	}
}

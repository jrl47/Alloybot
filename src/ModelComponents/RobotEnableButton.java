package ModelComponents;

public class RobotEnableButton extends ModelButton{
	
	Robot myRobot;
	public RobotEnableButton(Robot r){
		myRobot = r;
	}
	@Override
	public void respond() {
		myRobot.enabled();
	}
}

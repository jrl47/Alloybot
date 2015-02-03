package ModelComponents;

public class RobotEnableButton extends ModelButton{
	
	Robot myRobot;
	public RobotEnableButton(Robot r){
		myRobot = r;
	}
	public void respond() {
		myRobot.enabled();
	}
}

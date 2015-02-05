package ModelComponents;

public class RobotStopButton extends ModelButton{
	Robot myRobot;
	public RobotStopButton(Robot r){
		myRobot = r;
	}
	@Override
	public void respond() {
		myRobot.deselect();
		myRobot.disabled();
	}
}

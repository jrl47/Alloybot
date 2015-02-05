package ModelComponents;

public class RobotDeselectButton extends ModelButton{
	Robot myRobot;
	public RobotDeselectButton(Robot r){
		myRobot = r;
	}
	@Override
	public void respond() {
		myRobot.deselect();
	}
}

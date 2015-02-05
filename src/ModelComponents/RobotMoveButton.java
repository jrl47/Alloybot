package ModelComponents;

public class RobotMoveButton extends ModelButton{
	Robot myRobot;
	public RobotMoveButton(Robot r){
		myRobot = r;
	}
	@Override
	public void respond() {
		myRobot.prepareMove();
	}
}

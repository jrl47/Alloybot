package ModelComponents;

public class RobotDestroyButton extends ModelButton{
	Robot myRobot;
	ResourceManager resources;
	public RobotDestroyButton(Robot r, ResourceManager m){
		myRobot = r;
		resources = m;
	}
	@Override
	public void respond() {
		resources.setGems(resources.getGems()+(int)Math.pow(myRobot.getSize(),myRobot.getSize()));
		myRobot.destroy();
	}
}

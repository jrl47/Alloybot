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
		myRobot.destroy();
		resources.setGems(resources.getGems()+1);
	}
}

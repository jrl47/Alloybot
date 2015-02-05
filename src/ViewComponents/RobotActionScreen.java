package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ModelComponents.ModelButton;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.Robot;
import View.AlloyFont;
import View.ScreenBuilder;

public class RobotActionScreen extends ViewComponent{
	private BufferedImage myBackground;
	private ModelMap myMap;
	private AlloyBorderedButton myMoveButton;
	private AlloyBorderedButton myMineButton;
	private AlloyBorderedButton myDeselectButton;
	private AlloyBorderedButton myStopButton;
	private boolean needsButton;
	public RobotActionScreen(ModelComponent m, int xx, int yy){
		super(null, xx, yy, 100, 300);
		myMap = (ModelMap)m;
		needsButton = true;
		myMoveButton = null;
		myMineButton = null;
		myDeselectButton = null;
		myStopButton = null;
		try {
			myMoveButton = new AlloyBorderedButton(null, 10, 10, "MOVE", 1);
			myMineButton = new AlloyBorderedButton(null, 10, 34, "MINE", 1);
			myDeselectButton = new AlloyBorderedButton(null, 10, 58, "DESELECT", 1);
			myStopButton = new AlloyBorderedButton(null, 10, 82, "STOP", 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public BufferedImage loadImage() {
		if(myBackground==null){
		try {
			myBackground = ImageIO.read(ScreenBuilder.class.getResource("/robotactionbackground.png"));
			BufferedImage bb = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bb.getGraphics();
			g.drawImage(myBackground, 0, 0, null);
			myBackground = bb;
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		myImage = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = myImage.getGraphics();
		g.drawImage(myBackground, 0, 0, null);
		if(myMap.getSelectedRobot()!=null){
			if(needsButton){
				myMoveButton.setComponent(myMap.getSelectedRobot().getButtons().get(0));
				addComponent(myMoveButton);
				myMineButton.setComponent(myMap.getSelectedRobot().getButtons().get(1));
				addComponent(myMineButton);
				myDeselectButton.setComponent(myMap.getSelectedRobot().getButtons().get(2));
				addComponent(myDeselectButton);
				myStopButton.setComponent(myMap.getSelectedRobot().getButtons().get(3));
				addComponent(myStopButton);
				needsButton = false;
			}
		}
		else{
			removeComponent(myMoveButton);
			removeComponent(myMineButton);
			removeComponent(myDeselectButton);
			removeComponent(myStopButton);
			needsButton = true;
		}
		drawComponents();
		return myImage;
	}
	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
}

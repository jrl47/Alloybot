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
	private AlloyBorderedButton myDestroyButton;
	private boolean needsButton;
	public RobotActionScreen(ModelMap m, int xx, int yy){
		super(null, xx, yy, 100, 300);
		myMap = (ModelMap)m;
		needsButton = true;
		myMoveButton = null;
		myMineButton = null;
		myDeselectButton = null;
		myStopButton = null;
		myDestroyButton = null;
		try {
			myMoveButton = new AlloyBorderedButton(null, 10, 46, "MOVE", 1);
			myMineButton = new AlloyBorderedButton(null, 10, 66, "MINE", 1);
			myDeselectButton = new AlloyBorderedButton(null, 10, 86, "DESELECT", 1);
			myStopButton = new AlloyBorderedButton(null, 10, 106, "STOP", 1);
			myDestroyButton = new AlloyBorderedButton(null, 10, 126, "DESTROY", 1);
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
		Robot r;
		if(myMap.getSelectedObject()!=null){
			if(needsButton){
				if(myMap.getSelectedObject() instanceof Robot){
					r = (Robot)myMap.getSelectedObject();
					try {
						addComponent(new AlloyText(r.getName().toUpperCase() + " ROBOT", 1, 10, 6));
						addComponent(new AlloyText("SIZE " + r.getSize(), 1, 10, 26));
					} catch (IOException e) {
						e.printStackTrace();
					}
					myMoveButton.setComponent(r.getButtons().get(0));
					addComponent(myMoveButton);
					myMineButton.setComponent(r.getButtons().get(1));
					addComponent(myMineButton);
					myDeselectButton.setComponent(r.getButtons().get(2));
					addComponent(myDeselectButton);
					myStopButton.setComponent(r.getButtons().get(3));
					addComponent(myStopButton);
					myDestroyButton.setComponent(r.getButtons().get(4));
					addComponent(myDestroyButton);
					needsButton = false;
				}
			}
		}
		else{
			removeComponents();
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

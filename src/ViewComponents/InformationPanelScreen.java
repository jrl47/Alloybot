package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Model.OreData;
import ModelComponents.MapCell;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.Robot;
import View.ScreenBuilder;

public class InformationPanelScreen extends ViewComponent{
	
	private BufferedImage myBackground;
	private Background background;
	private ModelMap myMap;
	private AlloyBorderedButton myMoveButton;
	private AlloyBorderedButton myMineButton;
	private AlloyBorderedButton myDeselectButton;
	private AlloyBorderedButton myStopButton;
	private AlloyBorderedButton myDestroyButton;
	private boolean needsButton;
	private List<ViewComponent> myTileComponents;
	private List<ViewComponent> myRobotComponents;
	public InformationPanelScreen(ModelComponent c, ModelMap m, int xx, int yy){
		super(c, xx, yy, 100, 300);
		myMap = (ModelMap)m;
		needsButton = true;
		myMoveButton = null;
		myMineButton = null;
		myDeselectButton = null;
		myStopButton = null;
		myDestroyButton = null;
		createBackground();
		myTileComponents = new ArrayList<ViewComponent>();
		myRobotComponents = new ArrayList<ViewComponent>();
		try {
			addComponent(new AlloyText("TILE RESOURCES", 1, 10, 10));
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
		myImage = new BufferedImage(myBackground.getWidth(), myBackground.getHeight(), BufferedImage.TYPE_INT_ARGB);
		try{
			createTileInfo();
//			createRobotActionInfo();
		} catch(IOException e) {e.printStackTrace();}
		drawComponents();
		return myImage;
	}

	private void createRobotActionInfo() throws IOException {
		Robot r;
		if(myMap.getSelectedObject()!=null){
			addComponent(new AlloyText("OIL:", 5, 10, 10));
			if(needsButton){
				if(myMap.getSelectedObject() instanceof Robot){
					System.out.println("YEE");
					r = (Robot)myMap.getSelectedObject();

					addComponent(new AlloyText("OIL:", 5, 10, 10));
					AlloyText a = new AlloyText(r.getName().toUpperCase() + " ROBOT", 1, 10, 6);
					AlloyText b = new AlloyText("SIZE " + r.getSize(), 1, 10, 26);
					addComponent(a);
					addComponent(b);
					myRobotComponents.add(a);
					myRobotComponents.add(b);
					myMoveButton.setComponent(r.getButtons().get(0));
					addComponent(myMoveButton);
					myRobotComponents.add(myMoveButton);
					myMineButton.setComponent(r.getButtons().get(1));
					addComponent(myMineButton);
					myRobotComponents.add(myMineButton);
					myDeselectButton.setComponent(r.getButtons().get(2));
					addComponent(myDeselectButton);
					myRobotComponents.add(myDeselectButton);
					myStopButton.setComponent(r.getButtons().get(3));
					addComponent(myStopButton);
					myRobotComponents.add(myStopButton);
					myDestroyButton.setComponent(r.getButtons().get(4));
					addComponent(myDestroyButton);
					myRobotComponents.add(myDestroyButton);
					needsButton = false;
				}
			}
		}
		else{
			System.out.println("antiYEE");
			for(ViewComponent v: myRobotComponents){
				removeComponent(v);
			}
			needsButton = true;
		}
	}

	private void createTileInfo() throws IOException {
		for(ViewComponent v: myTileComponents){
			removeComponent(v);
		}

		MapCell currentCell = null;
		if(((ModelMap)myComponent).getCurrentHighlightedCell()!=null){
			currentCell = ((ModelMap)myComponent).getCurrentHighlightedCell();
		}
		else if(((ModelMap)myComponent).getSelectedCell()!=null){
			currentCell = ((ModelMap)myComponent).getSelectedCell();
		}
		if(currentCell!=null){
			AlloyText a = new AlloyText("OIL:", 1, 10, 30);
			myTileComponents.add(a);
			addComponent(a);
			String s = currentCell.getOil() + "";
			a = new AlloyText(s, 1, 40, 30);
			myTileComponents.add(a);
			addComponent(a);
			int counter = 0;
			for(int i=0; i<21; i++){
				if(currentCell.getOre(i)!=0){
					a = new AlloyText(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1, 10, 50 + (20*counter));
					myTileComponents.add(a);
					addComponent(a);
					s = currentCell.getOre(i) + "";
					a = new AlloyText(s + "%", 1, 110, 50 + (20*counter));
					myTileComponents.add(a);
					addComponent(a);
					counter++;
				}
			}
		}
		else{
			AlloyText a = new AlloyText("NO TILE SELECTED", 1, 10, 30);
			myTileComponents.add(a);
			addComponent(a);
		}
	}

	private void createBackground() {
		if(background==null){
		try {
			myBackground = ImageIO.read(ScreenBuilder.class.getResource("/tiledatabackground.png"));
			background = new Background(0,0,myBackground);
		} catch (IOException e) {e.printStackTrace();}
		}
		addComponent(background);
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}
	
}
package ViewComponents;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.Game;
import Model.Model;
import Model.OreData;
import Model.State;
import ModelComponents.ModelMap;
import ModelComponents.ResourceManager;
import ModelComponents.StateChangeButton;
import View.ScreenBuilder;

public class InventoryScreen extends ViewComponent{

	private Background background;
	private ModelMap myMap;
	private ResourceManager myManager;
	private State myState;
	private StateChangeButton myOil;
	private StateChangeButton myOre;
	private StateChangeButton myGems;
	private StateChangeButton myBack;
	public InventoryScreen(ModelMap m, int xx, int yy) {
		super(null, xx, yy);
		myMap = m;
		myManager = m.getResources();
		background = null;
		myState = new State("Back");
		myOil = new StateChangeButton(myState, "Oil");
		myOre = new StateChangeButton(myState, "Ore");
		myGems = new StateChangeButton(myState, "Gems");
		myBack = new StateChangeButton(myState, "Back");
		buildComponents();
	}

	private void buildComponents() {
		try{
			removeComponents();
			
			if(background==null)
				background = new Background(0,0,ImageIO.read(ScreenBuilder.class.getResource("/inventorybackground.png")));	
			addComponent(background);
//			addComponent(new AlloyText("OIL:", 1, 10, 30));
//			String s = myManager.getOil() + "";
//			addComponent(new AlloyText(s, 1, 40, 30));
//			int counter = 0;
//			for(int i=0; i<5; i++){
//				if(myManager.getOre(i)!=0){
//					addComponent(new AlloyText(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1, 10, 50 + (20*counter)));
//					s = myManager.getOre(i) + "";
//					addComponent(new AlloyText(s, 1, 110, 50 + (20*counter)));
//					counter++;
//				}
//			}
//			addComponent(new AlloyText("GEMS:", 1, 10, 50 + (20*counter)));
//			s = myManager.getGems() + "";
//			addComponent(new AlloyText(s, 1, 46, 50 + (20*counter)));
			
			
			if(myState.getState().equals("Oil")){
				addComponent(new AlloyText("OIL:", 5, 10, 10));
				addComponent(new AlloyText(myManager.getOil() + "", 5, 175, 10));
			}
			if(myState.getState().equals("Ore")){
				addComponent(new AlloyText("ORE:", 5, 10, 10));
				String s;
				int counter = 0;
				for(int i=0; i<21; i++){
					if(myManager.getOre(i)!=0){
						addComponent(new AlloyText(OreData.getOreObject(i).getMyName().toUpperCase() + " ORE:", 1, 10, 50 + (20*counter)));
						s = myManager.getOre(i) + "";
						addComponent(new AlloyText(s, 1, 110, 50 + (20*counter)));
						counter++;
					}
				}
			}
			if(myState.getState().equals("Gems")){
				addComponent(new AlloyText("GEMS:", 5, 10, 10));
				addComponent(new AlloyText(myManager.getGems() + "", 5, 210, 10));
			}
			if(myState.getState().equals("Back")){
				addComponent(new AlloyBorderedButton(myOil, 60, 60, "OIL", 7));
				addComponent(new AlloyBorderedButton(myOre, 280, 60, "ORE", 7));
				addComponent(new AlloyBorderedButton(myGems, 500, 60, "GEMS", 7));
			}
		}
		catch(IOException e) {e.printStackTrace();
		
		
		}
	}

	@Override
	public BufferedImage loadImage() {
		if((myState.getPreviousState()!=null && !myState.getState().equals(myState.getPreviousState())) || (!myState.getState().equals("Back") && (Game.ticks % Model.TICK_SCALAR )==0)){
			buildComponents();
		}
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		drawComponents();
		return myImage;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}

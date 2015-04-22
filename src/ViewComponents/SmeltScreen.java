package ViewComponents;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.OreData;
import ModelComponents.ModelComponent;
import ModelComponents.ModelMap;
import ModelComponents.Ore;
import ModelComponents.ResourceManager;
import ModelComponents.RobotCreationButton;
import View.AlloyFont;
import ModelComponents.SmeltButton;

public class SmeltScreen extends ViewComponent{

	private ResourceManager myManager;
	private AlloyBorderedSelectionMenu myOreSelection;
	private AlloyBorderedSelectionMenu myAmountSelection;
	private AlloyBorderedButton mySmeltButton;
	private int myOreIndex;
	private int myAmountIndex;
	private boolean loaded;

	public SmeltScreen(ResourceManager resourceManager,
			SmeltButton smeltButton, int i, int j) {
		super(null, i, j, 800, 450);
		myManager = resourceManager;
		mySmeltButton = null;
		try {
			mySmeltButton = new AlloyBorderedButton(smeltButton, 10, 230, "SMELT!", 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		createOreMenu();
		createAmountMenu();
	}

	private void createOreMenu() {
		myOreSelection = null;
		try {
			myOreSelection = new AlloyBorderedSelectionMenu(714, 26, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<List<String>> menuStrings = new ArrayList<List<String>>();
		for(int i=0; i<3; i++){
			menuStrings.add(new ArrayList<String>());
		}
		for(int i=0; i<21; i++){
			menuStrings.get(i/7).add(OreData.getOreObject(i).getMyName());
		}
		myOreSelection.loadSelection(menuStrings);
		myComponents.add(myOreSelection);
	}
	
	private void createAmountMenu() {
		myAmountSelection = null;
		try {
			myAmountSelection = new AlloyBorderedSelectionMenu(570, 26, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<List<String>> menuStrings = new ArrayList<List<String>>();
		for(int i=0; i<2; i++){
			menuStrings.add(new ArrayList<String>());
		}
		menuStrings.get(0).add("SIZE 1 (1 ORE)");
		menuStrings.get(0).add("SIZE 2 (4 ORE)");
		menuStrings.get(0).add("SIZE 3 (27 ORE)");
		menuStrings.get(0).add("SIZE 4 (256 ORE)");
		menuStrings.get(1).add("SIZE 5 (3125 ORE)");
		menuStrings.get(1).add("SIZE 6 (46656 ORE)");
		menuStrings.get(1).add("SIZE 7 (823543 ORE)");
		myAmountSelection.loadSelection(menuStrings);
		myComponents.add(myAmountSelection);
	}

	@Override
	public BufferedImage loadImage() {
		if(((SmeltButton) mySmeltButton.getComponent()).wasTriggered()){
			loaded = false;
		}
		myImage = new BufferedImage(800, 450, BufferedImage.TYPE_INT_ARGB);
		Graphics g = myImage.getGraphics();
		AlloyFont font = null;
		try {
			font = new AlloyFont();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(myOreSelection.getSelectedIndex()!=myOreIndex || myAmountSelection.getSelectedIndex()!=myAmountIndex){
			loaded = false;
			myOreIndex = myOreSelection.getSelectedIndex();
			myAmountIndex = myAmountSelection.getSelectedIndex();
		}
		if(myOreSelection.getSelectedIndex()!=-1 && myAmountSelection.getSelectedIndex()!=-1 && !loaded){
			removeComponents();
			myComponents.add(myOreSelection);
			myComponents.add(myAmountSelection);
			try {
				addComponent(new AlloyText("COST:", 2, 10, 10));
				int amount = myAmountSelection.getSelectedIndex()+1;
				int sizeCost = (int) Math.pow(amount, amount);
				int oreIndex = myOreSelection.getSelectedIndex();
				Ore ore = OreData.getOreObject(oreIndex);
				List<Ore> parents = ore.getMyParents();
				String oreName = parents.get(0).getMyName().toUpperCase();
				String oreName2 = parents.get(1).getMyName().toUpperCase();
				addComponent(new AlloyText(sizeCost + " " +  oreName + ",", 2, 86, 10));
				addComponent(new AlloyText(sizeCost + " " +  oreName2 + ",", 2, 86, 40));
				addComponent(new AlloyText("1 GEM", 2, 86, 70));
			
				addComponent(new AlloyText(oreName, 2, 10, 100));
				addComponent(new AlloyText("AVAILABLE:", 2, oreName.length()*14 + 24, 100));
				addComponent(new AlloyText(Integer.toString(myManager.getOre(parents.get(0).getMyIndex())), 2, oreName.length()*14 + 24 + 14 + 140, 100));
				
				addComponent(new AlloyText(oreName2, 2, 10, 130));
				addComponent(new AlloyText("AVAILABLE:", 2, oreName.length()*14 + 24, 130));
				addComponent(new AlloyText(Integer.toString(myManager.getOre(parents.get(1).getMyIndex())), 2, oreName.length()*14 + 24 + 14 + 140, 130));
				
				boolean cantPay = false;
				for(Ore o: parents){
					if(amount > myManager.getOre(o.getMyIndex())){
						cantPay = true;
					}
				}
				if(cantPay){ 
					removeComponent(mySmeltButton);
					addComponent(new AlloyText("NOT ENOUGH RESOURCES", 2, 10, 230));
				}
				else{
//					((SmeltButton)mySmeltButton.getComponent()).setCost(oreIndex, amount);
					addComponent(mySmeltButton);
				}
				
				addComponent(new AlloyText("OIL EFFICIENCY:", 1, 10, 270));
				addComponent(new AlloyText(Integer.toString(ore.getMyOil()), 1, "OIL EFFICIENCY:".length()*7 + 17, 270));
				int length1 = "OIL EFFICIENCY:".length()*7 + 24 + Integer.toString(ore.getMyOil()).length()*7 + 14;
				
				addComponent(new AlloyText("ORE EFFICIENCY:", 1, length1, 270));
				addComponent(new AlloyText(Integer.toString(ore.getMyOre()), 1, "ORE EFFICIENCY:".length()*7 + 7 + length1, 270));
				int length2 = length1 + "ORE EFFICIENCY".length()*7 + Integer.toString(ore.getMyOre()).length()*7 + 14 + 14 + 14;
				
				addComponent(new AlloyText("GEM EFFICIENCY:", 1, length2, 270));
				addComponent(new AlloyText(Integer.toString(ore.getMyGems()), 1, "GEM EFFICIENCY:".length()*7 + 7 + length2, 270));
				
				
				addComponent(new AlloyText("DIVERSITY:", 1, 10, 290));
				addComponent(new AlloyText(Integer.toString(ore.getMyDiversity()), 1, "DIVERSITY:".length()*7 + 17, 290));
				length1 = "DIVERSITY:".length()*7 + 24 + Integer.toString(ore.getMyDiversity()).length()*7 + 14;
				
				addComponent(new AlloyText("DISTANCE:", 1, length1, 290));
				addComponent(new AlloyText(Integer.toString(ore.getMyDistance()), 1, "DISTANCE:".length()*7 + 7 + length1, 290));
				length2 = length1 + "DISTANCE:".length()*7 + Integer.toString(ore.getMyDistance()).length()*7 + 14 + 14 + 14;
				
				addComponent(new AlloyText("LUCK:", 1, length2, 290));
				addComponent(new AlloyText(Integer.toString(ore.getMyLuck()), 1, "LUCK:".length()*7 + 7 + length2, 290));
				
			
				addComponent(new AlloyText("POWER:", 1, 10, 310));
				addComponent(new AlloyText(Integer.toString(ore.getMyPower()), 1, "POWER:".length()*7 + 17, 310));
				length1 = "POWER:".length()*7 + 24 + Integer.toString(ore.getMyPower()).length()*7 + 14;
				
				addComponent(new AlloyText("DURABILITY:", 1, length1, 310));
				addComponent(new AlloyText(Integer.toString(ore.getMyDurability()), 1, "DURABILITY:".length()*7 + 7 + length1, 310));
				length2 = length1 + "DURABILITY:".length()*7 + Integer.toString(ore.getMyDurability()).length()*7 + 14 + 14 + 14;
				
				addComponent(new AlloyText("MAGIC:", 1, length2, 310));
				addComponent(new AlloyText(Integer.toString(ore.getMyMagic()), 1, "MAGIC:".length()*7 + 7 + length2, 310));
				
				loaded = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (myOreSelection.getSelectedIndex()==-1 && myAmountSelection.getSelectedIndex()==-1){
			removeComponents();
			myComponents.add(myOreSelection);
			myComponents.add(myAmountSelection);
			try {
				addComponent(new AlloyText("SELECT A MATERIAL AND AMOUNT", 2, 10, 10));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		g.drawImage(font.getStringImage("AMOUNTS:", 1), 574, 7, null);
		g.drawImage(font.getStringImage("ORES:", 1), 718, 7, null);
		
		
		{ // old mini menu for temp testing purposes
			g.drawImage(font.getStringImage("OIL AVAILABLE:", 1), 10, 410, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getOil()), 1), 120, 410, null);
			g.drawImage(font.getStringImage("GEMS AVAILABLE:", 1), 10, 430, null);
			g.drawImage(font.getStringImage(Integer.toString(myManager.getGems()), 1), 132, 430, null);
		}
		
		drawComponents();
		return myImage;
	}

	@Override
	public BufferedImage loadHover() {
		return loadImage();
	}

}
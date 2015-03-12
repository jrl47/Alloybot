package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ModelComponents.Ore;

public class OreData {
	
	public static final int NUMBER_OF_ORES = 100;
	public static String[] oreData;
	public static Map<Integer, Ore> myOres;
	public static void init(){
    	// General file-reading stuff, nothing to see here
		oreData = new String[100];
    	URL myFile = OreData.class.getResource("/OreStats.txt");
    	BufferedReader b = null;
		try {
			b = new BufferedReader(new InputStreamReader(myFile.openStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	String currentLine = null;
		try {currentLine = b.readLine();} catch (IOException e) {e.printStackTrace();}
		int counter = 0;
    	while(currentLine!=null){
    		// Breaks up at colons
    		oreData[counter] = currentLine;
    		counter++;
    		try {currentLine = b.readLine();} catch (IOException e) {e.printStackTrace();}
    	}
		myOres = new HashMap<Integer, Ore>();
		for(int i=0; i<5; i++){
			myOres.put(i, new Ore(i));
		}
	}
	public static Ore getOreObject(int i){
		return myOres.get(i);
	}
}

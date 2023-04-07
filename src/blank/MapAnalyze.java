package blank;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class MapAnalyze {
	private BetterImage image;
	private File output;
	private FileWriter writer;
	
	public MapAnalyze(BetterImage i) {
		image = i;
		output = new File("output.txt");
		try {
			if(!output.createNewFile()) {
				output.delete();
				output.createNewFile();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
		try {
			writer = new FileWriter(output);
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	public int[] getNationSizes(int[] colors) {
	    int[] counter = new int[colors.length];
	    
	    for(int x = 0; x < image.getWidth(); x++) {
	    	for(int y = 0; y < image.getHeight(); y++) {
	    		for(int i = 0; i < colors.length; i++) {
	    			if(image.getRGB(x, y) == colors[i])
	    				counter[i]++;
	    		}
	    	}
	    	if(x%(image.getWidth()/10) == 0)
	    		System.out.println("Program " + (100*x/(image.getWidth())) + "% finished.");
	    }
	    
	    return counter;
	}
	
	public void writeNations(ArrayList<String> names, int[] values) {
		try {
			for(int i = 0; i < names.size(); i++) {
				writer.write(names.get(i) + ": " + values[i] + "\n");
			}
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Stop making me do try/catch");
		}
	}
	
	public String longestVertical() {
		int longest = 0;
		int longestXPos = 0, longestYPos = 0;
		int temp = 0;
		for(int x = 1; x < image.getWidth(); x++) {
			for(int y = 1; y < image.getHeight(); y++) {
				if(temp > longest) {
					longest = temp;
					longestXPos = x;
					longestYPos = y;
				}
				if((image.getRGB(x, y) == image.getRGB(x, y-1)) && image.getRGB(x, y) != 0) 
					temp++;
				else
					temp = 0;
			}
		}
		
		String longestString = "Longest vertical line is of length " + longest + " and ends at coordinate " + longestXPos + ", " + longestYPos;
		
		int[] highlight = {255, 255, 0, 0};
		for(int x = longestXPos; x < longestXPos; x++) {
			for(int y = longestYPos-longest; y < longestYPos; y++) {
				image.setRGB(x, y, highlight);
			}
		}
		
		return longestString;
	}
	
	public String longestHorizontal() {
		int longest = 0;
		int longestXPos = 0, longestYPos = 0;
		int temp = 0;
		
		for(int y = 1; y < image.getHeight(); y++) {
			for(int x = 1; x < image.getWidth(); x++) {
				if(temp > longest) {
					longest = temp;
					longestYPos = y;
					longestXPos = x; 
				}
				if((image.getRGB(x, y) == image.getRGB(x-1, y)) && image.getRGB(x, y) != 0) 
					temp++;
				else
					temp = 0;
			}
		}
		
		String longestString = "Longest horizontal line is of length " + longest + " and ends at coordinate " + longestXPos + ", " + longestYPos;

		int[] highlight = {255, 255, 0, 0};
		for(int y = longestYPos; y < longestYPos; y++) {
			for(int x = longestXPos-longest; x < longestXPos; x++) {
				image.setRGB(x, y, highlight);
			}
		}
		
		return longestString;
	}
	
	public ArrayList<Integer> getPerimeter(int startX, int startY){
		int searchTopY = startY;
		while(image.getRGB(startX, searchTopY) == image.getRGB(startX, startY)) { 
			searchTopY--;
		}
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(searchTopY);
		
		return a;
	}
}

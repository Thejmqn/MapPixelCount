package blank;

import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Map{
	public static void main(String args[]){
	
		BetterImage image = new BetterImage(3252, 3252, new File("Map.png"));
		MapTexter mapper = new MapTexter("input.txt", "output.txt");
		MapAnalyze analyzer = new MapAnalyze(image);
		
		ArrayList<String> names = mapper.getNames();
		ArrayList<String> colorsString = mapper.getColors();
		int[] colors = new int[names.size()];
	    int[] counter = new int[colors.length];
	    
	    for(int i = 0; i < colorsString.size(); i++) {
	    	colors[i] = mapper.rgbToInt(colorsString.get(i));
	    }
	    
	    counter = analyzer.getNationSizes(colors);
	    
	    analyzer.writeNations(names, counter);
	    
	    //System.out.println(analyzer.longestVertical());
		//System.out.println(analyzer.longestHorizontal());
		
		//System.out.println(analyzer.getPerimeter(107, 61));
		
	    try {
	        ImageIO.write(image.getImage(), "png", new File("Out.png"));
	        System.out.println("Image writing complete.");
	    }
	    catch (IOException e) {
	    	System.out.println("Error: " + e);
	    }
	    System.out.println("Program Finished.");
	}
}
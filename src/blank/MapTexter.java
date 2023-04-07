package blank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapTexter{
	private File input;
	private File output;
	private FileWriter writer;
	private Scanner reader;
	
	public MapTexter(String i, String o) {
		input = new File(i);
		output = new File(o);
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
			writer = new FileWriter(o);
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	//writes a one line message to the output file
	public void write(String message) {
		try {
			writer.write(message + "\n");
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	public ArrayList<String> getNames() {
		ArrayList<String> data = new ArrayList<String>();
		try {
			reader = new Scanner(input);
			while(reader.hasNextLine()) {
				String nextLine = reader.nextLine();
				data.add(nextLine.substring(0, nextLine.indexOf(':')));
			}
			reader.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
		return data;
	}
	
	public ArrayList<String> getColors(){
		ArrayList<String> data = new ArrayList<String>();
		try {
			reader = new Scanner(input);
			while(reader.hasNextLine()) {
				String nextLine = reader.nextLine();
				data.add(nextLine.substring(nextLine.indexOf(':')+2));
			}
			reader.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
		return data;
	}
	
	public int[] getRGB(ArrayList<String> list, int pos) {
		int[] data = new int[4];
		for(int i = 0; i < data.length; i++) {
			data[i] = Integer.parseInt(list.get(pos).substring(i*4, i*4+3));
		}
		return data;
	}
	
	public int rgbToInt(int[] rgb) {
		return ((rgb[0]<<24) | (rgb[1]<<16) | (rgb[2]<<8) | rgb[3]);
	}
	
	//BAD - MUST REPLACE. Skipping first value so just set it to 255? Don't know why but its 11 at night
	public int rgbToInt(String rgb) {
		int[] rgbArray = new int[4];
		rgbArray[0] = 255;
		int place = 1;
		for(int i = 0; i < rgb.length(); i++) {
			if(rgb.charAt(i) == ' ') {
				if(place < 3)
					rgbArray[place] = Integer.valueOf(rgb.substring(i+1, rgb.indexOf(' ', i+1)));
				else
					rgbArray[place] = Integer.valueOf(rgb.substring(i+1));
				place++;
			}
		}
		return rgbToInt(rgbArray);
	}
	
	public void writeClaims(ArrayList<Integer[]> claims) {
		try {
			for(int i = 0; i < claims.size(); i++) {
				writer.write("Claim " + (i+1) + ": " + claims.get(i)[0] + " " + claims.get(i)[1] + " " + claims.get(i)[2] + " " + claims.get(i)[3] + "\n");
			}
			writer.close();
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
}

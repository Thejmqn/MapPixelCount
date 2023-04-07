package blank;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class BetterImage{
	private BufferedImage image;
	
	public BetterImage(int w, int h, File f) {
		try {
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(f);
			System.out.println("Image reading complete.");
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void setAlpha(int x, int y, int val) {
		image.setRGB(x, y, ((val << 24) | ((image.getRGB(x, y)>>16 & 0xff) << 16) | ((image.getRGB(x, y)>>8 & 0xff) << 8) | (image.getRGB(x, y) & 0xff) & 0xff));
	}
	public void setRed(int x, int y, int val) {
		image.setRGB(x, y, (((image.getRGB(x, y)>>24 & 0xff) << 24) | (val << 16) | ((image.getRGB(x, y)>>8 & 0xff) << 8) | (image.getRGB(x, y) & 0xff) & 0xff));
	}
	public void setGreen(int x, int y, int val) {
		image.setRGB(x, y, (((image.getRGB(x, y)>>24 & 0xff) << 24) | ((image.getRGB(x, y)>>16 & 0xff) << 16) | (val << 8) | (image.getRGB(x, y) & 0xff) & 0xff));
	}
	public void setBlue(int x, int y, int val) {
		image.setRGB(x, y, (((image.getRGB(x, y)>>24 & 0xff) << 24) | ((image.getRGB(x, y)>>16 & 0xff) << 16) | ((image.getRGB(x, y)>>8 & 0xff) << 8) | (val & 0xff) & 0xff));
	}
	public void setRGB(int x, int y, int[] val) {
		setAlpha(x, y, val[0]);
		setRed(x, y, val[1]);
		setGreen(x, y, val[2]);
		setBlue(x, y, val[3]);
	}
	public int getAlpha(int x, int y) {
		return (image.getRGB(x, y)>>24 & 0xff);
	}
	public int getRed(int x, int y) {
		return (image.getRGB(x, y)>>16 & 0xff);
	}
	public int getGreen(int x, int y) {
		return (image.getRGB(x, y)>>8 & 0xff);
	}
	public int getBlue(int x, int y) {
		return (image.getRGB(x, y) & 0xff);
	}
	public int getRGB(int x, int y) {
		return image.getRGB(x, y);
	}
	public String getRGBString(int x, int y){
		return getAlpha(x, y) + " " + getRed(x, y) + " " + getGreen(x, y) + " " + getBlue(x, y);
	}
	public int getWidth() {
		return image.getWidth();
	}
	public int getHeight() {
		return image.getHeight();
	}
	public BufferedImage getImage() {
		return image;
	}
	public void write(File output) {
	    try {
	        ImageIO.write(getImage(), "png", output);
	        System.out.println("Image writing complete.");
	    }
	    catch (IOException e) {
	    	System.out.println("Error: " + e);
	    }		
	}
	public ArrayList<Integer[]> getClaimNames(){
		ArrayList<Integer[]> claims = new ArrayList<Integer[]>();
		for(int x = 0; x < getWidth(); x+=20) {
			for(int y = 0; y < getHeight(); y+=20) {
				Integer[] claim = new Integer[4];
				claim[0] = Integer.valueOf(getAlpha(x, y));
				claim[1] = Integer.valueOf(getRed(x, y));
				claim[2] = Integer.valueOf(getGreen(x, y));
				claim[3] = Integer.valueOf(getBlue(x, y));
				
				boolean present = false;
				for(int i = 0; i < claims.size(); i++) {
					if(Arrays.equals(claim, claims.get(i)))
						present = true;
				}
				if(!present)
					claims.add(claim);
			}
		}
		return claims;
	}
}
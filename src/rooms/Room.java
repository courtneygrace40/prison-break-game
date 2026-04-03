package rooms;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;

public class Room {
	Modify_Frame mf;
	KeyHandler kh;
	int playerx; //where the character spawns at first
	int playery;
	public BufferedImage rm;
	
	
	public void setImage(String filename) {
		try{
			rm = ImageIO.read(getClass().getResourceAsStream(filename));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		// Source - https://stackoverflow.com/a/9417836
		// Posted by Ocracoke, modified by community. See post 'Timeline' for change history
		// Retrieved 2026-02-11, License - CC BY-SA 3.0
			System.out.println(filename + " is being set up.");
		    Image tmp = rm.getScaledInstance(mf.frameWidth, mf.frameHeight, Image.SCALE_SMOOTH);
		    rm = new BufferedImage(mf.frameWidth, mf.frameHeight, BufferedImage.TYPE_INT_ARGB);  
		    Graphics2D g2d = rm.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = rm;
		g2.drawImage(image, 0, 0, null);
		
	}
	
	
	
}

package backgrounds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;

public class Background {
	
	//implementing a linked list of background objects 
	
	Modify_Frame mf;
	KeyHandler controls;
	
	Background nextBackground;
	Background previousBackground;
	boolean currBackground;
	
	BufferedImage bg;
	
	public Background(Modify_Frame mf, KeyHandler kh, String f) {
		this.mf = mf;
		this.controls = kh;
		setBackgroundImage(f);
		
	}
	
	public void setBackgroundImage(String filename) {
		try{
			bg = ImageIO.read(getClass().getResourceAsStream(filename));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Background getNextBackground() {
		return this.nextBackground;
	}
	public Background getPreviousBackground() {
		return this.previousBackground;
	}
	
	public void update() {
			//if player hits left right up down wall go to next background 
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = bg;
		g2.drawImage(image, 0, 0, null);
		
	}
}

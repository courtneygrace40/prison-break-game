package backgrounds;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;
import javax.swing.*;
import java.awt.Graphics;

public class Background extends JPanel{
	
	//implementing a linked list of background objects 
	private static final long serialVersionUID = 1L;
	
	Modify_Frame mf;
	KeyHandler controls;
	
	Background nextBackground;
	Background previousBackground;
	boolean currBackground;
	public enum ProgressionType {CLICK, AUTO, TRIGGER};
	public ProgressionType currentProgressionType;
	public BufferedImage bg;
	
	
	//When sending in the type of background, must be "CLICK" as a string exactly 
	public Background(Modify_Frame mf, KeyHandler kh, String f, String ProgressiveType) {
		this.mf = mf;
		this.controls = kh;
		
		
		if (ProgressiveType.equals("CLICK")) {
			this.currentProgressionType = ProgressionType.CLICK;
		} else if (ProgressiveType.equals("Trigger")) {
			this.currentProgressionType = ProgressionType.TRIGGER;
		}
		else {
			this.currentProgressionType = ProgressionType.AUTO;
		}
		
		setBackgroundImage(f);
		
	}
	
	public void setBackgroundImage(String filename) {
		try{
			bg = ImageIO.read(getClass().getResourceAsStream(filename));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		// Source - https://stackoverflow.com/a/9417836
		// Posted by Ocracoke, modified by community. See post 'Timeline' for change history
		// Retrieved 2026-02-11, License - CC BY-SA 3.0
			System.out.println(filename + " is being set up.");
		    Image tmp = bg.getScaledInstance(mf.frameWidth, mf.frameHeight, Image.SCALE_SMOOTH);
		    bg = new BufferedImage(mf.frameWidth, mf.frameHeight, BufferedImage.TYPE_INT_ARGB);  
		    Graphics2D g2d = bg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();
		    
	}
    
	public Background getNextBackground() {
		return this.nextBackground;
	}
	public Background getPreviousBackground() {
		return this.previousBackground;
	}
	
	public void setNextBackground(Background b) {
		this.nextBackground = b;
		
	}
	
	public void setPreviousBackground(Background b) {
		this.previousBackground = b;
	}
	
	public void setCurrentBackground(boolean x) {
		this.currBackground = x;
	}
	
	public void update() {
			//if player hits left right up down wall go to next background  (not implemented)
			
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = bg;
		g2.drawImage(image, 0, 0, null);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (bg != null) {
	        g2.drawImage(bg, 0, 0, null);
	    }
	}
}

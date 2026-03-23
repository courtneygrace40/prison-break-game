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
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class Background extends JPanel{
	
	//implementing a linked list of background objects 
	private static final long serialVersionUID = 1L;
	
	Modify_Frame mf;
	KeyHandler controls;
	
	Background nextBackground;
	Background previousBackground;
	boolean currBackground;
	public enum ProgressionType {CLICK, AUTO, TRIGGER, SKIP};
	public ProgressionType currentProgressionType;
	public BufferedImage bg;
	public boolean characterPaint; 
	public ProgressionType secondaryProgressionType;
	public boolean lastBackground;
	
	
	//When sending in the type of background, must be "CLICK" as a string exactly 
	public Background(Modify_Frame mf, KeyHandler kh, String f, String ProgressiveType, boolean charp, String sPT, boolean sb, boolean main, boolean lastFrame) {
		this.mf = mf;
		this.controls = kh;
		this.characterPaint = charp;
		this.lastBackground = lastFrame;
		
		if (ProgressiveType.equals("CLICK")) {
			this.currentProgressionType = ProgressionType.CLICK;
		} else if (ProgressiveType.equals("Trigger")) {
			this.currentProgressionType = ProgressionType.TRIGGER;
		}
		else {
			this.currentProgressionType = ProgressionType.AUTO;
		}
		if (sPT != null) {
			if(sPT.equals("SKIP")){
		
			this.secondaryProgressionType = ProgressionType.SKIP;
			}
		}
		
		setBackgroundImage(f);
		
		this.setLayout(new BorderLayout());
		
		
		if (sb) {
			JButton skipButton = this.buttonCreator("/buttons/buttontest.png", "RIGHT", "SOUTH", "SKIP");
			skipButton.addActionListener(mf);
			}
		
		if (main) {
			JButton startButton = this.buttonCreator("/buttons/buttontest.png", "CENTER", "SOUTH", "START");
			startButton.addActionListener(mf);
		}
		
		
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

	
	@Override
	protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			if (bg != null) {
		        g2.drawImage(bg, 0, 0, null);
		    }
			//this is not rly a great fix but it just changes the draw order and put it on the correct baackground 
			if (this.characterPaint) {
				mf.player1.draw(g2);
				mf.guard1.draw(g2);
				mf.door1.draw(g2);

			}
			
		}
	
	public JButton buttonCreator(String imagePath, String fl, String bl, String name) {
		int align;
		if (fl.equals("RIGHT")) {
			align = FlowLayout.RIGHT;
		} else {
			align = FlowLayout.CENTER;
		}
		
		String borderAlign;
		if (bl.equals("SOUTH")) {
			borderAlign = BorderLayout.SOUTH;
		} else {
			borderAlign = BorderLayout.CENTER;
		}
		
		JPanel buttonContainer = new JPanel(new FlowLayout(align));
		buttonContainer.setOpaque(false);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		JButton button = new JButton(imageIcon);
		button.setActionCommand(name);
		buttonContainer.add(button);
        this.add(buttonContainer, borderAlign);
		return(button);
	}

}

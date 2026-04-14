package rooms;  
import java.util.ArrayList;
import java.awt.Rectangle;

import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;
import javax.swing.*;

import backgrounds.Entrance;
import backgrounds.Space;
import backgrounds.Background.ProgressionType;

import java.awt.Graphics;


public class Room extends JPanel implements Space{
	
	//implementing a linked list of background objects 
	private static final long serialVersionUID = 1L;
	
	Modify_Frame mf;
	KeyHandler controls;
	
	public BufferedImage bg;
	public boolean characterPaint = false; 
	public boolean lastBackground;
	
	public int playerx = 100;
	public int playery = 100;
	
	public Entrance exit;
	public ArrayList<Entrance> entrances = new ArrayList<>();
	public ArrayList<Rectangle> walls = new ArrayList<>();
	
	public String key;
	public int location;
	
	public enum ProgressionType {CLICK, AUTO, TRIGGER, SKIP};
	public ProgressionType currentProgressionType;
	public ProgressionType secondaryProgressionType;
	
	public Room(Modify_Frame mf, KeyHandler kh, String f, boolean lastFrame) {
		this.mf = mf;
		this.controls = kh;
		this.lastBackground = lastFrame;
		
		this.currentProgressionType = ProgressionType.SKIP;
		this.secondaryProgressionType = null;
		
		setBackgroundImage(f);
		
		this.setLayout(null);
		
	}
	
	public void setPlayerCoords(int x, int y) {
		playerx = x;
		playery = y;
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
	
	public void update() {
			//if player hits left right up down wall go to next background  (not implemented)
			
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = bg;
		g2.drawImage(image, 0, 0, null);
		
	}
	
	public void setCharPaint(boolean x) {
		this.characterPaint = x;
	}
	
	public void setProgressionType(String progressionType, String sPT) {
		
		if (progressionType.equals("CLICK")) {
			this.currentProgressionType = ProgressionType.CLICK;
		} else if (progressionType.equals("Trigger")) {
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
		
	}
	
	
	public void setKey(String s) {
		this.key = s;
	}
	
	public void setLocation(int i) {
		this.location = i;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public int getMyLocation() {
		return this.location;
	}
	
	public void addEntrance(String entranceType, int xMin, int yMin, int xMax, int yMax) {
		Entrance newEntrance = new Entrance(this, entranceType, xMin, yMin, xMax, yMax);
		
		switch(entranceType) {
		case "top":
			this.exit = newEntrance;
		
		this.entrances.add(newEntrance);
		}
		
	}
	
		
		
		
	
	
	
	public void setButtons(boolean sb) {
		
		
		if (sb) {
			//JButton skipButton = this.buttonCreator("/buttons/buttontest.png", "RIGHT", "SOUTH", "SKIP");
		
			int w = 64;
			int h = 64;
			int x = mf.frameWidth - w - 20;
			int y = mf.frameHeight - h - 20;
			JButton skipButton = this.buttonCreator("/buttons/buttontest.png", x, y, w, h, "SKIP");
			skipButton.addActionListener(mf);
			
		}
		
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			if (bg != null) {
		        g2.drawImage(bg, 0, 0, null);
		    }
			//this is not rly a great fix but it just changes the draw order and put it on the correct baackground 
			
			if(this.characterPaint) {
				if (mf.indexBG >=4 && mf.indexBG < 5) {
					mf.player1.draw(g2);
					mf.guard1.draw(g2);
					//mf.door1.draw(g2);
				}
				else if (mf.indexBG >= 5) {
					mf.playerGuard.draw(g2);
					mf.guard1.draw(g2);
					//mf.door1.draw(g2);
				}
			} //paints the character in the specific order needed (for now, we can change if we need to)
			

		}

	
	public JButton buttonCreator(String imagePath, int x, int y, int h, int w, String name) {
		//buttonContainer.setOpaque(false);
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		JButton button = new JButton(imageIcon);
		button.setBounds(x, y, w, h);
		button.setActionCommand(name);
		button.setBorderPainted(false);
	    button.setContentAreaFilled(false);
	    button.setFocusPainted(false);
	    button.setOpaque(false);
		this.add(button);
		return(button);
		

	}

	public String getChallengeType() {
		// TODO Auto-generated method stub
		return "No Challenge Type" ;
	}
	
	public boolean getActiveChallenge() {
		return false;
	}
	
	public void setActiveChallenge(boolean b) {
		
	}
	
	@Override
	public ArrayList<Entrance> getEntrances() {
	    return this.entrances;
	}
	
	public int getPlayerX() {
		return(playerx);
	};
    public void setPlayerX(int x) {};
    public int getPlayerY() {
    	return(playery);
    };
    public void setPlayerY(int y) {};

    @Override
    public ArrayList<Rectangle> getWalls() {
    	return(this.walls);
    }

}


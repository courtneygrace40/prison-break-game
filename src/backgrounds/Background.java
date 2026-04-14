package backgrounds;  
import java.util.ArrayList;

import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;
import javax.swing.*;

import java.awt.Graphics;
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
import java.awt.Rectangle;

public class Background extends JPanel implements Space{
	
	//implementing a linked list of background objects 
	private static final long serialVersionUID = 1L;
	
	Modify_Frame mf;
	KeyHandler controls;
	Background nextBackground;
	Background previousBackground;
	boolean currBackground;
	
	public enum ProgressionType {CLICK, AUTO, TRIGGER, SKIP};
	public ProgressionType currentProgressionType;
	public ProgressionType secondaryProgressionType;
	
	public enum HType {HType1, HType2, HType3, HType4, HType5, HType6, HType7, HType8, HType9, HType10, HType11, HType12}
	public HType bgHType;
	
	public BufferedImage bg;
	
	public boolean characterPaint = false; 
	
	public boolean lastBackground;
	
	public ArrayList<Rectangle> walls = new ArrayList<>();
	
	public int playerx = 100;
	public int playery = 100;
	
	public Entrance top;
	public Entrance right;
	public Entrance bottom;
	public Entrance left;
	public Entrance room;
	
	public ArrayList<Entrance> entrances = new ArrayList<>();
	
	
	public String key;
	public int location;
	
	
	//When sending in the type of background, must be "CLICK" as a string exactly 
	public Background(Modify_Frame mf, KeyHandler kh, String f, boolean lastFrame) {
		this.mf = mf;
		this.controls = kh;
		this.lastBackground = lastFrame;
		
		this.currentProgressionType = ProgressionType.TRIGGER;
		this.secondaryProgressionType = null;
		
		setBackgroundImage(f);
		
		this.setLayout(null);
		
	}
	
	public Background(Modify_Frame mf, KeyHandler kh, boolean lastFrame, int hType, String key) {
		this.mf = mf;
		this.controls = kh;
		this.lastBackground = lastFrame;
		this.currentProgressionType = ProgressionType.TRIGGER;
		this.secondaryProgressionType = null;
		
		this.setHType(hType);
		this.setLayout(null);
		this.setKey(key);
		
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
	
	public void setHType(int i) {
		Rectangle wall1;
		Rectangle wall2;
		switch(i) {
		case 1: 
			this.bgHType = HType.HType1;
			this.setBackgroundImage("/backgrounds/hallways/HType1.png");
			wall1 = new Rectangle(0, 285, 640, 1);
			walls.add(wall1);		
			break;
		case 2: 
			this.bgHType = HType.HType2;
			this.setBackgroundImage("/backgrounds/hallways/HType2.png");
			wall1 = new Rectangle(320, 0, 3, 640);
			walls.add(wall1);
			break;
		case 3: 
			this.bgHType = HType.HType3;
			this.setBackgroundImage("/backgrounds/hallways/HType3.png");
			break;
		case 4: 
			this.bgHType = HType.HType4;
			this.setBackgroundImage("/backgrounds/hallways/HType4.png");
			break;
		case 5: 
			this.bgHType = HType.HType5;
			this.setBackgroundImage("/backgrounds/hallways/HType5.png");
			break;
		case 6: 
			this.bgHType = HType.HType6;
			this.setBackgroundImage("/backgrounds/hallways/HType6.png");
			break;
		case 7: 
			this.bgHType = HType.HType7;
			this.setBackgroundImage("/backgrounds/hallways/HType7.png");
			break;
		case 8: 
			this.bgHType = HType.HType8;
			this.setBackgroundImage("/backgrounds/hallways/HType8.png");
			break;
		case 9: 
			this.bgHType = HType.HType9;
			this.setBackgroundImage("/backgrounds/hallways/HType9.png");
			break;
		case 10: 
			this.bgHType = HType.HType10;
			this.setBackgroundImage("/backgrounds/hallways/HType10.png");
			break;
		case 11: 
			this.bgHType = HType.HType11;
			this.setBackgroundImage("/backgrounds/hallways/HType11.png");
			break;
		case 12: 
			this.bgHType = HType.HType12;
			this.setBackgroundImage("/backgrounds/hallways/HType12.png");
			break;
		}	
	}
	
	public void addEntrance(String entranceType, int xMin, int yMin, int xMax, int yMax) {
		Entrance newEntrance = new Entrance(this, entranceType, xMin, yMin, xMax, yMax);
		
		switch(entranceType) {
			case "top":
				this.top = newEntrance;
				break;
			case "right":
				this.right = newEntrance;
				break;
			case "bottom": 
				this.bottom = newEntrance;
				break;
			case "left":
				this.left = newEntrance;	
				break;
			case "room":
				this.room = newEntrance;
				break;
			}
		
		this.entrances.add(newEntrance);
		
	}
	
	public void addEntrance(String entranceType) {
		
		int xMin = 0;
		int yMin = 0;
		int xMax = 0;
		int yMax = 0;
		
		switch(entranceType) {
			case "top":
				//this.top = newEntrance; //70y, 320x
				xMin = 270;
				yMin = 0;
				xMax = 370;
				yMax = 50;
				break;
			case "right":
				//this.right = newEntrance; //550x, 300y
				xMin = 570;
				yMin = 250;
				xMax = 640;
				yMax = 350;
				break;
			case "bottom": 
				//this.bottom = newEntrance; //550y, 320x
				xMin = 270;
				yMin = 570;
				xMax = 370;
				yMax = 640;
				break;
			case "left":
				//this.left = newEntrance; //300y, 70x
				xMin = 0;
				yMin = 250;
				xMax = 50;
				yMax = 350;
				break;
			case "room":
				//this.room = newEntrance;
				break;
			}
		
		Entrance newEntrance = new Entrance(this, entranceType, xMin, yMin, xMax, yMax);
		
		this.entrances.add(newEntrance);
		
	}
	
	public void addOutsideBounds() {
		Rectangle wall1 = new Rectangle(0, 450, 640, 340);
		walls.add(wall1);
	}
	
	@Override
	public ArrayList<Entrance> getEntrances() {
	    return this.entrances;
	}
	
		
		
		
	
	
	
	public void setButtons(boolean sb, boolean main) {
		
		
		if (sb) {
			//JButton skipButton = this.buttonCreator("/buttons/buttontest.png", "RIGHT", "SOUTH", "SKIP");
		
			int w = 64;
			int h = 64;
			int x = mf.frameWidth - w - 20;
			int y = mf.frameHeight - h - 20;
			JButton skipButton = this.buttonCreator("/buttons/buttontest.png", x, y, w, h, "SKIP");
			skipButton.addActionListener(mf);
			
			}
		
		if (main) {
			//JButton startButton = this.buttonCreator("/buttons/buttontest.png", "RIGHT", "SOUTH", "START");
			//startButton.addActionListener(mf);
			int w = 64;
			int h = 64;
			int x = mf.frameWidth - w - 20;
			int y = mf.frameHeight - h - 20;
			JButton skipButton = this.buttonCreator("/buttons/buttontest.png", x, y, w, h, "START");
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
	
	@Override
	public int getPlayerX(){
		return(this.playerx);
	};
	@Override
    public void setPlayerX(int x) {
    	this.playerx = x;
    };
    @Override
    public int getPlayerY() {
    	return(this.playery);
    };
    @Override
    public void setPlayerY(int y) {
    	this.playery = y;
    };
    
    @Override
    public ArrayList<Rectangle> getWalls() {
    	return(this.walls);
    }


}


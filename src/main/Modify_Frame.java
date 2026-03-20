package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JPanel;

import backgrounds.Background;
import entity.Player;
import java.util.LinkedList;

public class Modify_Frame extends JPanel implements Runnable, ActionListener{
	private static final long serialVersionUID = 1L; //idk what this is but eclipse really wanted it 
	
	//set pixel size of the window. 
	public int charSize = 64;
	public int frameHeight = charSize*10;
	public int frameWidth = charSize*10;
	
	//This is the progression type information and implementation of JPanel
	public enum ProgressionType {CLICK, AUTO, TRIGGER, SKIP}; //Can this be some sort of component that we are able to just use? should i create a sep class?
	CardLayout bgLayout;
	//JPanel masterPanel = new JPanel();
	LinkedList<Background> bg = new LinkedList <Background>();
	
	
	// create a game timeline / thread 
	public boolean startGame = false;
	
	KeyHandler controls = new KeyHandler();
	MouseHandler mouse = new MouseHandler();
	
	Thread timeline;
	
	//we need to set a fps rate ... for now I am setting it to 90 fps
	//check curr time
	long currentTime = System.nanoTime(); // 1 billion nano seconds = 1 second (very precise)
	int FPS = 90;
	
	int indexBG = 0;
	
	//set background
	//this will change to implementing a linked list at some point?
	Background prologue1 = new Background(this, controls, "/backgrounds/prologue1.png", "AUTO", false, "SKIP", true, false, false);
	Background prologue2 = new Background(this, controls, "/backgrounds/prologue2.png", "AUTO", false, "SKIP", true, false, false);
	Background prologue3 = new Background(this, controls, "/backgrounds/prologue3.png", "AUTO", false, "SKIP", true, false, false);
	Background hallway1 = new Background(this, controls, "/backgrounds/hallway1.png", "TRIGGER", true, null, false, false, true); // last bg for now
	Background mazeBackground = new Background(this, controls, "/backgrounds/mazeBackground.png", "TRIGGER", true, null, false, false, false);
	Background mainScreen = new Background (this, controls, "/backgrounds/mainScreen.png", "CLICK", false, null, false, true, false);
	
	public JButton skipButton = new JButton("Skip");
	
	
	Timer myTimer;
	
	//make player inside of this frame
	public Player player1 = new Player(this, controls);
	
	
	
	public Modify_Frame() {
		
		this.setPreferredSize(new Dimension(frameWidth ,frameHeight));
		this.setDoubleBuffered(true);
		
		this.addKeyListener(controls);
		this.addMouseListener(mouse);
		
		this.setFocusable(true);
		this.requestFocusInWindow();	
		
		//Creates CardLayout information
		bgLayout = new CardLayout();
		this.setLayout(bgLayout);
		//masterPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));
		
		//Implements the linked list, which is built into Java 
		this.bg.add(prologue1);
		this.bg.add(prologue2);
		this.bg.add(prologue3);
		this.bg.add(mainScreen);
		this.bg.add(mazeBackground);
		this.bg.add(hallway1);		
		//Iterates through the linked list and adds them to the MasterPanel
		int j = 0;
		
		for (Background i : bg) {
			this.add(i, Integer.toString(j));
			j++;
		}
		
		
		//???? but I don't know where this needs to be added? like does it need to be added somewhere else?
		this.add(skipButton);
		repaint();
		update();
		
		
		//Configures with JPanel
		//this.setLayout(new BorderLayout());
		//this.add(masterPanel, BorderLayout.CENTER);
				
		bgLayout.show(this, "0");
		
		//Timer to trigger events -> could this be moved to be started with screens that are auto? can we make it an attribute?
		this.myTimer = new Timer(7000, this);
		this.myTimer.start();
		
		//So that the JPanel also listens to the mouse and can receive input from the mouse
		//this.addMouseListener(mouse);
		//this.setFocusable(true);
		
		
		//COMMENTED OUT -> does there need to be a mouse listener currently? or do we just need buttons? right now, there is no purpose 
		//Right now, this is a mini/anonymous class in the class, which is not ideal. this should be moved if possible
		/*this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
				Modify_Frame.this.screenProgressionLogic(e);
		        
		    }
		});*/
	
		

		}
	
	public void startGame() {
		timeline = new Thread(this);
		timeline.start();
	}

	@Override
	public void run() {
		//when you call modify_frame, it calls run automatically
		
		//creating vars to control frames per second speed
		double drawInterval = 1000000000/FPS; //1 second (1 billion nano seconds/ frames per seconds)
		double nextDrawTime = System.nanoTime()+drawInterval; //curr time plus draw interval (when to draw next movement)

		
		while(timeline != null) {
			while (!startGame){
				//doesnt work yet but we want it to set start game to true eventually and run main screen? idk
				if (mouse.click) {
					if(mouse.x <= 640 && mouse.x >= 0) // coords of buttons eventually or make a button?
						if(mouse.y <= 640 && mouse.y >=0)
							//System.out.println("X = "+mouse.x);
							//System.out.println("Y = "+mouse.y);
							startGame = true;
							break;
				}
				else if (controls.enterpressed) {
					startGame = true;
				}
			}
			
			
			
			//Draw the screen with the updated information
			repaint(); // how to call paintComponent method
			
			//masterPanel.setBackground(Color.MAGENTA);
			//bgLayout.show(masterPanel, "0");
			
			//update the information needed for the game (char position etc)
			update();
			
			//check if time passed is = to next draw time
			double remainingTime = nextDrawTime - System.nanoTime();
			
			//sleep for remaining time
			try {
				remainingTime = remainingTime/100000; //get time from nano secs to millisecs
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				//error
				e.printStackTrace();
			}
			
		}
	}
	//Is this function constantly checking the state? 
	//Change this to be able to be applied to many components in the future 
	//THIS FUNCTION SHOULD NOW WORK
	public void paintComponent(Graphics g) {
			super.paintComponent(g); // calls j panel and class (set by java to make this work)
			Graphics2D g2 = (Graphics2D)g;
			if (indexBG == 4) { 
				startGame = true;
			}
			if (bg.get(indexBG).characterPaint){
				//mazeBackground.draw(g2);
				player1.draw(g2);
			}
			g2.dispose();
		}
				
	
	//not sure if this is correct? 
	public void update() {
		mainScreen.update();
		//masterPanel.updateUI();
		//update player1 position
		player1.update();
		if (this.frameWidth - this.charSize <= player1.x && mainScreen.lastBackground == false) {
			player1.setDefaults();
			this.advanceScreen();
		}

	}
	
	//This is the screenProgression Logic, which checks to make sure that the type of screen matches the action
	/*public void screenProgressionLogic(MouseEvent actionType) {
		if (bg.get(indexBG).currentProgressionType == Background.ProgressionType.CLICK){
				this.advanceScreen();
		} 
		else if (bg.get(indexBG).secondaryProgressionType == Background.ProgressionType.SKIP){
				this.skipToMain(indexBG);
		}
		
	}*/
	
	public void screenProgressionLogic(ActionEvent actionType, Object source, String command) {
		if (source == this.myTimer) {
			if (bg.get(indexBG).currentProgressionType == Background.ProgressionType.AUTO){
				if (this.indexBG < 4) {
					this.advanceScreen();
				} 
				//AFTER updating to the next one (here, the door), the timer needs to stop 
				if (bg.get(indexBG).currentProgressionType == Background.ProgressionType.CLICK) {
					this.myTimer.stop();
				}
			} 
		}
		else if (command.equals("SKIP")) {
			if (bg.get(indexBG).secondaryProgressionType == Background.ProgressionType.SKIP){
				this.skipToMain(indexBG);
			}
		} 
		else if (command.equals("START")){
			if (bg.get(indexBG).currentProgressionType == Background.ProgressionType.CLICK){
				this.advanceScreen();
		} 
		}
	}
	
	//https://www.geeksforgeeks.org/java/java-awt-cardlayout-class/
	//Action Handler -> the "action" is the timer running down, which is a global variable 
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String command = e.getActionCommand();
		this.screenProgressionLogic(e, source, command);
		}
	
	//ONLY USE THIS TO ADVANCE SCREEN
	public void advanceScreen(){
		bgLayout.next(this);
		this.indexBG ++; 
	}
	
	public void skipToMain(int indexBG){
		int skipNum = 3 - indexBG; 
		for (int i = 0; i<skipNum; i++) {
			this.advanceScreen();
		}
	}
	

	
}
		
	
	


//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)

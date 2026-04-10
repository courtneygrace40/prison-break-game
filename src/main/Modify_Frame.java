package main;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import backgrounds.Background;
import backgrounds.Entrance;
import backgrounds.Space;
import entity.Door;
import entity.Guard;
import entity.Player;
import entity.PlayerGuardCostume;
import rooms.ChallengeRoom;
import rooms.Room;
import rooms.SliderPuzzleRoom;

public class Modify_Frame extends JPanel implements Runnable, ActionListener{
	private static final long serialVersionUID = 1L; //idk what this is but eclipse really wanted it 
	
	//location of door: 30 over, 20 up so 300-400 px, 200-300 px 
	//CODE DIFFERENT ENTRANCE TYPES TO MAKE IT EASIER 
	
	//set pixel size of the window. 
	public int charSize = 64;
	public int frameHeight = charSize*10; 
	public int frameWidth = charSize*10;
	public Player player1;
	
	//This is the progression type information and implementation of JPanel
	public enum ProgressionType {CLICK, AUTO, TRIGGER, SKIP}; //Can this be some sort of component that we are able to just use? should i create a sep class?
	CardLayout bgLayout;
	//JPanel masterPanel = new JPanel();
	public LinkedList<Background> bg = new LinkedList <Background>();
	public Background currentBackground;
	public Room currentRoom = null;
	
	// create a game timeline / thread 
	public boolean startGame = false;
	
	KeyHandler controls = new KeyHandler();
	MouseHandler mouse = new MouseHandler();
	
	Thread timeline;
	
	//we need to set a fps rate ... for now I am setting it to 90 fps
	//check curr time
	long currentTime = System.nanoTime(); // 1 billion nano seconds = 1 second (very precise)
	int FPS = 90;
	
	public int indexBG = 0;
	public int indexBGCollection = 0;
	
	//set background
	//this will change to implementing a linked list at some point?
	Background prologue1 = new Background(this, controls, "/backgrounds/prologue1.png", false);
	Background prologue2 = new Background(this, controls, "/backgrounds/prologue2.png",  false);
	Background prologue3 = new Background(this, controls, "/backgrounds/prologue3.png",  false);
	
	Background outside = new Background(this, controls, "/backgrounds/outsidePrison.png", false);
	Background hallway1 = new Background(this, controls, "/backgrounds/hallway1.png", false); 
	Background hallway2 = new Background(this, controls, "/backgrounds/hallway2.png",  false);
	Background hallway3 = new Background(this, controls, "/backgrounds/hallway3.png", false);
	
	Background mazeBackground = new Background(this, controls, "/backgrounds/mazeBackground.png", true);// last bg for now
	Background mainScreen = new Background (this, controls, "/backgrounds/mainScreen.png", false);
	
	ChallengeRoom testRoom = new ChallengeRoom(this, controls, "/backgrounds/Room1.png", true); //test
	
	public JButton skipButton = new JButton("Skip");
	
	
	Timer myTimer;
	
	//door
	public Door door1 = new Door(this);
	
	//guard
	public Guard guard1 = new Guard(this);
	
	//player in guard costume
	public PlayerGuardCostume playerGuard = new PlayerGuardCostume(this, controls);
	ArrayList<ArrayList<Space>> locations = new ArrayList<>();
	
	
	
	public Modify_Frame() {
		
		//set specific player coords if needed
		outside.setPlayerCoords(64,64);
		hallway1.setPlayerCoords(320,320);
		hallway2.setPlayerCoords(0, 320);
		hallway3.setPlayerCoords(0, 320);
		testRoom.setPlayerCoords(320, 320);
		
		outside.setCharPaint(true);
		hallway1.setCharPaint(true);
		hallway2.setCharPaint(true);
		hallway3.setCharPaint(true);
		mazeBackground.setCharPaint(true);
		testRoom.setCharPaint(true);
		
		outside.setKey("outside");
		hallway1.setKey("hallway1");
		hallway2.setKey("hallway2");
		hallway3.setKey("hallway3");
		testRoom.setKey("testRoom");

		
		prologue1.setProgressionType("AUTO", "SKIP");
		prologue2.setProgressionType("AUTO", "SKIP");
		prologue3.setProgressionType("AUTO", "SKIP");
		
		prologue1.setButtons(true, false);
		prologue2.setButtons(true, false);
		prologue3.setButtons(true, false);
		
		testRoom.setButtons(true);
		testRoom.setChallengeType("Slider Puzzle");
		
		mainScreen.setProgressionType("CLICK", null);
		mainScreen.setButtons(false, true);
		
		outside.addEntrance(0, 300, 340, 339, 429);
		this.currentBackground = outside;
		
		hallway1.addEntrance(0, 280, 0, 370,  25);
		hallway1.addEntrance(1, 550, 300, 640, 450);
		hallway2.addEntrance(2, 280, 550, 370, 640);
		
		//trial add room to entrances 
		hallway1.addEntrance(4, 280, 550, 370, 640);
		testRoom.addEntrance(5, 280, 550, 370, 640);
		
		hallway1.setLocation(1);
		hallway2.setLocation(2);
		hallway3.setLocation(3);
		
		
		ArrayList<Space> groupA = new ArrayList<>();
        groupA.add(hallway1);
        groupA.add(null);
        groupA.add(null);
        groupA.add(null);
        groupA.add(null); 
        locations.add(groupA);
        
        ArrayList<Space> groupB = new ArrayList<>();
        groupB.add(hallway2);
        groupB.add(hallway3);
        groupB.add(outside);
        groupB.add(null);
        groupB.add(testRoom);
        locations.add(groupB);
        
        ArrayList<Space> groupC = new ArrayList<>();
        groupC.add(null);
        groupC.add(null);
        groupC.add(hallway1);
        groupC.add(null);
        groupC.add(null);
        locations.add(groupC);
        
        ArrayList<Space> groupD = new ArrayList<>();
        groupD.add(null);
        groupD.add(null);
        groupD.add(null);
        groupD.add(hallway1);
        groupD.add(null);
        locations.add(groupD);
        
        
       
		
		
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
		
		this.bg.add(outside);
		this.bg.add(hallway1);	
		this.bg.add(hallway2);	
		this.bg.add(hallway3);	
		this.bg.add(mazeBackground);
		
		this.add(prologue1, "prologue1");
		this.add(prologue2, "prologue2");
		this.add(prologue3, "prologue3");
		this.add(mainScreen, "mainScreen");
		this.add(outside, "outside");
		this.add(hallway1, "hallway1");
		this.add(hallway2, "hallway2");
		this.add(hallway3, "hallway3");
		this.add(testRoom, "testRoom");
		//Iterates through the linked list and adds them to the MasterPanel
		//int j = 0;
		
		//for (Background i : bg) {
			//this.add(i, Integer.toString(j));
			//j++;
		//}
		
		//make player inside of this frame
		player1 = new Player(this, controls, bg);
		
		
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
			
			//Draw the screen with the updated information
			repaint(); // how to call paintComponent method
			
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

	public void paintComponent(Graphics g) {
			super.paintComponent(g); // calls j panel and class (set by java to make this work)
			Graphics2D g2 = (Graphics2D)g;
			if (indexBG == 4) { 
				startGame = true;
			}
			//if (bg.get(indexBG).characterPaint){
				//mazeBackground.draw(g2);
				//player1.draw(g2);
				//playerGuard.draw(g2);
				//guard1.draw(g2);
				//door1.draw(g2);
			//} //had to comment it out to paint characters the right way 
			g2.dispose();
		}
				
	
	//updated code to allow the character switch to work between screens
	public void update() {
		mainScreen.update();
		
		if (indexBG < 5) {
			player1.update(); 
			if (this.currentBackground.entrances.size() > 0) {
				for (Entrance e : currentBackground.entrances) {
				    if (player1.x >= e.xMin && player1.x <= e.xMax && 
				        player1.y >= e.yMin && player1.y <= e.yMax) {
				        
				        this.advanceList(e.entranceType);
				        break; // 
				    }
				}
			}
			
			
			if ((door1.x == player1.x && door1.y == player1.y) && bg.get(indexBG).lastBackground == false) {
				player1.setDefaults();
				this.advanceScreen();
			}	
		} else {
			playerGuard.update();
			if ((door1.x == playerGuard.x && door1.y == playerGuard.y) && bg.get(indexBG).lastBackground == false) {
				playerGuard.setDefaults();
				this.advanceScreen();
			}
		}
		door1.update();

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
	
	
	//four types of trigger events: 0, 1, 2, 3 corresponding to someone going through the top of a screen, right, bottom, or left 
	//need to program that, but we need to declare action events somehow; maybe an action handler? 
	//not sure how we wanna do this, I can research this today/tomorrow 
	public void advanceList(int index) {
		//get action event types
		System.out.println("made it here!");
		ArrayList <Space> current = this.locations.get(indexBGCollection); //this stores the current node that it is on
		System.out.println(current);
		System.out.println(current.get(index));
		
		if (index == 5) {
			//if currently in a room... 
			//return to previous hallway based on room number
			//this is unimplemented so far
		}
		else {
		if (current.get(index) instanceof Background) {
		Space nextSpace = current.get(index); //switches to this background 
		Background next = (Background) nextSpace;
		String bgName = next.getKey(); //gets the key/string that represents that specific card
		indexBGCollection = next.getMyLocation(); //gets the location integer so we know which list to access in the future
		bgLayout.show(this, bgName); //shows the correct card
		this.currentBackground = next;
		}
		else if (current.get(index) instanceof Room) {
		    Space nextSpace = current.get(index); 
		    Room next = (Room) nextSpace;
		    
		    String bgName = next.getKey(); 
		    bgLayout.show(this, bgName); 
		    this.currentRoom = next;

		    if (next.getChallengeType().equals("Slider Puzzle")) {
		        // Use invokeLater to ensure the window pops up smoothly over the JPanel
		        javax.swing.SwingUtilities.invokeLater(() -> {
		        SliderPuzzleRoom puzzle = new SliderPuzzleRoom(this, this.controls);
		            
		            // Center it on the Modify_Frame
		            puzzle.setLocationRelativeTo(this); 
		            puzzle.setVisible(true);
		            puzzle.toFront();
		        });
		    }
		}
		
	}
	}
	

	
}

//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)

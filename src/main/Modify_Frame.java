package main;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import backgrounds.Background;
import backgrounds.Entrance;
import backgrounds.Space;
import entity.Guard;
import entity.Brother;
import entity.Player;
import entity.PlayerGuardCostume;
import rooms.ChallengeRoom;
import rooms.Room;
import rooms.SliderPuzzleRoom;
import rooms.WhackAMoleRoom;
import rooms.KeyPadRoom;
import rooms.LibraryRoom;
import rooms.CombatRoom;
import rooms.DecoderRoom;
import rooms.GuessingGameRoom;

public class Modify_Frame extends JPanel implements Runnable, ActionListener{
	private static final long serialVersionUID = 1L; //idk what this is but eclipse really wanted it 
	
	//location of door: 30 over, 20 up so 300-400 px, 200-300 px 
	//CODE DIFFERENT ENTRANCE TYPES TO MAKE IT EASIER 
	
	//set pixel size of the window. 
	public int charSize = 64;
	public int frameHeight = charSize*10; 
	public int frameWidth = charSize*10;
	public Player player1;
	public int guardApps = 0;
	
	//This is the progression type information and implementation of JPanel
	public enum ProgressionType {CLICK, AUTO, TRIGGER, SKIP}; //Can this be some sort of component that we are able to just use? should i create a sep class?
	CardLayout bgLayout;
	//JPanel masterPanel = new JPanel();
	public LinkedList<Background> bg = new LinkedList <Background>();
	public Space currentBackground;
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
	public boolean inCombat = false;
	public CombatRoom combatState = null;
	public int combatCount = 0;
	public int combatDelay = 60; //slower attacks to not make guard win immediatly 
	
	
	//set background
	//this will change to implementing a linked list at some point?
	Background prologue1 = new Background(this, controls, "/backgrounds/prologue1.png", false);
	Background prologue2 = new Background(this, controls, "/backgrounds/prologue2.png",  false);
	Background prologue3 = new Background(this, controls, "/backgrounds/prologue3.png",  false);
	
	Background outside = new Background(this, controls, "/backgrounds/outsidePrison.png", false);
	/*Background hallway1 = new Background(this, controls, "/backgrounds/hallway1.png", false); 
	Background hallway2 = new Background(this, controls, "/backgrounds/hallway2.png",  false);
	Background hallway3 = new Background(this, controls, "/backgrounds/hallway3.png", false);*/
	
	Background h1 = new Background(this, controls, false, 9, "h1");
	Background h2 = new Background(this, controls, false, 1, "h2");
	Background h3 = new Background(this, controls, false, 1, "h3");
	Background h4 = new Background(this, controls, false, 4, "h4");
	Background h5 = new Background(this, controls, false, 2, "h5");
	Background h6 = new Background(this, controls, false, 7, "h6");
	Background h7 = new Background(this, controls, false, 2, "h7");
	Background h8 = new Background(this, controls, false, 11, "h8");
	Background h9 = new Background(this, controls, false, 1, "h9");
	Background h10 = new Background(this, controls, false, 1, "h10");
	Background h11 = new Background(this, controls, false, 8, "h11");
	Background h12 = new Background(this, controls, false, 6, "h12");
	Background h13 = new Background(this, controls, false, 9, "h13");
	Background h14 = new Background(this, controls, false, 1, "h14");
	Background h15 = new Background(this, controls, false, 3, "h15");
	Background h16 = new Background(this, controls, false, 7, "h16");
	Background h17 = new Background(this, controls, false, 2, "h17");
	Background h18 = new Background(this, controls, false, 12, "h18");
	Background h19 = new Background(this, controls, false, 1, "h19");
	Background h20 = new Background(this, controls, false, 4, "h20");
	Background h21 = new Background(this, controls, false, 1, "h21");
	Background h22 = new Background(this, controls, false, 7, "h22");
	Background h23 = new Background(this, controls, false, 5, "h23");
	
	
	Background[] allHallways = {h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23};
	
	
	HashMap<Space, HashMap<String, Space>> worldMap = new HashMap<>();
	HashMap<Space, Boolean> visitedRooms = new HashMap<>();
	ArrayList<Space> endingSequence = new ArrayList<>();
	
	
	Background mazeBackground = new Background(this, controls, "/backgrounds/mazeBackground.png", true);// last bg for now
	Background mainScreen = new Background (this, controls, "/backgrounds/mainScreen.png", false);
	Background winScreen = new Background (this, controls, "/backgrounds/YouWin.png", false);
	Background gameOverScreen = new Background (this, controls,"/backgrounds/YouDie.png", false);
	Background cellHole = new Background (this, controls, "/backgrounds/CellHole.png", false);
	Background cell = new Background (this, controls, "/backgrounds/Cell.png", false);
	Background death = new Background (this, controls, "/backgrounds/Death.png", false);
	Background doorCell = new Background(this, controls, "/backgrounds/DoorCell.png", false);
			
	ChallengeRoom testRoom = new ChallengeRoom(this, controls, "/backgrounds/Room1.png", true); //test
	ChallengeRoom sliderRoom = new ChallengeRoom(this, controls, "/backgrounds/Room5.png", true);
	//KeyPad room is the room right before the user enters the brother's cell
	ChallengeRoom keyPadRoom = new ChallengeRoom(this, controls, "/backgrounds/Room1.png", true);
	ChallengeRoom killBugRoom = new ChallengeRoom(this, controls, "/backgrounds/PrisonYard.png", true);
	ChallengeRoom decoderRoom = new ChallengeRoom(this, controls, "/backgrounds/Room3.png", true);
	ChallengeRoom guessingGame = new ChallengeRoom(this, controls, "/backgrounds/Room1.png", true);
	ChallengeRoom library = new ChallengeRoom(this,controls, "/backgrounds/Room4.png", true);
	
	Image doorImage = new ImageIcon("/Door.png").getImage();
	public JButton skipButton = new JButton("Skip");
	
	
	Timer myTimer;
	
	//door
	//public Door door1 = new Door(this);
	
	//guard
	public Guard guard1 = new Guard(this, controls);
	
	
	//player in guard costume
	public PlayerGuardCostume playerGuard = new PlayerGuardCostume(this, controls);
	ArrayList<ArrayList<Space>> locations = new ArrayList<>();
	
	
	
	public Modify_Frame() {
		
		// -----SETTING UP ROOMS AND HALLWAYS -----
		

		visitedRooms.put(guessingGame, false);
		visitedRooms.put(testRoom, false);
		visitedRooms.put(sliderRoom, false);
		visitedRooms.put(keyPadRoom, false);
		visitedRooms.put(killBugRoom, false);
		visitedRooms.put(decoderRoom, false);
		visitedRooms.put(library, false);
		
		outside.setPlayerCoords(64,64);
		testRoom.setPlayerCoords(320, 320);
		
		outside.setCharPaint(true);
		mazeBackground.setCharPaint(true);
		testRoom.setCharPaint(true);
		
		outside.setKey("outside");
		testRoom.setKey("testRoom");
		winScreen.setKey("winScreen");
		cellHole.setKey("cellHole");
		cell.setKey("cell");
		death.setKey("death");	
		doorCell.setKey("doorCell");
		guessingGame.setKey("guessingGame");
		sliderRoom.setKey("sliderRoom");
		keyPadRoom.setKey("keyPadRoom");
		killBugRoom.setKey("killBugRoom");
		decoderRoom.setKey("decoderRoom");
		library.setKey("library");
		
		endingSequence.add(doorCell);
		endingSequence.add(keyPadRoom);
		endingSequence.add(cell);
		endingSequence.add(cellHole);
		endingSequence.add(winScreen);
		
		outside.setGuardBool(true);
		outside.addOutsideBounds();
		
		prologue1.setProgressionType("AUTO", "SKIP");
		prologue2.setProgressionType("AUTO", "SKIP");
		prologue3.setProgressionType("AUTO", "SKIP");
		
		// ----- BUTTON SETTING -----
		
		prologue1.setButtons(true, false);
		prologue2.setButtons(true, false);
		prologue3.setButtons(true, false);
		
		testRoom.setButtons(true);
		sliderRoom.setButtons(true);
		keyPadRoom.setButtons(true);
		killBugRoom.setButtons(true);
		library.setButtons(true);
		
		// ----- SET CHAR PAINT -----
		
		testRoom.setCharPaint(false);
		sliderRoom.setCharPaint(false);
		keyPadRoom.setCharPaint(false);
		killBugRoom.setCharPaint(false);
		library.setCharPaint(false);
		
		// ----- SET CHALLENGE TYPE -----
		
		//testRoom.setChallengeType("Guessing Game");
		sliderRoom.setChallengeType("Slider Puzzle");
		keyPadRoom.setChallengeType("Key Pad");
		killBugRoom.setChallengeType("Kill Bugs");
		decoderRoom.setChallengeType("Decoder");
		guessingGame.setChallengeType("Guessing Game");
		library.setChallengeType("Library");
		
		mainScreen.setProgressionType("CLICK", null);
		mainScreen.setButtons(false, true);
		
		
		// ----- SETTING UP WORLD MAP -----
		
		outside.addEntrance("right", 300, 340, 339, 429);
		this.currentBackground = outside;

		worldMap.put(outside, new HashMap<>());
		worldMap.get(outside).put("right", h1);
		
		/* 
		 1. Put hallway into worldMap
		 2. Put hallways/destinations into hallway's hashMap
		 3. Add entrances 
		 */
		
		

		worldMap.put(h1, new HashMap<>());
		worldMap.get(h1).put("right", h2);
		worldMap.get(h1).put("top", guessingGame);
		h1.addEntrance("right");
		h1.addEntrance("left");
		h1.addEntrance("top");
		
		
		
		worldMap.put(h2, new HashMap<>());
		worldMap.get(h2).put("right", h3);
		worldMap.get(h2).put("left", h1);
		h2.addEntrance("right");
		h2.addEntrance("left");
		
		worldMap.put(h3, new HashMap<>());
		worldMap.get(h3).put("right", h4);
		worldMap.get(h3).put("left", h2);
		h3.addEntrance("right");
		h3.addEntrance("left");
		
		worldMap.put(h4, new HashMap<>());
		worldMap.get(h4).put("right", h14);
		worldMap.get(h4).put("left", h3);
		worldMap.get(h4).put("bottom", h5);
		h4.addEntrance("right");
		h4.addEntrance("left");
		h4.addEntrance("bottom");
		
		worldMap.put(h5, new HashMap<>());
		worldMap.get(h5).put("top", h4);
		worldMap.get(h5).put("bottom", h6);
		h5.addEntrance("top");
		h5.addEntrance("bottom");
		
		worldMap.put(h6, new HashMap<>());
		worldMap.get(h6).put("bottom", h7);
		worldMap.get(h6).put("top", h5);
		worldMap.get(h6).put("left", killBugRoom);
		h6.addEntrance("top");
		h6.addEntrance("bottom");
		h6.addEntrance("left");
		
		worldMap.put(h7, new HashMap<>());
		worldMap.get(h7).put("top", h6);
		worldMap.get(h7).put("bottom", h8);
		h7.addEntrance("top");
		h7.addEntrance("bottom");
		
		worldMap.put(h8, new HashMap<>());
		worldMap.get(h8).put("top", h7);
		worldMap.get(h8).put("right", h9);
		h8.addEntrance("top");
		h8.addEntrance("right");
		
		worldMap.put(h9, new HashMap<>());
		worldMap.get(h9).put("right", h10);
		worldMap.get(h9).put("left", h8);
		h9.addEntrance("right");
		h9.addEntrance("left");
		
		worldMap.put(h10, new HashMap<>());
		worldMap.get(h10).put("right", h11);
		worldMap.get(h10).put("left", h9);
		h10.addEntrance("right");
		h10.addEntrance("left");
		
		worldMap.put(h11, new HashMap<>());
		worldMap.get(h11).put("right", h13);
		worldMap.get(h11).put("left", h10);
		worldMap.get(h11).put("top", h12);
		h11.addEntrance("right");
		h11.addEntrance("left");
		h11.addEntrance("top");
		
		worldMap.put(h12, new HashMap<>());
		worldMap.get(h12).put("bottom", h11);
		h12.addEntrance("bottom");
		
		worldMap.put(h13, new HashMap<>());
		worldMap.get(h13).put("left", h11);
		worldMap.get(h13).put("right", decoderRoom);
		h13.addEntrance("left");
		h13.addEntrance("right");
		
		worldMap.put(h14, new HashMap<>());
		worldMap.get(h14).put("right", h15);
		worldMap.get(h14).put("left", h4);
		h14.addEntrance("right");
		h14.addEntrance("left");
		
		worldMap.put(h15, new HashMap<>());
		worldMap.get(h15).put("left", h14);
		worldMap.get(h15).put("top", h16);
		h15.addEntrance("left");
		h15.addEntrance("top");
		
		worldMap.put(h16, new HashMap<>());
		worldMap.get(h16).put("bottom", h15);
		worldMap.get(h16).put("top", h17);
		worldMap.get(h16).put("left", library);
		h16.addEntrance("bottom");
		h16.addEntrance("top");
		h16.addEntrance("left");
		
		worldMap.put(h17, new HashMap<>());
		worldMap.get(h17).put("bottom", h16);
		worldMap.get(h17).put("top", h18);
		h17.addEntrance("bottom");
		h17.addEntrance("top");
		
		worldMap.put(h18, new HashMap<>());
		worldMap.get(h18).put("right", h19);
		worldMap.get(h18).put("bottom", h17);
		h18.addEntrance("right");
		h18.addEntrance("bottom");
		
		worldMap.put(h19, new HashMap<>());
		worldMap.get(h19).put("right", h20);
		worldMap.get(h19).put("left", h18);
		h19.addEntrance("right");
		h19.addEntrance("left");
		
		worldMap.put(h20, new HashMap<>());
		worldMap.get(h20).put("right", h21);
		worldMap.get(h20).put("left", h19);
		worldMap.get(h20).put("bottom", sliderRoom);
		h20.addEntrance("right");
		h20.addEntrance("left");
		h20.addEntrance("bottom");
		
		worldMap.put(h21, new HashMap<>());
		worldMap.get(h21).put("right", h22);
		worldMap.get(h21).put("left", h20);
		h21.addEntrance("right");
		h21.addEntrance("left");
		
		worldMap.put(h22, new HashMap<>());
		worldMap.get(h22).put("bottom", h23);
		worldMap.get(h22).put("left", h21);
		worldMap.get(h22).put("top", doorCell);
		h22.addEntrance("bottom");
		h22.addEntrance("left");
		h22.addEntrance("top");
		
		worldMap.put(h23, new HashMap<>());
		worldMap.get(h23).put("top", winScreen);
		h23.addEntrance("top");
		worldMap.put(winScreen, new HashMap<>());
		
		worldMap.put(guessingGame, new HashMap<>());
		worldMap.get(guessingGame).put("bottom", h1);
		guessingGame.addEntrance("bottom");
		
		worldMap.put(decoderRoom, new HashMap<>());
		worldMap.get(decoderRoom).put("left", h13);
		decoderRoom.addEntrance("left");
		
		worldMap.put(sliderRoom, new HashMap<>());
		worldMap.get(sliderRoom).put("top", h20);
		sliderRoom.addEntrance("top");
		
		worldMap.put(killBugRoom, new HashMap<>());
		worldMap.get(killBugRoom).put("right", h6);
		killBugRoom.addEntrance("right");
		
		worldMap.put(library, new HashMap<>());
		worldMap.get(library).put("right", h16);
		library.addEntrance("right");
		

		// ----- SETUP FOR MODIYFY FRAME -----

		
		gameOverScreen.setKey("gameOver");
		

		
		this.setPreferredSize(new Dimension(frameWidth ,frameHeight));
		this.setDoubleBuffered(true);
		
		this.addKeyListener(controls);
		this.addMouseListener(mouse);
		
		this.setFocusable(true);
		this.requestFocusInWindow();	
		
		//Creates CardLayout information
		bgLayout = new CardLayout();
		this.setLayout(bgLayout);
		
	
		// ----- ADDS FIRST FEW HALLWAYS TO BG LINKED LIST -----
		
		this.bg.add(prologue1);
		this.bg.add(prologue2);
		this.bg.add(prologue3);
		this.bg.add(mainScreen);
		
		this.bg.add(outside);
		
		this.add(prologue1, "prologue1");
		this.add(prologue2, "prologue2");
		this.add(prologue3, "prologue3");
		this.add(mainScreen, "mainScreen");
		this.add(outside, "outside");
		this.add(testRoom, "testRoom");
		this.add(winScreen, "winScreen");
		this.add(gameOverScreen, "gameOver");
		this.add(guessingGame, "guessingGame");
		this.add(killBugRoom, "killBugRoom");
		this.add(sliderRoom, "sliderRoom");
		this.add(keyPadRoom, "keyPadRoom");
		this.add(library, "library");		
		// ----- ADD HALLWAYS TO MODIFY FRAME -----
		
		for (Background h : allHallways) {
			h.setCharPaint(true);
			this.add(h, h.getKey());
			h.setProb(0.2);
			h.setZeroProb();
			
		}
		
		h4.setProb(0.8);
		h11.setProb(0.8);
		h22.setProb(0.8);
		h19.setProb(0.7);
		h13.setProb(0.7);
		h2.setProb(0.6);
	
		//make player inside of this frame
		player1 = new Player(this, controls);
		this.player1.startGamePosition();
		
		//???? but I don't know where this needs to be added? like does it need to be added somewhere else?
		this.add(skipButton);
		repaint();
		update();
				
		bgLayout.show(this, "0");
		
		this.myTimer = new Timer(7000, this);
		this.myTimer.start();
		
		Brother brother = new Brother(this, controls);


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
		
		if (currentBackground == gameOverScreen) {
			return;
		}
		mainScreen.update();
		
		if(inCombat) {
			player1.update();
			guard1.update();
			if (combatCount > 0) {
				combatCount--;
			}
			if (isPlayerTouchingGuard() && combatCount == 0) {
				if (controls.enterpressed) {
					combatState.playerAttack();
					System.out.println("Player Attacked! Guard Health: " + combatState.guardHealth);
				}
				else {
					combatState.guardAttack();
					System.out.println("Guard Attacked! Player Health: " + combatState.playerHealth);
				}
				combatCount = 60;
			}
			
			if (combatState.isOver()) {
				if (combatState.playerWon()) {
					System.out.println("Player won! Move to next hallway!");
					inCombat = false;
				}
				else if (combatState.guardWon()) {
					System.out.println("Guard won! Game over!");
					inCombat = false;
					combatState = null;
					showGameOverScreen();
					return;
				}
			}
		}
		
		//if (indexBG < 5) {
		else { 
			player1.update(); 
			guard1.update();
			if (this.currentBackground.getEntrances().size() > 0) {
				for (Entrance e : currentBackground.getEntrances()) {
				    if (player1.x >= e.xMin && player1.x <= e.xMax && 
				        player1.y >= e.yMin && player1.y <= e.yMax) {
				        
				        this.advanceList(e.entranceType);
				        break; // 
				    }
				//}
			}
			
			
			/*if ((door1.x == player1.x && door1.y == player1.y) && bg.get(indexBG).lastBackground == false) {
				player1.setDefaults();
				this.advanceScreen();
			}	
		} else {
			playerGuard.update();
			if ((door1.x == playerGuard.x && door1.y == playerGuard.y) && bg.get(indexBG).lastBackground == false) {
				playerGuard.setDefaults();
				this.advanceScreen();*/
			}
		}
		}
		//door1.update();

	//}

	
	
	//This is the screenProgression Logic, which checks to make sure that the type of screen matches the action
	/*public void screenProgressionLogic(MouseEvent actionType) {
		if (bg.get(indexBG).currentProgressionType == Background.ProgressionType.CLICK){
				this.advanceScreen();
		} 
		else if (bg.get(indexBG).secondaryProgressionType == Background.ProgressionType.SKIP){
				this.skipToMain(indexBG);
		}
		
	}*/
	
	public boolean isPlayerTouchingGuard() {
		int playerLeft = player1.x;
		int playerRight = player1.x + charSize;
		int playerTop = player1.y;
		int playerBottom = player1.y + charSize;
		
		int guardLeft = guard1.x;
		int guardRight = guard1.x + charSize;
		int guardTop = guard1.y;
		int guardBottom = guard1.y + charSize;
		
		return playerRight > guardLeft &&
				playerLeft < guardRight &&
				playerBottom > guardTop &&
				playerTop < guardBottom;
	}
	
	public void screenProgressionLogic(ActionEvent actionType, Object source, String command) {
		if (source == this.myTimer) {
			if (bg.get(indexBG).currentProgressionType == Background.ProgressionType.AUTO){
				if (this.indexBG < 3) {
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
	
	public void advanceList(String direction) {
	    HashMap<String, Space> exits = worldMap.get(this.currentBackground);
	    if(exits.get(direction) == doorCell) {
	    	this.endingSequence();
	    }
	    
	    if (exits != null) {
	        Space nextSpace = exits.get(direction);
	        if (nextSpace != null) {
	            this.currentBackground = nextSpace;
	            if(nextSpace.shouldGuardPaint() && this.guardApps <4) {
	            	nextSpace.incGuardApps();
	            	inCombat = true;
	            	combatState = new CombatRoom();
	            	System.out.println("Combat Started");
	            }
	            bgLayout.show(this, nextSpace.getKey());
	            this.player1.enterRoom(direction);
	            repaint();
	            if (nextSpace instanceof Room) {
	            	System.out.println("Entered a room");
	                handleRoomEntry((Room) nextSpace);
	            }
	            
	            System.out.println("Moved to: " + nextSpace.getKey());
	        }
	    } 
	}

	//ash generated
	private void handleRoomEntry(Room room) {
		if(visitedRooms.get(room) == false) {
		if (room.getChallengeType().equals("Slider Puzzle") && room.getActiveChallenge()) {
			System.out.println("start puzzle ");
	    	room.setActiveChallenge(false);
	        // Use invokeLater to ensure the window pops up smoothly over the JPanel
            	javax.swing.SwingUtilities.invokeLater(() -> {
                // Assuming SliderPuzzleRoom extends JDialog
                SliderPuzzleRoom puzzle = new SliderPuzzleRoom(this, this.controls);
                visitedRooms.replace(this.sliderRoom, true);
                puzzle.setModal(true); // This stops the user from moving the player while puzzling
                puzzle.setLocationRelativeTo(this); 
                puzzle.setVisible(true);
	        });  
	    }
		else if (room.getChallengeType().equals("Key Pad")&& room.getActiveChallenge() && visitedRooms.get(this.sliderRoom) == true) {
			System.out.println("start puzzle ");
	    	room.setActiveChallenge(false);
	        // Use invokeLater to ensure the window pops up smoothly over the JPanel
            	javax.swing.SwingUtilities.invokeLater(() -> {
                // Assuming SliderPuzzleRoom extends JDialog
                KeyPadRoom puzzle = new KeyPadRoom(this, this.controls);
                visitedRooms.replace(this.keyPadRoom, true);
                puzzle.setModal(true); // This stops the user from moving the player while puzzling
                puzzle.setLocationRelativeTo(this); 
                puzzle.setVisible(true);
	        });
            	
		}
		else if (room.getChallengeType().equals("Kill Bugs")&& room.getActiveChallenge()) {
			System.out.print(room.returnBackground());
			System.out.print(this.currentBackground);
			System.out.println("start puzzle ");
	    	room.setActiveChallenge(false);
	        // Use invokeLater to ensure the window pops up smoothly over the JPanel
            	javax.swing.SwingUtilities.invokeLater(() -> {
                // Assuming SliderPuzzleRoom extends JDialog
                WhackAMoleRoom puzzle = new WhackAMoleRoom(this, this.controls);
                visitedRooms.replace(this.killBugRoom, true);
                puzzle.setModal(true); // This stops the user from moving the player while puzzling
                puzzle.setLocationRelativeTo(this); 
                puzzle.setVisible(true);
	        });
            	
		}
		else if (room.getChallengeType().equals("Decoder")&& room.getActiveChallenge()) {
			System.out.println("start puzzle ");
	    	room.setActiveChallenge(false);
	        // Use invokeLater to ensure the window pops up smoothly over the JPanel
            	javax.swing.SwingUtilities.invokeLater(() -> {
                // Assuming SliderPuzzleRoom extends JDialog
                DecoderRoom puzzle = new DecoderRoom(this, this.controls);
                visitedRooms.replace(this.decoderRoom, true);
                puzzle.setModal(true); // This stops the user from moving the player while puzzling
                puzzle.setLocationRelativeTo(this); 
                puzzle.setVisible(true);
	        });
            	
			}
		}


		else if (room.getChallengeType().equals("Guessing Game")&& room.getActiveChallenge()) {
			System.out.println("start puzzle ");
	    	room.setActiveChallenge(false);
	        // Use invokeLater to ensure the window pops up smoothly over the JPanel
            	javax.swing.SwingUtilities.invokeLater(() -> {
                // Assuming SliderPuzzleRoom extends JDialog
                GuessingGameRoom puzzle = new GuessingGameRoom(this, this.controls);
                visitedRooms.replace(this.guessingGame, true);
                puzzle.setModal(true); // This stops the user from moving the player while puzzling
                puzzle.setLocationRelativeTo(this); 
                puzzle.setVisible(true);
	        });
            	
		}
		else if (room.getChallengeType().equals("Library")&& room.getActiveChallenge()) {
			System.out.println("start puzzle ");
	    	room.setActiveChallenge(false);
	        // Use invokeLater to ensure the window pops up smoothly over the JPanel
            	javax.swing.SwingUtilities.invokeLater(() -> {
                // Assuming SliderPuzzleRoom extends JDialog
                LibraryRoom puzzle = new LibraryRoom(this, this.controls);
                visitedRooms.replace(this.library, true);
                puzzle.setModal(true); // This stops the user from moving the player while puzzling
                puzzle.setLocationRelativeTo(this); 
                puzzle.setVisible(true);
	        });
            	
		}
		}
	
	public void showGameOverScreen() {
		this.currentBackground = gameOverScreen;
		bgLayout.show(this, "gameOver");
		repaint();

	}
	
	public void endingSequence() {
		
	}
	

	

	
	
	//four types of trigger events: 0, 1, 2, 3 corresponding to someone going through the top of a screen, right, bottom, or left 
	//need to program that, but we need to declare action events somehow; maybe an action handler? 
	//not sure how we wanna do this, I can research this today/tomorrow 
	public void advanceList(int bg) {
		//get action event types
		//this.currentBackground = worldMap.get(this.currentBackground).get(bg);
		System.out.println("made it here!");
		//ArrayList <Space> current = this.locations.get(indexBGCollection); //this stores the current node that it is on
		//String[] currentRoom = this.mapLayout[indexBGCollection];
		//String nextRoom = currentRoom[index];
		//System.out.println(nextRoom);
		//System.out.println(current.toString());
		//System.out.println(current.get(index));
		
		//if (index == 5) {
			//if currently in a room... 
			//return to previous hallway based on room number
			//this is unimplemented so far
		//}
		
		//else {
			
			if (this.currentBackground instanceof Background) {
				
				//Space nextSpace = current.get(index); //switches to this background 
				//Background next = (Background) nextSpace;
				//String bgName = next.getKey(); //gets the key/string that represents that specific card
				//indexBGCollection = next.getMyLocation(); //gets the location integer so we know which list to access in the future
				bgLayout.show(this, this.currentBackground.getKey()); //shows the correct card
				//this.currentBackground = next;
			}
			
		else if (this.currentBackground instanceof Room) {
		    //Space nextSpace = current.get(index); 
		    //Room next = (Room) nextSpace;
		    
		    /*String bgName = next.getKey(); 
		    bgLayout.show(this, bgName); 
		    this.currentRoom = next;

		    if (next.getChallengeType().equals("Slider Puzzle") && next.getActiveChallenge()) {
		    	next.setActiveChallenge(false);
		        // Use invokeLater to ensure the window pops up smoothly over the JPanel
		        javax.swing.SwingUtilities.invokeLater(() -> {
		        SliderPuzzleRoom puzzle = new SliderPuzzleRoom(this, this.controls);
		            
		            // Center it on the Modify_Frame
		            puzzle.setLocationRelativeTo(this); 
		            puzzle.setVisible(true);
		            puzzle.toFront();
		        });*/
		    }
		}
	

		
	//}
	}

	

	
//}

//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)

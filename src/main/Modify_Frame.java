package main;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import backgrounds.Background;
import entity.Player;

public class Modify_Frame extends JPanel implements Runnable{
	// final variables are unchangeable : we are using them here to set pixel size of the window. 
	public int charSize = 64;
	final int frameHeight = charSize*8;
	final int frameWidth = charSize*8;

	
	
	// create a game timeline / thread 
	
	KeyHandler controls = new KeyHandler();
	Thread timeline;
	
	//we need to set a fps rate ... for now I am setting it to 60 fps
	//check curr time
	long currentTime = System.nanoTime(); // 1 billion nano seconds = 1 second (very precise)
	int FPS = 60;
	
	//set background
	Background background1 = new Background(this, controls, "/backgrounds/mazeBackground.png");
	
	//make player inside of this frame
	Player player1 = new Player(this, controls);
	
	public Modify_Frame() {
		
		this.setPreferredSize(new Dimension(frameWidth ,frameHeight));
		//this.setBackground(Color.pink);
		this.setDoubleBuffered(true);
		this.addKeyListener(controls);
		this.setFocusable(true);
	}
	
	public void startGame() {
		timeline = new Thread(this);
		timeline.start();
	}

	@Override
	public void run() {
		//when call modify_frame, call run automatically
		
		//creating vars to control frames per second speed
		double drawInterval = 1000000000/FPS; //1 second (1 billion nano seconds/ frames per seconds)
		double nextDrawTime = System.nanoTime()+drawInterval; //curr time plus draw interval (when to draw next movement)
		
		
		
		while(timeline != null) {

			//update the information needed for the game (char position etc)
			update();
			//Draw the screen with the updated information
			repaint(); // how to call paintComponent method
			
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
			background1.draw(g2);
			player1.draw(g2);
			
			g2.dispose();
		}
				
	public void update() {
		//update player1 position
		player1.update();

	}
	
	public void updateBackground() {
		
	}
}

//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)

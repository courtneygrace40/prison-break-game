package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Modify_Frame extends JPanel implements Runnable{
	// final variables are unchangeable : we are using them here to set pixel size of the window. 
	final int frameHeight = 640;
	final int frameWidth = 640;
	final int charSize = 64;
	
	// create a game timeline / thread 
	Thread timeline;
	
	public Modify_Frame() {
		
		this.setPreferredSize(new Dimension(frameWidth ,frameHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGame() {
		timeline = new Thread(this);
		timeline.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//when call modify_frame, call run
		while(timeline != null) {
			//update the information needed for the game (char position etc)
			update();
			//Draw the screen with the updated information
			repaint(); // how to call paintComponent method
			
		}
	public void paintComponent(Graphics g) {
			super.paintComponent(g); // calls j panel and class (set by java to make this work)
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(Color.white);
			g2.fillRect(100, 100, charSize, charSize);
			g2.dispose();
		}
				
	public void update() {
		//empty for now
		
	}
	

	}
}

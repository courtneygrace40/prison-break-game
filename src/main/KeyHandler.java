package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean uppressed, downpressed, leftpressed, rightpressed, enterpressed;
	public boolean playerUp, playerDown, playerLeft, playerRight;
	public boolean guardUp, guardDown, guardLeft, guardRight;
	@Override
	public void keyTyped(KeyEvent e) {
		
		//has to be implemented for when a key is typed - not sure when we use but not needed yet
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//get what the key value is 
		int keyCode = e.getKeyCode();
		//check if the key code is in ASDW or up down left right
		if (keyCode == KeyEvent.VK_LEFT) {
			playerLeft= true;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			playerDown= true;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			playerRight= true;
		}
		if (keyCode == KeyEvent.VK_UP) {
			playerUp=true;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			enterpressed=true;
		}
		
		if (keyCode == KeyEvent.VK_W) {
			guardUp = true;
		}
		if (keyCode == KeyEvent.VK_S) {
			guardDown = true;
		}
		if (keyCode == KeyEvent.VK_A) {
			guardLeft = true;
		}
		if (keyCode == KeyEvent.VK_D) {
			guardRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//get what the key value is 
		int keyCode = e.getKeyCode();
		//check if the key code is in ASDW or up down left right
		if (keyCode == KeyEvent.VK_LEFT) {
			playerLeft= false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			playerDown= false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			playerRight= false;
		}
		if (keyCode == KeyEvent.VK_UP) {
			playerUp=false;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			enterpressed=false;
		}
		if (keyCode == KeyEvent.VK_W) {
			guardUp = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			guardDown = false;
		}
		if (keyCode == KeyEvent.VK_A) {
			guardLeft = false;
		}
		if (keyCode == KeyEvent.VK_D) {
			guardRight = false;
		}
		
	}
	
	
	
	
	
	
	
	
}


//src: https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyListener.html
//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)
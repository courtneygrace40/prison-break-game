package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean uppressed, downpressed, leftpressed, rightpressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		//has to be implemented for when a key is typed - not sure when we use
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//get what the key value is 
		int keyCode = e.getKeyCode();
		//check if the key code is in ASDW or up down left right
		if (keyCode == KeyEvent.VK_A|| keyCode == KeyEvent.VK_LEFT) {
			leftpressed= true;
		}
		if (keyCode == KeyEvent.VK_S|| keyCode == KeyEvent.VK_DOWN) {
			downpressed= true;
		}
		if (keyCode == KeyEvent.VK_D|| keyCode == KeyEvent.VK_RIGHT) {
			rightpressed= true;
		}
		if (keyCode == KeyEvent.VK_W|| keyCode == KeyEvent.VK_UP) {
			uppressed=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//get what the key value is 
		int keyCode = e.getKeyCode();
		//check if the key code is in ASDW or up down left right
		if (keyCode == KeyEvent.VK_A|| keyCode == KeyEvent.VK_LEFT) {
			leftpressed= false;
		}
		if (keyCode == KeyEvent.VK_S|| keyCode == KeyEvent.VK_DOWN) {
			downpressed= false;
		}
		if (keyCode == KeyEvent.VK_D|| keyCode == KeyEvent.VK_RIGHT) {
			rightpressed= false;
		}
		if (keyCode == KeyEvent.VK_W|| keyCode == KeyEvent.VK_UP) {
			uppressed=false;
		}
		
	}
	
	
	
	
	
	
	
	
}


//src: https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyListener.html
//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)
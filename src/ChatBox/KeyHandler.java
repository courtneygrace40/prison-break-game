package ChatBox;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean enterpressed;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		//has to be implemented for when a key is typed
        
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//get what the key value is 
		int keyCode = e.getKeyCode();
		//check if the key code is enter
		
		if (keyCode == KeyEvent.VK_ENTER) {
			enterpressed=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//get what the key value is 
		int keyCode = e.getKeyCode();
		//check if the key code is enter
		if (keyCode == KeyEvent.VK_ENTER) {
			enterpressed=false;
		}
		
	}
	
	
	
	
	
	
	
}


//src: https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyListener.html
//https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2 (Game Loop and Key Input - How to Make a 2D Game in Java #2)
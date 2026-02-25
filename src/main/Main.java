package main;
import javax.swing.JFrame;
import java.awt.*; 

import ChatBox.ChatGui;
// import package title screen; 

public class Main {
	public static void main(String [] args) {
		
		//Create the frame.
		JFrame frame = new JFrame("Game Play Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Prison Break... The Game");
		
		ChatGui chatGui = new ChatGui();
		frame.add(chatGui, BorderLayout.EAST);
		
		Modify_Frame frame_mod = new Modify_Frame();
		frame.add(frame_mod);
		
		frame.pack(); //sets window size to preferred window size indicated in modify_frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		frame_mod.startGame();
		
	
		

		
	

	}
}


//Sources:
//https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
//https://www.youtube.com/watch?v=om59cwR7psI (How to Make a 2D Game in Java #1 - The Mechanism of 2D Games -- RyiSnow)



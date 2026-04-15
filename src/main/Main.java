package main;    
import javax.swing.*; 
// import package title screen; 

public class Main {
	public static void main(String [] args) {
		
		//Create the frame.
		JFrame frame = new JFrame("Game Play Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Prison Break... The Game");
		
		Modify_Frame frame_mod_L = new Modify_Frame();
		//masterPanel.add(frame_mod);
		//frame.add(frame_mod);
		
        frame.add(frame_mod_L);
		frame.pack(); //sets window size to preferred window size indicated in modify_frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		frame_mod_L.startGame();

		
	

	}
}
 

//Sources:
//https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
//https://www.youtube.com/watch?v=om59cwR7psI (How to Make a 2D Game in Java #1 - The Mechanism of 2D Games -- RyiSnow)



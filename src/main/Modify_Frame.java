package main;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Modify_Frame extends JPanel{
	// final variables are unchangeable : we are using them here to set pixel size of the window. 
	final int frameHeight = 640;
	final int frameWidth = 640;
	
	
	
	public Modify_Frame() {
		
		this.setPreferredSize(new Dimension(frameWidth ,frameHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
}

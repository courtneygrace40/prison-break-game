package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;

public class Door extends Entity{
	Modify_Frame mf;
	KeyHandler controls;
	
	public Door(Modify_Frame mf) {
		this.mf = mf;
		
		setDefaults();
		getDoorImage();
		
	}
	private void setDefaults() {
		x = mf.frameWidth - mf.charSize;
		y = 100;
		speed = 0;
		direction = "front";
		
	}
	public void getDoorImage() {
		try{
			front = ImageIO.read(getClass().getResourceAsStream("/backgrounds/door.png"));
			back = ImageIO.read(getClass().getResourceAsStream("/backgrounds/door.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		if (direction.equals("front")) {
			image = front;
		}
		else if (direction.equals("back")) {
			image = back;
		}
			
		g2.drawImage(image, x, y, mf.charSize, mf.charSize, null);
		
	}
}

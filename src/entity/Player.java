package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;

public class Player extends Entity{
	Modify_Frame mf;
	KeyHandler controls;
	
	public Player(Modify_Frame mf, KeyHandler kh) {
		
		this.mf = mf;
		this.controls = kh;
		this.direction = "back";
		
		setDefaults();
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		try{
			front = ImageIO.read(getClass().getResourceAsStream("/player/player1.png"));
			back = ImageIO.read(getClass().getResourceAsStream("/player/player1back.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaults() {
		x = 100;
		y = 100;
		speed = 2;
		direction = "back";
		
	}
	
	public void update() {
		//update player position
		if(controls.uppressed){
			direction = "back";
			y -= speed; //movement depending on player speed	
				}
		else if(controls.downpressed) {
			direction = "back";
			y += speed;
			}
		else if (controls.rightpressed) {
			direction = "front";
			x += speed;
				}
		else if (controls.leftpressed) {
			direction = "front";
			x -= speed;
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

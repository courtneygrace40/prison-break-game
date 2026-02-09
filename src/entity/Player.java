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
		//this.direction = "forward"; will use when we have a front and back but not of use yet
		
		setDefaults();
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		try{
			front = ImageIO.read(getClass().getResourceAsStream("/player/player1.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaults() {
		x = 100;
		y = 100;
		speed = 4;
		
	}
	public void update() {
		//update player position
		if(controls.uppressed){
			y -= speed; //movement depending on player speed	
				}
		else if(controls.downpressed) {
			y += speed;
			}
		else if (controls.rightpressed) {
			x += speed;
				}
		else if (controls.leftpressed) {
			x -= speed;
				}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = front;
		//add if else for front and back when front and back exist
		
		g2.drawImage(image, x, y, mf.charSize, mf.charSize, null);
		
	}
}

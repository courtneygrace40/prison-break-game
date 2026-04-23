package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
			front = ImageIO.read(getClass().getResourceAsStream("/player/Main_Front.png"));
			back = ImageIO.read(getClass().getResourceAsStream("/player/Main_Back.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefaults() {
		x = mf.currentBackground.getPlayerX();
		y = mf.currentBackground.getPlayerY();
		speed = 2;
		direction = "back";
		
	}
	
	public void enterRoom(String entrance) {
		switch(entrance) {
		case "top": //550y, 320x
			x = 290;
			y = 550;
			break;
		case "right": //300y, 70x
			x = 70;
			y = 270;
			break;
		case "left": //550x, 300y
			x = 550;
			y = 270;
			break;
		case "bottom":
			//70y, 320x
			x = 290;
			y = 70;
			break;
		}
		
	}
	
	public void startGamePosition() {
		x = 290;
		y = 550;
	}
	
	public void update() {
		
		int oldX = x;
	    int oldY = y;
		
		//update player position
		if(controls.playerUp){
			direction = "back";
			y -= speed; //movement depending on player speed	
				}
		else if(controls.playerDown) {
			direction = "back";
			y += speed;
			}
		else if (controls.playerRight) {
			direction = "front";
			x += speed;
				}
		else if (controls.playerLeft) {
			direction = "front";
			x -= speed;
				}
		x = Math.max(0, Math.min(x, mf.frameWidth - mf.charSize)); //helps make sure that character stays in bound of the screen by measuring the coordinates 
	    y = Math.max(0, Math.min(y, mf.frameHeight - mf.charSize));
	    
	    Rectangle playerHitbox = new Rectangle(x, y, mf.charSize, mf.charSize);

	    // If the player "intersects" the wall, undo the move
	    if(mf.currentBackground.getWalkable()!=null) {
	        if ( mf.currentBackground.getWalkable().contains(playerHitbox) == false) {
	            x = oldX;
	            y = oldY;   
	        }
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

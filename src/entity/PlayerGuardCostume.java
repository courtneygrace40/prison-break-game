package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;


public class PlayerGuardCostume extends Entity{
	Modify_Frame mf;
	KeyHandler controls;
	BufferedImage playerGuardImage;
	
	public PlayerGuardCostume(Modify_Frame mf, KeyHandler kh) {
		this.mf = mf;
		this.controls = kh;
		setDefaults();
		getPlayerGuardImage();
		
	}
	
	private void setDefaults() {
        x = 150;
        y = 150;
		speed = 2; //no movement yet
		
	}
	
	public void update() {
		//update player position
		if(controls.uppressed){
			//direction = "back";
			y -= speed; //movement depending on player speed	
				}
		else if(controls.downpressed) {
			//direction = "back";
			y += speed;
			}
		else if (controls.rightpressed) {
			//direction = "front";
			x += speed;
				}
		else if (controls.leftpressed) {
			//direction = "front";
			x -= speed;
				}
		x = Math.max(0, Math.min(x, mf.frameWidth - mf.charSize)); //helps make sure that character stays in bound of the screen by measuring the coordinates 
	    y = Math.max(0, Math.min(y, mf.frameHeight - mf.charSize));
		
	}
	
	public void getPlayerGuardImage() {
		try {
			playerGuardImage = ImageIO.read(getClass().getResourceAsStream("/player/PlayerGuardCostume.png"));
		}catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    public void draw(Graphics2D g2) {
        if (playerGuardImage != null) {
            g2.drawImage(playerGuardImage, x, y, mf.charSize, mf.charSize, null);
        }
    }



}
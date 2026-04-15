package entity;

import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;

public class Guard extends Entity {

    Modify_Frame mf;
    BufferedImage guardImage;
    KeyHandler controls;


    public Guard(Modify_Frame mf, KeyHandler controls) {
        this.mf = mf;
        this.controls = controls;

        setDefaults();
        getGuardImage();
    }

    public void setDefaults() {
        x = 200;
        y = 200;
        speed = 2; // not moving
    }

    public void getGuardImage() {
        try {
            guardImage = ImageIO.read(getClass().getResourceAsStream("/player/TheGuard_Front.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public void update() {
		//update player position
		if(controls.guardUp){
			direction = "back";
			y -= speed; //movement depending on player speed	
				}
		else if(controls.guardDown) {
			direction = "back";
			y += speed;
			}
		else if (controls.guardRight) {
			direction = "front";
			x += speed;
				}
		else if (controls.guardLeft) {
			direction = "front";
			x -= speed;
				
		}
		x = Math.max(0, Math.min(x, mf.frameWidth - mf.charSize)); //helps make sure that character stays in bound of the screen by measuring the coordinates 
	    y = Math.max(0, Math.min(y, mf.frameHeight - mf.charSize));
		
	}
	
// commenting just to see if this will allow me to push code, because it keeps saying that there are no changes to stage


    public void draw(Graphics2D g2) {
        if (guardImage != null) {
            g2.drawImage(guardImage, x, y, mf.charSize, mf.charSize, null);
        }
    }
}
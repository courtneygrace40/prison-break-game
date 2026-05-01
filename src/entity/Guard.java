package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.Modify_Frame;

public class Guard extends Entity {

    Modify_Frame mf;
    BufferedImage guardImage;
    KeyHandler controls;
    boolean movingRight = true;
    int leftBoundry;
    int rightBoundry;
    


    public Guard(Modify_Frame mf, KeyHandler controls) {
        this.mf = mf;
        this.controls = controls;

        setDefaults();
        getGuardImage();
        
    }

    public void setDefaults() {
        x = 200;
        y = 270;
        speed = 1; 
        leftBoundry = 100;
        rightBoundry = 500;
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
		
		if (movingRight) {
			direction = "front";
			x += speed;
			
			if (x >= rightBoundry) 
				movingRight = false;
		} else {
			direction = "front";
			x -= speed;
			
			if (x <= leftBoundry) {
				movingRight = true;
			}
		}
		
		int oldX = x;
	    int oldY = y;

		x = Math.max(0, Math.min(x, mf.frameWidth - mf.charSize)); //helps make sure that character stays in bound of the screen by measuring the coordinates 
	    y = Math.max(0, Math.min(y, mf.frameHeight - mf.charSize));
	    
	    Rectangle playerHitbox = new Rectangle(x, y, mf.charSize, mf.charSize);

	 
	    if(mf.currentBackground.getWalkable()!=null) {
	        if ( mf.currentBackground.getWalkable().contains(playerHitbox) == false) {
	            x = oldX;
	            y = oldY;   
	        }
	    }
		
	}
	
// commenting just to see if this will allow me to push code, because it keeps saying that there are no changes to stage


    public void draw(Graphics2D g2) {
        if (guardImage != null) {
            g2.drawImage(guardImage, x, y, mf.charSize, mf.charSize, null);
        }
    }
}
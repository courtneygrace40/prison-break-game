package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Modify_Frame;

public class Guard extends Entity {

    Modify_Frame mf;
    BufferedImage guardImage;

    public Guard(Modify_Frame mf) {
        this.mf = mf;

        setDefaults();
        getGuardImage();
    }

    public void setDefaults() {
        x = 200;
        y = 200;
        speed = 0; // not moving
    }

    public void getGuardImage() {
        try {
            guardImage = ImageIO.read(getClass().getResourceAsStream("/player/TheGuard_Front.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// commenting just to see if this will allow me to push code, because it keeps saying that there are no changes to stage


    public void draw(Graphics2D g2) {
        if (guardImage != null) {
            g2.drawImage(guardImage, x, y, mf.charSize, mf.charSize, null);
        }
    }
}
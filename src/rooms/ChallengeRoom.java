package rooms;

import main.KeyHandler;
import main.Modify_Frame;

public class ChallengeRoom extends Room{
	
	public String challengeType;
	
	public ChallengeRoom(Modify_Frame mf, KeyHandler kh, int x, int y, String filename) {
		
		this.setImage(filename); //pretty sure this will work but not completely
		this.playerx = x;
		this.playery = y;
		this.mf = mf;
		this.kh = kh;
		
	}
	
}

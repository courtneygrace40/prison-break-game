package rooms;


import main.KeyHandler;
import main.Modify_Frame;

public class ChallengeRoom extends Room{
	
	public String challengeType;
	public RoomChallenge challenge;
	public boolean activeChallenge = true;
	
	public ChallengeRoom(Modify_Frame mf, KeyHandler kh, String f, boolean lastFrame) {
		super(mf, kh, f, lastFrame);
		this.mf = mf;
		this.controls = kh;
		this.setBackgroundImage(f); //pretty sure this will work but not completely
		
	}
	
	public void setChallengeType(String type) {
		this.challengeType = type;
	}
	
	@Override
	public String getChallengeType() {
		return this.challengeType;
	}
	
	public void setChallenge(RoomChallenge c) {
		this.challenge = c;
	}
	
	public RoomChallenge getChallenge() {
		return this.challenge;
	}
	
	@Override
	public void setActiveChallenge(boolean b) {
		this.activeChallenge = b;
	}
	
	@Override
	public boolean getActiveChallenge() {
		return this.activeChallenge;
	}
	
	
	
	
}

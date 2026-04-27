package rooms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import main.KeyHandler;
import main.Modify_Frame;

public class GuessingGameRoom extends JDialog implements RoomChallenge, ActionListener{

	private static final long serialVersionUID = 1L;
	KeyHandler kh;
	Modify_Frame mf;
	
	public GuessingGameRoom(Modify_Frame mf, KeyHandler kh) {
		this.mf = mf;
		this.kh = kh;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateLogic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintObjects() {
		// TODO Auto-generated method stub
		
	}

}

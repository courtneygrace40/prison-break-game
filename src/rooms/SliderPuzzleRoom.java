package rooms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.KeyHandler;
import main.Modify_Frame;

public class SliderPuzzleRoom extends JDialog implements RoomChallenge, ActionListener {
    
    Modify_Frame mf;
    KeyHandler kh;
    JButton[] buttons = new JButton[9]; // Using an array makes logic much easier
    JButton shuffle;
    int counter = 0;
    JLabel counterLabel;
    
    public SliderPuzzleRoom(Modify_Frame mf, KeyHandler kh) {
        
        this.mf = mf;
        this.kh = kh;
        
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(mf); // Centers the puzzle over the game
        
        // Change close operation so it doesn't kill the whole game
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeButtons();
        addButtons();
        setbounds();
    }
	
	private void setbounds() {
		// TODO Auto-generated method stub
		buttons[0].setBounds(90,60,50,40);
		buttons[1].setBounds(160,60,50,40);
		buttons[2].setBounds(230,60,50,40);
		buttons[3].setBounds(90,115,50,40);
		buttons[4].setBounds(160,115,50,40);
		buttons[5].setBounds(230,115,50,40);
		buttons[6].setBounds(90,170,50,40);
		buttons[7].setBounds(160,170,50,40);
		buttons[8].setBounds(230,170,50,40);
		shuffle.setBounds(135,245,100,40);
		counterLabel.setBounds(145,15,180,40);
	}

	private void addButtons() {
		// TODO Auto-generated method stub
		add(buttons[0]);add(buttons[1]);add(buttons[2]);add(buttons[3]);add(buttons[4]);add(buttons[5]);add(buttons[6]);add(buttons[7]);add(buttons[8]); add(shuffle);
		Container contentPane = this.getContentPane();
		contentPane.add(counterLabel);
	}

	public void initializeButtons() {
		// TODO Auto-generated method stub
		buttons[0] = new JButton("1");
		buttons[1] = new JButton(" ");
		buttons[2] = new JButton("3");
		buttons[3]= new JButton("4");
		buttons[4]= new JButton("5");
		buttons[5]= new JButton("6");
		buttons[6]= new JButton("7");
		buttons[7]= new JButton("8");
		buttons[8]= new JButton("2");
		shuffle = new JButton("Shuffle!");
		counterLabel = new JLabel("Clicks: 0");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

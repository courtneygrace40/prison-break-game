package rooms;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import main.KeyHandler;
import main.Modify_Frame;

public class keyPadRoom extends JDialog implements RoomChallenge, ActionListener {
    //CODE TO WIN IS : 617413
    Modify_Frame mf;
    KeyHandler kh;
    JButton  b1, b2, b3, b4, b5, b6, b7, b8, b9, enter;
    JLabel winnerLabel;
	ArrayList <JButton> pressed= new ArrayList <JButton> ();
	ArrayList <JButton> winCode= new ArrayList <JButton> ();
    
    public keyPadRoom(Modify_Frame mf, KeyHandler kh) {
        
        this.mf = mf;
        this.kh = kh;
        
    	winCode.add(b6);
    	winCode.add(b1);
    	winCode.add(b7);
    	winCode.add(b4);
    	winCode.add(b1);
    	winCode.add(b3);
        
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(mf); // Centers the puzzle over the game
        
        // Change close operation so it doesn't kill the whole game
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeButtons();
        addButtons();
        setbounds();
        addActionListener();
  
    }
	

	private void addActionListener() {
		// TODO Auto-generated method stub
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		enter.addActionListener(this);
		
	}

	private void setbounds() {
		// TODO Auto-generated method stub
		b1.setBounds(90,60,50,40);
		b2.setBounds(160,60,50,40);
		b3.setBounds(230,60,50,40);
		b4.setBounds(90,115,50,40);
		b5.setBounds(160,115,50,40);
		b6.setBounds(230,115,50,40);
		b7.setBounds(90,170,50,40);
		b8.setBounds(160,170,50,40);
		b9.setBounds(230,170,50,40);
		enter.setBounds(160, 235, 50, 40);
		winnerLabel.setBounds(50, 280, 300, 40);
	}

	private void addButtons() {
		// TODO Auto-generated method stub
		add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);add(enter);
		Container contentPane = this.getContentPane();
		contentPane.add(winnerLabel);
	}

	public void initializeButtons() {
		// TODO Auto-generated method stub
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4= new JButton("4");
		b5= new JButton("5");
		b6= new JButton("6");
		b7= new JButton("7");
		b8= new JButton("8");
		b9= new JButton("9");
		enter = new JButton("Enter");
		winnerLabel = new JLabel("Enter the password...");
	}
	
	@Override
	public boolean hasFinished() {
		Boolean correct = false;
		for (int i = 0; i<6;i++) {
			if (pressed.get(i)==winCode.get(i)) {
				correct = true;
			}
			else {
				correct = false;
				break;
			}
		}
		return correct;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object b = e.getSource(); //what button has been clicked
		if (b==b1) {
			pressed.add(b1);
		}
		else if (b==b2) {
			 pressed.add(b2);
		}
		else if (b==b3) {
			pressed.add(b3);
		}
		else if (b==b4) {
			pressed.add(b4);
		}
		else if (b==b5) {
			pressed.add(b5);
		}
		else if (b==b6) {
			pressed.add(b6);
		}
		else if (b==b7) {
			pressed.add(b7);
		}
		else if (b==b8) {
			pressed.add(b8);
		}
		else if (b==b9) {
			pressed.add(b9);
		}
		
		else if (b == enter) {
			if (hasFinished()) {
	        winnerLabel.setText("Click! You hear a lock unlock... nice!");
	        // Disable all buttons
	        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
	        for (JButton x : buttons) x.setEnabled(false);
			}
			else {
				winnerLabel.setText("Uh oh... alarms are sounding...");
			}
	    }
		
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

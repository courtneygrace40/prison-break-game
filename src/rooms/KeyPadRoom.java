package rooms;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import main.KeyHandler;
import main.Modify_Frame;

public class KeyPadRoom extends JDialog implements RoomChallenge, ActionListener {
    private static final long serialVersionUID = 1L;
	//CODE TO WIN IS : 617413
    Modify_Frame mf;
    KeyHandler kh;
    JButton  b1, b2, b3, b4, b5, b6, b7, b8, b9, enter, fake;
    JLabel winnerLabel;
	ArrayList <JButton> pressed= new ArrayList <JButton> ();
	ArrayList <JButton> winCode= new ArrayList <JButton> ();
	ArrayList <JButton> buttons= new ArrayList <JButton> ();
	
    public KeyPadRoom(Modify_Frame mf, KeyHandler kh) {
        
        this.mf = mf;
        this.kh = kh;
        
        
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(mf); // Centers the puzzle over the game
        setOpacity(1);
        setBackground(Color.gray);
        
        // Change close operation so it doesn't kill the whole game
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeButtons();
        addButtons();
        setbounds();
        addActionListener();
        
    	winCode.add(b6);
    	winCode.add(b1);
    	winCode.add(b7);
    	winCode.add(b4);
    	winCode.add(b1);
    	winCode.add(b3);
    	
    	buttons.add(b1);buttons.add(b2);buttons.add(b3);buttons.add(b4);buttons.add(b5);buttons.add(b6);buttons.add(b7);buttons.add(b8);buttons.add(b9);buttons.add(enter);
    	
    	
    	for (JButton button: buttons){
    		button.setOpaque(true);
    		button.setBorderPainted(false);
    		button.setBackground(Color.decode("#384266"));
    		button.setForeground(Color.white);

    	}
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

		b1.setBounds(40,10,55,40);
		b2.setBounds(110,10,55,40);
		b3.setBounds(190,10,55,40);
		b4.setBounds(40,65,55,40);
		b5.setBounds(110,65,55,40);
		b6.setBounds(190,65,55,40);
		b7.setBounds(40,120,55,40);
		b8.setBounds(110,120,55,40);
		b9.setBounds(190,120,55,40);
		enter.setBounds(40, 175, 195, 40);
		  
	}

	private void addButtons() {
		// TODO Auto-generated method stub
		add(enter);add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);add(fake);
	}

	public void initializeButtons() {
		// TODO Auto-generated method stub
		enter = new JButton("Enter");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4= new JButton("4");
		b5= new JButton("5");
		b6= new JButton("6");
		b7= new JButton("7");
		b8= new JButton("8");
		b9= new JButton("9");
		fake = new JButton(" ");
		
		
	}
	
	@Override
	public boolean hasFinished() {
		Boolean correct = false;
		System.out.println(winCode);
		System.out.println(pressed);
		if (pressed.equals(winCode)) {
			correct = true;
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
			System.out.println("Finished puzzle!");
			
			JOptionPane.showMessageDialog(this,
				    "Click! You hear a lock unlock... nice!",
				    "The code is correct.",
				    JOptionPane.PLAIN_MESSAGE);
	        // Disable all buttons
	        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
	        for (JButton x : buttons) x.setEnabled(false);
	        setVisible(false);
			}
			else {
				setVisible(false);
				JOptionPane.showMessageDialog(this,
					    "Uhoh... you hear alarms begin to sound...",
					    "The code is NOT CORRECT.",
					    JOptionPane.PLAIN_MESSAGE);
			}
	    }
		
	}


}

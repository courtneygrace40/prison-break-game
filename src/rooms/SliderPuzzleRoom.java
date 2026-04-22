package rooms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.KeyHandler;
import main.Modify_Frame;

public class SliderPuzzleRoom extends JDialog implements RoomChallenge, ActionListener {
    
    Modify_Frame mf;
    KeyHandler kh;
    JButton  b1, b2, b3, b4, b5, b6, b7, b8, b9;
    JLabel winnerLabel;
    
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
        addActionListener();
        shuffle();
        //initializeGameLoop();
    }
	
	private void initializeGameLoop() {
		/*
		 * while (hasFinished()== false) { //do nothing until } if (hasFinished()==true)
		 * { winnerLabel.setText("Good Job! Remember this number... 6");
		 * b1.setEnabled(false); b2.setEnabled(false); b3.setEnabled(false);
		 * b4.setEnabled(false); b5.setEnabled(false); b6.setEnabled(false);
		 * b7.setEnabled(false); b8.setEnabled(false); b9.setEnabled(false); }
		 */
			
		
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
		winnerLabel.setBounds(145,15,180,40);
	}

	private void addButtons() {
		// TODO Auto-generated method stub
		add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);
		Container contentPane = this.getContentPane();
		contentPane.add(winnerLabel);
	}

	public void initializeButtons() {
		// TODO Auto-generated method stub
		b1 = new JButton("1");
		b2 = new JButton(" ");
		b3 = new JButton("3");
		b4= new JButton("4");
		b5= new JButton("5");
		b6= new JButton("6");
		b7= new JButton("7");
		b8= new JButton("8");
		b9= new JButton("2");
		winnerLabel = new JLabel("Order the numbers from 1-8");
	}

	public void shuffle() {
		 String s = b4.getText();
		  	b4.setText(b9.getText());
		    b9.setText(s);
		    s = b1.getText();
		    b1.setText(b5.getText());
		    b5.setText(s);
		    s = b2.getText();
		    b2.setText(b7.getText());
		    b7.setText(s);
	}
	
	@Override
	public boolean hasFinished() {
		if (b1.getText().equals("1")&&b2.getText().equals("2")&&b3.getText().equals("3")&&b4.getText().equals("4")&&b5.getText().equals("5")&&b6.getText().equals("6")&&b7.getText().equals("7")&&b8.getText().equals("8")) {
			return true;
		}
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
		Object b = e.getSource();
		String s;
		String button = null;
		if (b==b1) {
			 button = "b1";
			 s = b1.getText();
		}
		else if (b==b2) {
			 button = "b2";
			 s = b2.getText();
		}
		else if (b==b3) {
			 button = "b3";
			 s = b3.getText();
		}
		else if (b==b4) {
			 button = "b4";
			 s = b4.getText();
		}
		else if (b==b5) {
			 button = "b5";
			 s = b5.getText();
		}
		else if (b==b6) {
			 button = "b6";
			 s = b6.getText();
		}
		else if (b==b7) {
			 button = "b7";
			 s = b7.getText();
		}
		else if (b==b8) {
			 button = "b8";
			 s = b8.getText();
		}
		else if (b==b9) {
			 button = "b9";
			 s = b9.getText();
		}
		switch (button) {
		case "b1":
			s = b1.getText();
			if (b2.getText().equals(" ")) {
				b2.setText(s);
				b1.setText(" ");
			}
			else if (b4.getText().equals(" ")) {
				b4.setText(s);
				b1.setText(" ");
			}
			break;
		case "b2":
			 s = b2.getText();
			if (b1.getText().equals(" ")) {
				b1.setText(s);
				b2.setText(" ");
			}
			else if (b3.getText().equals(" ")) {
				b3.setText(s);
				b2.setText(" ");
			}
			else if (b5.getText().equals(" ")) {
				b5.setText(s);
				b2.setText(" ");
			}
			break;
		case "b3":
			 s = b3.getText();
			if (b2.getText().equals(" ")) {
				b2.setText(s);
				b3.setText(" ");
			}
			else if (b6.getText().equals(" ")) {
				b6.setText(s);
				b3.setText(" ");
			}
			break;
		case "b4":
			 s = b4.getText();
			if (b1.getText().equals(" ")) {
				b1.setText(s);
				b4.setText(" ");
			}
			else if (b5.getText().equals(" ")) {
				b5.setText(s);
				b4.setText(" ");
			}
			else if (b7.getText().equals(" ")) {
				b7.setText(s);
				b4.setText(" ");
			}
			break;
		case "b5":
			 s = b5.getText();
			if (b2.getText().equals(" ")) {
				b2.setText(s);
				b5.setText(" ");
			}
			else if (b4.getText().equals(" ")) {
				b4.setText(s);
				b5.setText(" ");
			}
			else if (b6.getText().equals(" ")) {
				b6.setText(s);
				b5.setText(" ");
			}
			else if (b8.getText().equals(" ")) {
				b8.setText(s);
				b5.setText(" ");
			}
			break;
		case "b6":
			 s = b6.getText();
			if (b3.getText().equals(" ")) {
				b3.setText(s);
				b6.setText(" ");
			}
			else if (b5.getText().equals(" ")) {
				b5.setText(s);
				b6.setText(" ");
			}
			else if (b9.getText().equals(" ")) {
				b9.setText(s);
				b6.setText(" ");
			}
			break;
		case "b7":
			 s = b7.getText();
			if (b4.getText().equals(" ")) {
				b4.setText(s);
				b7.setText(" ");
			}
			else if (b8.getText().equals(" ")) {
				b8.setText(s);
				b7.setText(" ");
			}
			break;
		case "b8":
			 s = b8.getText();
			if (b5.getText().equals(" ")) {
				b5.setText(s);
				b8.setText(" ");
			}
			else if (b7.getText().equals(" ")) {
				b7.setText(s);
				b8.setText(" ");
			}
			else if (b9.getText().equals(" ")) {
				b9.setText(s);
				b8.setText(" ");
			}
			break;
		case "b9":
			 s = b9.getText();
			if (b8.getText().equals(" ")) {
				b8.setText(s);
				b9.setText(" ");
			}
			else if (b6.getText().equals(" ")) {
				b6.setText(s);
				b9.setText(" ");
			}
			break;
		default:
			break;
			
		    
		}
		if (hasFinished()) {
	        winnerLabel.setText("Good Job! Remember this number... 6");
	        // Disable all buttons
	        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
	        for (JButton x : buttons) x.setEnabled(false);
	    }
		
	}

}

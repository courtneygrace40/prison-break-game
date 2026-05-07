package rooms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.KeyHandler;
import main.Modify_Frame;

public class SliderPuzzleRoom extends JDialog implements RoomChallenge, ActionListener {
    
    private static final long serialVersionUID = 1L;
	Modify_Frame mf;
    KeyHandler kh;
    JButton  b1, b2, b3, b4, b5, b6, b7, b8, b9, fake;
    JLabel winnerLabel;
    String direction;
	ImageIcon img1 = new ImageIcon(getClass().getResource("/Key1.png"));
	ImageIcon img2 = new ImageIcon(getClass().getResource("/Key2.png"));
	ImageIcon img3 = new ImageIcon(getClass().getResource("/Key3.png"));
	ImageIcon img4 = new ImageIcon(getClass().getResource("/Key4.png"));
	ImageIcon img5 = new ImageIcon(getClass().getResource("/Key5.png"));
	ImageIcon img6 = new ImageIcon(getClass().getResource("/Key6.png"));
	ImageIcon img7 = new ImageIcon(getClass().getResource("/Key7.png"));
	ImageIcon img8 = new ImageIcon(getClass().getResource("/Key8.png"));
	ImageIcon img9 = new ImageIcon(getClass().getResource("/KeyBlank.png"));
    
    public SliderPuzzleRoom(Modify_Frame mf, KeyHandler kh) {
        
        this.mf = mf;
        this.kh = kh;
        this.direction = "top";
        
        setLayout(null);
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
		winnerLabel.setBounds(90, 250, 250, 40);
		
		
		
	}

	private void addButtons() {
		// TODO Auto-generated method stub
		add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);
		Container contentPane = this.getContentPane();
		contentPane.add(winnerLabel);
		add(fake);
	}

	public void initializeButtons() {
		// TODO Auto-generated method stub
		
		
		b1 = new JButton(img1);
		b2 = new JButton(img9);
		b3 = new JButton(img3);
		b4= new JButton(img4);
		b5= new JButton(img5);
		b6= new JButton(img6);
		b7= new JButton(img7);
		b8= new JButton(img8);
		b9= new JButton(img2);
		winnerLabel = new JLabel("Order the numbers from 1-8");
		fake = new JButton(" ");
	}

	public void shuffle() {
		 ImageIcon s = (ImageIcon) b4.getIcon();
		  	b4.setIcon(b9.getIcon());
		    b9.setIcon(s);
		    s = (ImageIcon)b1.getIcon();
		    b1.setIcon(b5.getIcon());
		    b5.setIcon(s);
		    s = (ImageIcon)b2.getIcon();
		    b2.setIcon(b7.getIcon());
		    b7.setIcon(s);
	}
	
	@Override
	public boolean hasFinished() {
		if (b1.getIcon().equals(img1)&&b2.getIcon().equals(img2)&&b3.getIcon().equals(img3)&&b4.getIcon().equals(img4)&&b5.getIcon().equals(img5)&&b6.getIcon().equals(img6)&&b7.getIcon().equals(img7)&&b8.getIcon().equals(img8)) {
			return true;
		}
		return false;
	}
  

	@Override
	public void actionPerformed(ActionEvent e) {
		Object b = e.getSource();
		Icon s;
		String button = null;
		if (b==b1) {
			 button = "b1";
			 s = b1.getIcon();
		}
		else if (b==b2) {
			 button = "b2";
			 s = b2.getIcon();
		}
		else if (b==b3) {
			 button = "b3";
			 s = b3.getIcon();
		}
		else if (b==b4) {
			 button = "b4";
			 s = b4.getIcon();
		}
		else if (b==b5) {
			 button = "b5";
			 s = b5.getIcon();
		}
		else if (b==b6) {
			 button = "b6";
			 s = b6.getIcon();
		}
		else if (b==b7) {
			 button = "b7";
			 s = b7.getIcon();
		}
		else if (b==b8) {
			 button = "b8";
			 s = b8.getIcon();
		}
		else if (b==b9) {
			 button = "b9";
			 s = b9.getIcon();
		}
		switch (button) {
		case "b1":
			s = b1.getIcon();
			if (b2.getIcon().equals(img9)) {
				b2.setIcon(s);
				b1.setIcon(img9);
			}
			else if (b4.getIcon().equals(img9)) {
				b4.setIcon(s);
				b1.setIcon(img9);
			}
			break;
		case "b2":
			 s = b2.getIcon();
			if (b1.getIcon().equals(img9)) {
				b1.setIcon(s);
				b2.setIcon(img9);
			}
			else if (b3.getIcon().equals(img9)) {
				b3.setIcon(s);
				b2.setIcon(img9);
			}
			else if (b5.getIcon().equals(img9)) {
				b5.setIcon(s);
				b2.setIcon(img9);
			}
			break;
		case "b3":
			 s = b3.getIcon();
			if (b2.getIcon().equals(img9)) {
				b2.setIcon(s);
				b3.setIcon(img9);
			}
			else if (b6.getIcon().equals(img9)) {
				b6.setIcon(s);
				b3.setIcon(img9);
			}
			break;
		case "b4":
			 s = b4.getIcon();
			if (b1.getIcon().equals(img9)) {
				b1.setIcon(s);
				b4.setIcon(img9);
			}
			else if (b5.getIcon().equals(img9)) {
				b5.setIcon(s);
				b4.setIcon(img9);
			}
			else if (b7.getIcon().equals(img9)) {
				b7.setIcon(s);
				b4.setIcon(img9);
			}
			break;
		case "b5":
			 s = b5.getIcon();
			if (b2.getIcon().equals(img9)) {
				b2.setIcon(s);
				b5.setIcon(img9);
			}
			else if (b4.getIcon().equals(img9)) {
				b4.setIcon(s);
				b5.setIcon(img9);
			}
			else if (b6.getIcon().equals(img9)) {
				b6.setIcon(s);
				b5.setIcon(img9);
			}
			else if (b8.getIcon().equals(img9)) {
				b8.setIcon(s);
				b5.setIcon(img9);
			}
			break;
		case "b6":
			 s = b6.getIcon();
			if (b3.getIcon().equals(img9)) {
				b3.setIcon(s);
				b6.setIcon(img9);
			}
			else if (b5.getIcon().equals(img9)) {
				b5.setIcon(s);
				b6.setIcon(img9);
			}
			else if (b9.getIcon().equals(img9)) {
				b9.setIcon(s);
				b6.setIcon(img9);
			}
			break;
		case "b7":
			 s = b7.getIcon();
			if (b4.getIcon().equals(img9)) {
				b4.setIcon(s);
				b7.setIcon(img9);
			}
			else if (b8.getIcon().equals(img9)) {
				b8.setIcon(s);
				b7.setIcon(img9);
			}
			break;
		case "b8":
			 s = b8.getIcon();
			if (b5.getIcon().equals(img9)) {
				b5.setIcon(s);
				b8.setIcon(img9);
			}
			else if (b7.getIcon().equals(img9)) {
				b7.setIcon(s);
				b8.setIcon(img9);
			}
			else if (b9.getIcon().equals(img9)) {
				b9.setIcon(s);
				b8.setIcon(img9);
			}
			break;
		case "b9":
			 s = b9.getIcon();
			if (b8.getIcon().equals(img9)) {
				b8.setIcon(s);
				b9.setIcon(img9);
			}
			else if (b6.getIcon().equals(img9)) {
				b6.setIcon(s);
				b9.setIcon(img9);
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
	        mf.advanceList(direction);
	      
	    }
		
	}

}

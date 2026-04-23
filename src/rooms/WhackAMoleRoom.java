package rooms;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import main.KeyHandler;
import main.Modify_Frame;

public class WhackAMoleRoom extends JDialog implements RoomChallenge, ActionListener{
	Random rand = new Random();
	Timer bugTimer;
	int bugPosition;
	Modify_Frame mf;
    KeyHandler kh;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9; //positions
	ArrayList <JButton> buttons = new ArrayList <JButton>(); 
	int kills = 0;
	
	public WhackAMoleRoom(Modify_Frame mf, KeyHandler kh){
		this.setLayout(new GridLayout(3,3));
		
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(mf); // Centers the puzzle over the game
        
        // Change close operation so it doesn't kill the whole game
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.mf= mf;
		this.kh=kh;
        initializeButtons();
        addButtons();
        addActionListener();
        
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);
        buttons.add(b9);
        
        
        //start mole movement
        bugTimer = new Timer(1000, new ActionListener() { // 1000ms = 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasFinished()) {
                    paintObjects();
                } else {
                    bugTimer.stop();
                    JOptionPane.showMessageDialog(null, "Mission Accomplished! Code: 4");
                }
            }
        });
        bugTimer.start();
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

	private void addButtons() {
		add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);
	}

	public void initializeButtons() {
		b1 = new JButton(" ");
		b2 = new JButton(" ");
		b3 = new JButton(" ");
		b4= new JButton(" ");
		b5= new JButton(" ");
		b6= new JButton(" ");
		b7= new JButton(" ");
		b8= new JButton(" ");
		b9= new JButton(" ");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object b = e.getSource();
		if (b.equals(buttons.get(bugPosition))) {
			kills ++;
			bugPosition = rand.nextInt(0,8);
		}
		else {
			//do nothing
		}
		
	}

	@Override
	public boolean hasFinished() {
		// TODO Auto-generated method stub
		if (kills == 10) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public void updateLogic() {
//		while (hasFinished() == false){
//			bugPosition = rand.nextInt(0,8);
//			paintObjects();
//			long sleepTime = rand.nextLong((long) 2.5);
//			try {
//				Thread.sleep(sleepTime);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		
	}

	@Override
	public void paintObjects() {
	initializeButtons();
	(buttons.get(bugPosition)).setText("BUG");
		
		
	}

}

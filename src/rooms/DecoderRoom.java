package rooms;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import main.KeyHandler;
import main.Modify_Frame;

public class DecoderRoom extends JDialog implements RoomChallenge, ActionListener {
	private static final long serialVersionUID = 1L;
	
	//should decode to PAGE TEN
	// Caeser Cipher: Shifts all letters + 3
	
	KeyHandler kh;
	Modify_Frame mf;
	JButton b1, b2, b3, b4, b5, b6, b7, reset;
	JLabel label1, label2, label3, label4, label5, label6, label7, hint;
	String[] alphabet = {"P","A","G","E","T","N"};
	
	public DecoderRoom(Modify_Frame mf, KeyHandler kh) {
		this.kh = kh;
		this.mf = mf;
		
        setLayout(null);
        setSize(450, 300);
        setResizable(false);
        setLocationRelativeTo(mf); // Centers the puzzle over the game
        
        // Change close operation so it doesn't kill the whole game
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeButtons();
        initializeLabels();
        addComponents();
        setbounds();
        addActionListener();
        
        
	}

	private void addActionListener() {
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		//reset.addActionListener(this);
		
	}

	private void setbounds() {
		b1.setBounds(50, 50, 40,50);
		b2.setBounds(100, 50, 40,50);
		b3.setBounds(150, 50, 40,50);
		b4.setBounds(200, 50, 40,50);
		b5.setBounds(250, 50, 40,50);
		b6.setBounds(300, 50, 40,50);
		b7.setBounds(350, 50, 40,50);
		//reset.setBounds(175, 170, 80, 50);
		
		label1.setBounds(60, 110, 40, 20);
		label2.setBounds(110, 110, 40,20);
		label3.setBounds(160, 110, 40,20);
		label4.setBounds(210, 110, 40,20);
		label5.setBounds(260, 110, 40,20);
		label6.setBounds(310, 110, 40,20);
		label7.setBounds(360, 110, 40,20);
		hint.setBounds(50, 150, 400, 100); //change if reset exists
				
	}

	private void addComponents() {
		add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);//add(reset);
		
		Container contentPane = this.getContentPane();
		contentPane.add(label1);contentPane.add(label2);contentPane.add(label3);contentPane.add(label4);
		contentPane.add(label5);contentPane.add(label6);contentPane.add(label7);contentPane.add(hint);
		
	}

	private void initializeLabels() {
		label1 = new JLabel("G");
		label2 = new JLabel("E");
		label3 = new JLabel("N");
		label4 = new JLabel("A");
		label5 = new JLabel("P");
		label6 = new JLabel("T");
		label7 = new JLabel("E");
		hint = new JLabel("Hint: Click the letters to unscrabble the secret message...");
		
	}

	private void initializeButtons() {
		b1 = new JButton(alphabet[2]);
		b2 = new JButton(alphabet[3]);
		b3 = new JButton(alphabet[5]);
		b4= new JButton(alphabet[1]);
		b5= new JButton(alphabet[0]);
		b6= new JButton(alphabet[4]);
		b7= new JButton(alphabet[3]);
		//reset= new JButton("Reset");
		
	}
	
//	public void resetButtons() {
//		b1.setText(alphabet[2]);
//		b2.setText(alphabet[3]);
//		b3.setText(alphabet[5]);
//		b4.setText(alphabet[1]);
//		b5.setText(alphabet[0]);
//		b6.setText(alphabet[4]);
//		b7.setText(alphabet[3]);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		//if (b.getText().equals("Reset")) {
			//resetButtons();
		//}
		//else {
		int index;
		String s = b.getText();
		index = Arrays.asList(alphabet).indexOf(s);
		
		updateButton(index, b);
		
		if (hasFinished()) {
			
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			b5.setEnabled(false);
			b6.setEnabled(false);
			b7.setEnabled(false);
			//reset.setEnabled(false);
			
			label1.setText("P");
			label2.setText("A");
			label3.setText("G");
			label4.setText("E");
			label5.setText("T");
			label6.setText("E");
			label7.setText("N");
			
			hint.setText("Nice job-- remember this message for later");
		}
		//}

	}

	@Override
	public boolean hasFinished() {
		if(b1.getText().equals("P")&&b2.getText().equals("A")&&b3.getText().equals("G")&&b4.getText().equals("E")&&b5.getText().equals("T")&&b6.getText().equals("E")&&b7.getText().equals("N")) {
			return true;
		}
		else {
		return false;
		}
	}
	
	public void updateButton(int i, JButton b) {
		//update button one letter
		if (i == 5) {
			b.setText(alphabet[0]);
		}
		else {
			i ++;
			b.setText(alphabet[i]);
		}
		
//		if (b.equals(b1)) {
//			if (b.getText().equals("P")) {
//				b1.setBackground(Color.green);
//			}
//			else {
//				b1.setBackground(Color.white);
//			}
//		}
//		else if (b.equals(b2)) {
//			if (b.getText().equals("A")) {
//				b2.setBackground(Color.green);
//			}
//			else {
//				b2.setBackground(Color.white);
//			}
//		}
//		else if (b.equals(b3)) {
//			if (b.getText().equals("G")) {
//				b3.setBackground(Color.green);
//			}
//			else {
//				b2.setBackground(Color.white);
//			}
//		}
//		else if (b.equals(b4)) {
//			if (b.getText().equals("E")) {
//				b4.setBackground(Color.green);
//			}
//			else {
//				b4.setBackground(Color.white);
//			}
//		}
//		else if (b.equals(b5)) {
//			if (b.getText().equals("T")) {
//				b5.setBackground(Color.green);
//			}
//			else {
//				b5.setBackground(Color.white);
//			}
//		}
//		else if (b.equals(b6)) {
//			if (b.getText().equals("E")) {
//				b6.setBackground(Color.green);
//			}
//			else {
//				b6.setBackground(Color.white);
//			}
//		}
//		else if (b.equals(b7)) {
//			if (b.getText().equals("N")) {
//				b7.setBackground(Color.green);
//			}
//			else {
//				b7.setBackground(Color.white);
//			}
//		}
		
	}

}

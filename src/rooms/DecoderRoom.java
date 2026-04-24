package rooms;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.*;

import main.KeyHandler;
import main.Modify_Frame;

public class DecoderRoom extends JDialog implements RoomChallenge, ActionListener {
	private static final long serialVersionUID = 1L;
	
	KeyHandler kh;
	Modify_Frame mf;
	JButton b1, b2, b3, b4, b5, b6, b7, reset;
	JLabel label1, label2, label3, label4, label5, label6, label7, hint;
	String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W","X","Y","Z"};
	
	public DecoderRoom(Modify_Frame mf, KeyHandler kh) {
		this.kh = kh;
		this.mf = mf;
		
        setLayout(null);
        setSize(450, 250);
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
		reset.addActionListener(this);
		
	}

	private void setbounds() {
		b1.setBounds(50, 50, 40,50);
		b2.setBounds(100, 50, 40,50);
		b3.setBounds(150, 50, 40,50);
		b4.setBounds(200, 50, 40,50);
		b5.setBounds(250, 50, 40,50);
		b6.setBounds(300, 50, 40,50);
		b7.setBounds(350, 50, 40,50);
		reset.setBounds(175, 170, 80, 50);
		
		label1.setBounds(60, 110, 40, 20);
		label2.setBounds(110, 110, 40,20);
		label3.setBounds(160, 110, 40,20);
		label4.setBounds(210, 110, 40,20);
		label5.setBounds(260, 110, 40,20);
		label6.setBounds(310, 110, 40,20);
		label7.setBounds(360, 110, 40,20);
		hint.setBounds(50, 200, 400, 100);
				
	}

	private void addComponents() {
		add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(reset);
		
		Container contentPane = this.getContentPane();
		contentPane.add(label1);contentPane.add(label2);contentPane.add(label3);contentPane.add(label4);
		contentPane.add(label5);contentPane.add(label6);contentPane.add(label7);contentPane.add(hint);
		
	}

	private void initializeLabels() {
		label1 = new JLabel("0");
		label2 = new JLabel("1");
		label3 = new JLabel("2");
		label4 = new JLabel("3");
		label5 = new JLabel("4");
		label6 = new JLabel("5");
		label7 = new JLabel("6");
		hint = new JLabel("Hint: When -3 is the same as +23...");
		
	}

	private void initializeButtons() {
		b1 = new JButton(alphabet[15+3]);
		b2 = new JButton(alphabet[0+3]);
		b3 = new JButton(alphabet[6+3]);
		b4= new JButton(alphabet[4+3]);
		b5= new JButton(alphabet[19+3]);
		b6= new JButton(alphabet[4+3]);
		b7= new JButton(alphabet[13+3]);
		reset= new JButton("Reset");
		
	}
	
	public void resetButtons() {
		b1.setText(alphabet[15+3]);
		b2.setText(alphabet[3]);
		b3.setText(alphabet[9]);
		b4.setText(alphabet[7]);
		b5.setText(alphabet[22]);
		b6.setText(alphabet[7]);
		b7.setText(alphabet[16]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		if (b.getText().equals("Reset")) {
			resetButtons();
		}
		else {
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
			reset.setEnabled(false);
			
			label1.setText("P");
			label2.setText("A");
			label3.setText("G");
			label4.setText("E");
			label5.setText("T");
			label6.setText("E");
			label7.setText("N");
			
			hint.setText("Nice job-- remember this message for later");
		}
		}

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

	@Override
	public void updateLogic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintObjects() {

	}
	
	public void updateButton(int i, JButton b) {
		//update button one letter
		if (i == 25) {
			b.setText(alphabet[0]);
		}
		else {
			i ++;
			b.setText(alphabet[i]);
		}
	}

}

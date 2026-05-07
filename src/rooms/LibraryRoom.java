package rooms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.KeyHandler;
import main.Modify_Frame;

import javax.swing.*;

public class LibraryRoom extends JDialog implements RoomChallenge, ActionListener{
	
	JButton forward, back;
	int pageNum = 0;
	ImageIcon page1_2, page3_4, page5_6, page7_8, page9_10;
	ArrayList <ImageIcon> bookPages = new ArrayList <ImageIcon>();
	JLabel book;
	Modify_Frame mf;
	KeyHandler kh;

	private static final long serialVersionUID = 1L;
	
	public LibraryRoom(Modify_Frame mf, KeyHandler kh) {
		this.mf= mf;
		this.kh = kh;
		
		this.setLayout(null);
		setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(mf);
		
		setImages();
		bookPages.add(page1_2);bookPages.add(page3_4);bookPages.add(page5_6);bookPages.add(page7_8);bookPages.add(page9_10);
		
		initializeButtons();
        addButtons();
        addActionListener();
		
		book = new JLabel(bookPages.get(pageNum));
		book.setBounds(0,0, 400,300);
		this.add(book);
		

	}
	
	private void initializeButtons() {
		forward = new JButton("Next");
		back = new JButton("Back");
		
		forward.setBounds(350,220,50,50);
		back.setBounds(10, 220, 50, 50);
	}
	
	private void addButtons() {
		add(forward);add(back);
	}
	
	private void addActionListener() {
		forward.addActionListener(this);
		back.addActionListener(this);
	}
	
	private void setImages() {
		java.net.URL page1 = getClass().getResource("/pages/page1_2.png");
		java.net.URL page2 = getClass().getResource("/pages/page3_4.png");
		java.net.URL page3 = getClass().getResource("/pages/page5_6.png");
		java.net.URL page4 = getClass().getResource("/pages/page7_8.png");
		java.net.URL page5 = getClass().getResource("/pages/page9_10.png");
		page1_2 = new ImageIcon(page1);
		page3_4 = new ImageIcon(page2);
		page5_6 = new ImageIcon(page3);
		page7_8 = new ImageIcon(page4);
		page9_10 = new ImageIcon(page5);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object b = e.getSource();
		
		if (b.equals(forward)) {
			if (pageNum<4) {
				pageNum ++;
				}
		}
		
		else if (b.equals(back)) {
			if (pageNum > 0) {
			pageNum--;
			}
			
		
		}
		book.setIcon(bookPages.get(pageNum));
		this.repaint();
	}

	@Override
	public boolean hasFinished() {
		// TODO Auto-generated method stub
		return false;
	}


}

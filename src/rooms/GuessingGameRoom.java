package rooms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.KeyHandler;
import main.Modify_Frame;

public class GuessingGameRoom extends JDialog implements RoomChallenge, ActionListener{

	private static final long serialVersionUID = 1L;
	KeyHandler kh;
	Modify_Frame mf;
	String direction;
	JButton a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4;
	ArrayList <JButton> buttons = new ArrayList <JButton>();
	ArrayList <ImageIcon> positions = new ArrayList <ImageIcon>();
	Boolean locked = false;
	int playerWins = 0;
	ArrayList <JButton> cards = new ArrayList <JButton>();
	int cardsFlipped;
	BufferedImage noteImage;
	Boolean noteShown = false;
	Timer slideTimer;
	int noteY = -200;
	int speed = 3;
	
	
	ImageIcon card1 = new ImageIcon(getClass().getResource("/cards/barsCard.png"));
	ImageIcon card2 = new ImageIcon(getClass().getResource("/cards/bloodCard.png"));
	ImageIcon card3 = new ImageIcon(getClass().getResource("/cards/lilguardCard.png"));
	ImageIcon card4 = new ImageIcon(getClass().getResource("/cards/spidercard.png"));
	ImageIcon card0 = new ImageIcon(getClass().getResource("/cards/blankcard.png"));
	
	
	public GuessingGameRoom(Modify_Frame mf, KeyHandler kh) {
		this.mf = mf;
		this.kh = kh;
		this.direction = "top";
		
        setLayout(null);
        setSize(400, 410);
        setResizable(false);
        setLocationRelativeTo(mf); // Centers the puzzle over the game
        
        // Change close operation so it doesn't kill the whole game
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        initializeButtons();
        addButtons();
        setbounds();
        addActionListener();
        
        buttons.add(a1);buttons.add(a2);buttons.add(a3);buttons.add(a4);
        buttons.add(b1);buttons.add(b2);buttons.add(b3);buttons.add(b4);
        buttons.add(c1);buttons.add(c2);buttons.add(c3);buttons.add(c4);
        buttons.add(d1);buttons.add(d2);buttons.add(d3);buttons.add(d4);
        
        positions.add(card1);positions.add(card1);positions.add(card1);positions.add(card1);
        positions.add(card2);positions.add(card2);positions.add(card2);positions.add(card2);
        positions.add(card3);positions.add(card3);positions.add(card3);positions.add(card3);
        positions.add(card4);positions.add(card4);positions.add(card4);positions.add(card4);
        
        shuffle();
        cardsFlipped = 0;
        playerWins=0;
        
        try {
			noteImage = ImageIO.read(getClass().getResourceAsStream("/cards/notetwo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public void initializeButtons(){
		a1 = new JButton(card0);
		a2 = new JButton(card0);
		a3 = new JButton(card0);
		a4 = new JButton(card0);
		b1 = new JButton(card0);
		b2 = new JButton(card0);
		b3 = new JButton(card0);
		b4 = new JButton(card0);
		c1 = new JButton(card0);
		c2 = new JButton(card0);
		c3 = new JButton(card0);
		c4 = new JButton(card0);
		d1 = new JButton(card0);
		d2 = new JButton(card0);
		d3 = new JButton(card0);
		d4 = new JButton(card0);
	}
	
	public void addButtons() {
		add(a1); add(a2);add(a3);add(a4);
		add(b1);add(b2);add(b3);add(b4);
		add(c1);add(c2);add(c3);add(c4);
		add(d1);add(d2);add(d3);add(d4);
		
	}
	
	public void setbounds() {
		//start at x:20, y:20, space out cards by 10 between each card
		a1.setBounds(20, 20, 80, 80);
		a2.setBounds(110, 20, 80, 80);
		a3.setBounds(200, 20, 80, 80);
		a4.setBounds(290, 20, 80, 80);
		b1.setBounds(20, 110, 80, 80);
		b2.setBounds(110, 110, 80, 80);
		b3.setBounds(200, 110, 80, 80);
		b4.setBounds(290, 110, 80, 80);
		c1.setBounds(20, 200, 80, 80);
		c2.setBounds(110, 200, 80, 80);
		c3.setBounds(200, 200, 80, 80);
		c4.setBounds(290, 200, 80, 80);
		d1.setBounds(20, 290, 80, 80);
		d2.setBounds(110, 290, 80, 80);
		d3.setBounds(200, 290, 80, 80);
		d4.setBounds(290, 290, 80, 80);
	}
	
	public void addActionListener() {
		a1.addActionListener(this);
		a2.addActionListener(this);
		a3.addActionListener(this);
		a4.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);
		d1.addActionListener(this);
		d2.addActionListener(this);
		d3.addActionListener(this);
		d4.addActionListener(this);
	}
	
	public void shuffle() {
		
		Random rand = new Random();
        
        // Start from the last element and swap one by one
        for (int i = positions.size() - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = rand.nextInt(i + 1);
            
            // Swap list.get(i) with the element at random index j
            ImageIcon temp = positions.get(i);
            positions.set(i, positions.get(j));
            positions.set(j, temp);
		}
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (locked) {
			return;
		}
		
		Timer flipTimer = new Timer (500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Paused...");
				cards.get(0).setIcon(card0);
				cards.get(1).setIcon(card0);
				cardsFlipped = 0;
				
				cards.clear();
				locked = false;
				
			}
			
		});
		flipTimer.setRepeats(false);
		
		Object b = e.getSource();
		
		if (cardsFlipped<2) {
		int pos = buttons.indexOf(b);
		JButton button = buttons.get(pos);
		if (cards.contains(button)) return; //thx gemini
		cards.add(buttons.get(pos));
		button.setIcon(positions.get(pos));
		cardsFlipped++;
		System.out.println(cardsFlipped);
		System.out.println(cards);
		}
		if (cardsFlipped == 2) {
			locked = true;
			if((cards.get(0)).getIcon().equals(cards.get(1).getIcon())) {
				System.out.println("Good");
				playerWins++;
				
				cards.get(0).setEnabled(false);
				cards.get(1).setEnabled(false);
				
				
			}
			flipTimer.start();
			
			
			}
		if (hasFinished() && !noteShown) {
			// the inmate slides you a note 
			noteShown= true;
			slideNote();
			System.out.println("Done!");
			mf.advanceList(direction);
		}
		
	}

	private void slideNote() {
		    slideTimer = new Timer(16, new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            noteY += speed;
		            if (noteY > 150) { // Target Y position
		                slideTimer.stop();
		            }
		            repaint(); // This tells the Dialog to call paint()
		        }
		    });
		    slideTimer.start();
		    
		    
		}
		

	@Override
	public boolean hasFinished() {
		if (playerWins >= 8) {
			return true;
		}
		return false;
	}

	
	@Override
	public void paint(Graphics g) {
	    super.paint(g); // Draws the buttons and background first
	    
	    if (noteShown && noteImage != null) {
	        Graphics2D g2d = (Graphics2D) g;
	        int centerX = (getWidth() - noteImage.getWidth()) / 2;
	        g2d.drawImage(noteImage, centerX, noteY, null);
	    }
	}

}

package ChatBox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatGui extends JPanel {
	private static final long serialVersionUID = 1L; //eclipse needs this
	private JTextArea messageArea;
	private JTextField textField;
	private JButton sendButton;
	private chat newChat;
	
	public ChatGui() {
		newChat = new chat();
		messageArea = new JTextArea(10,20); //creates a text box area to displayb all of the lines 
		textField = new JTextField(10); //creates the ability to add a line of text into the box
		sendButton = new JButton("Send"); //creates a send button to use
		
		//set text to wrap
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		
		//create a layout on how everything will be placed in the game
		setLayout(new BorderLayout()); 
		add (new JScrollPane(messageArea), BorderLayout.CENTER); //allows you to scroll through the textarea to look at all of the lines
		JPanel bottomPanel = new JPanel(new BorderLayout()); //create a bottom panel to allow the text field and button 
		bottomPanel.add(textField, BorderLayout.CENTER);
		bottomPanel.add(sendButton,BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);
		
		//make send button work
		sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
		
	}
	//method to create and send messages in the chat box
	private void sendMessage() {
		String text = textField.getText(); //gets text from textField box
		if (!text.isEmpty()) {
			newChat.addMessage(text); //adds new message into the array list 
			messageArea.append(text + "\n"); //allows the box area to display the line and then produce a new one
			textField.setText(""); //resets box to blank
		}
		
	}
}
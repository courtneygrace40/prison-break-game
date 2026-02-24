package ChatBox;
import java.util.ArrayList;
import java.util.Scanner;

public class chat {
	private ArrayList<String> messages;
    private Scanner input;
    
    public chat() {
    	messages = new ArrayList<String>();
    	input = new Scanner(System.in);
    }
    public void addMessage(String message) {
    	messages.add(message);
    }
    public void printMessage() {
    	for (String msg: messages) {
    		System.out.println(msg);
    	}
    }
    public void runChat() {
    	while (true) {
    		System.out.println("Enter message on screen, else type quit to exit");
    		String message = input.nextLine();
    		if (message.equalsIgnoreCase("quit")) {
    			break;
    		}
    		addMessage(message);
    		printMessage();
    	}
    }
}

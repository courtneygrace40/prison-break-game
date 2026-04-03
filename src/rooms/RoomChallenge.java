package rooms;

public interface RoomChallenge {
    boolean hasFinished();           // Has the player won?
    void updateLogic();              // Any timed events
    void paintObjects();			//if things need to be drawn? 
}
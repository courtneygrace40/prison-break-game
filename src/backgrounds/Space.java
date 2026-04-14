package backgrounds;

import java.awt.Rectangle;
import java.util.ArrayList;

public interface Space {

	
	public ArrayList<Entrance> entrances = new ArrayList<>();
	public String getKey();
	public ArrayList<Entrance> getEntrances();
	public ArrayList<Rectangle> walls = new ArrayList<>();
	int getPlayerX();
    void setPlayerX(int x);
    int getPlayerY();
    void setPlayerY(int y);
    public ArrayList<Rectangle> getWalls();
}

package backgrounds;


public class Entrance {
	
	public String entranceType; //0 1 2 3 for top, right, bottom, left, 4 = enter room?  
	public Space bg;
	public int xMin;
	public int yMin;
	public int xMax;
	public int yMax;
	
	public Entrance(Space bg, String entranceType, int xMin, int yMin, int xMax, int yMax) {
		
		this.entranceType = entranceType;
		this.bg = bg;
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax; 
		
	}

	
	
}
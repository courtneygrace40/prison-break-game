package backgrounds;


public class Entrance {
	
	public int entranceType; //0 1 2 3 for top, right, bottom, left 
	public Background bg;
	public int xMin;
	public int yMin;
	public int xMax;
	public int yMax;
	
	public Entrance(Background bg, int entranceType, int xMin, int yMin, int xMax, int yMax) {
		
		this.entranceType = entranceType;
		this.bg = bg;
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax; 
		
	}
	
	
}
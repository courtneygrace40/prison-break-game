package backgrounds;

public class BG_Node {
	

		
		public int playerx;
		public int playery;
		
		public Background nextBG;
		
		
		
		public BG_Node(int playerx, int playery, Background nextBG) {
			
			this.playerx = playerx;
			this.playery = playery;
			this.nextBG = nextBG;
		
		}
		
}

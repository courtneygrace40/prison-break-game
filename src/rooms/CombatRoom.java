package rooms;

public class CombatRoom {
	public int playerHealth;
	public int guardHealth;
	
	public CombatRoom() {
		playerHealth = 50;
		guardHealth = 20;

	}
	public void playerAttack() {
		guardHealth -= 5;
	}
	
	public void guardAttack() {
		playerHealth -= 5;
	}
	
	public boolean isOver() {
		return playerHealth <= 0 || guardHealth <= 0;
	}
	
	public boolean playerWon() {
		return guardHealth <= 0;
	}
	
	public boolean guardWon() {
		return playerHealth <= 0;
	}
	


}


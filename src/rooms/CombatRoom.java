package rooms;

public class CombatRoom {
	public int playerHealth;
	public int guardHealth;
	
	public CombatRoom() {
		playerHealth = 40;
		guardHealth = 40;

	}
	public void playerAttack() {
		guardHealth -= 5;
	}
	
	public void guardAttack() {
		playerHealth -= 2;
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


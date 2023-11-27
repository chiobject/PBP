package unit;

public abstract class unit {
	int prodTime;
	int prodAmount;
	int attack;
	int moveTime;
	
	public unit(int prodTime, int prodAmount, int attack, int moveTime) {
		this.prodTime = prodTime;
		this.prodAmount = prodAmount;
		this.attack = attack;
		this.moveTime = moveTime;
	}
	
	public void adjustProdTime(int prodTime) {
		this.prodTime += prodTime;
	}
	
	public void adjustProdAmount(int prodAmount) {
		this.prodAmount += prodAmount;
	}
	
	public void adjustAttack(int attack) {
		this.attack += attack;
	}
	
	public void adjustMoveTime(int moveTime) {
		this.moveTime += moveTime;
	}
	
}

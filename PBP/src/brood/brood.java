package brood;

public abstract class brood {
	int brood; // 1:"인간" 2:"고블린" 3:"엘프"

	protected int AD; // 공격력
	protected int speed; // 이동속도
	protected int maxPopulationCount; // 기지 최대 인구수
	protected String unitLeft;
	protected String unitRight;
	protected int popProdRate;
	protected int popProdSpeed;

	public brood(int brood) {
		this.brood = brood;
	}

	public void setBrood(int brood) {
		this.brood = brood;
	}
	// 생산할 때 한번에 처리할 것인가 따로 할것인가?? 따로 하는게 나을것 같음 겹치면 동시에 올라가게 되는 경우도 있음

    public String getUnitLeft() {
    	return unitLeft;
    }
    
    public String getUnitRight() {
    	return unitRight;
    }
    public int getAD() {
    	return AD;
    }
    public int getSpeed() {
    	return speed;
    }
    public int getpopProdRate() {
    	return popProdRate;
    }
    public int getpopProdSpeed() {
    	return popProdSpeed;
    }
}

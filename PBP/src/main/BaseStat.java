package main;


public class BaseStat {
	
	private double AD;		// 공격력
	private double speed; 	// 이동력
	private int nowPopulationCount;	// 기지 현재 인구수
	private int maxPopulationCount;	// 기지 최대 인구수

	public BaseStat(double AD, double speed, int nowPopulationCount, int maxPopulationCount) {
		this.AD = AD;
		this.speed = speed;
		this.nowPopulationCount = nowPopulationCount;
		this.maxPopulationCount = maxPopulationCount;
	}
	
	public double getAD() {
		return AD;
	}
	public void setAD(double AD) {
		this.AD = AD;
	}
	public double getspeed() {
		return speed;
	}
	public void setspeed(double speed) {
		this.speed = speed;
	}
	public int getNowPopulationCount() {
		return nowPopulationCount;
	}
	public void setNowPopulationCount(int nowPopulationCount) {
		this.nowPopulationCount = nowPopulationCount;
	}
	public int getMaxPopulationCount() {
		return maxPopulationCount;
	}
	public void setMaxPopulationCount(int maxPopulationCount) {
		this.maxPopulationCount = maxPopulationCount;
	}
	
	
	// addCount 만큼 플레이어의 최대 인구수 증가 이거를 섬을 점령할 때마다 증가하는 식으로 해야 할듯
	public void addMaxPopulationCount(int addCount) {
		this.maxPopulationCount += addCount;
		System.out.println("최대 인구수: " + this.maxPopulationCount);
		System.out.println();
	}
	
	// addCount 만큼 플레이어의 현재 인구수 증가(초당 증가)
	public void addNowPopulationCount(int addCount) {
		this.nowPopulationCount += addCount;
	}
	
	//생산할 때 한번에 처리할 것인가 따로 할것인가?? 따로 하는게 나을것 같음 겹치면 동시에 올라가게 되는 경우도 있음
}
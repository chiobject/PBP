package field;

public abstract class field {
	public int type;
	public String name;
	public int p[] = new int[3]; // [0] 종족 , [1] 유닛 종류 [2] 유닛 수
	
	field(int type, String name){
		this.type = type;
		this.name = name;
	}
	
	void setUnit(int player, int p[]) {
		
	}
	
	void setType(int type) {
		this.type = type;
	}
	
	public abstract void button1();
	public abstract void button2();
	public abstract void button3();
	public abstract void button4();
	
}

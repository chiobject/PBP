package field;

public abstract class field {
	public int type;
	public String name;
	public int p1[] = new int[2];
	public int p2[] = new int[2];	// [0] 종족 , [1] 유닛 수
	
	field(int type, String name){
		this.type = type;
		this.name = name;
	}
	
	void setunit(int player, int p[]) {
		if(player == 1) {
			p1 = p;
		}
		else if(player == 2) {
			p2 = p;
		}
	}
	
	void unitCount(int player, int a){
		if(player == 1) {
			
		}
	}
	
	
	
}

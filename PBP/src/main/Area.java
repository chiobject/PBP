package main;

public abstract class Area {
	String area;
	
	Area(String area) {
		this.area = area;
	}
	
	Area(){
		this("Plain");
	}
	
	boolean isArea(String x){
		return area == x;
	}
	
	void setArea(String area) {
		this.area = area;
	}
}

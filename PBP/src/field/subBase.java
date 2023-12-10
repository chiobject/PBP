package field;

public class subBase extends field {
	public subBase(){
		super(2,"서브",3);
		buttonName1 = "소환하기";
	}

	@Override
	public void button1() {
		// TODO Auto-generated method stub
		unitSummon(0);
		System.out.println(unitCount);
	}

	@Override
	public void button2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void button3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void button4() {
		// TODO Auto-generated method stub
		
	}
}

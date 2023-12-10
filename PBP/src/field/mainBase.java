package field;

import main.gameGUI;
import unit.unit;

public class mainBase extends field {
	public mainBase() {
		super(1, "기지", 4);
		buttonName1 = "소환하기";
		
	}

	@Override
	public void button1() {
		// TODO Auto-generated method stub
		unitSummon(2);
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

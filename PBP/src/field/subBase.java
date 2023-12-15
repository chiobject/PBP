package field;

import main.gameGUI;

public class subBase extends field {
	public subBase(){
		super(2,"서브",3);
		buttonName1 = "소환하기";
	}

	@Override
	public void button1() {
		// TODO Auto-generated method stub
		gameGUI.getMainCanvas().getSelectField().setFieldActivate(false);
		gameGUI.getMainCanvas().getSelectField().setDirActivate(true);
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

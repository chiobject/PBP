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
		if(gameGUI.getMap().field[gameGUI.getMainCanvas().getSelect().x]
				[gameGUI.getMainCanvas().getSelect().y].getFieldOwner() != 0) {
			unit unit = new unit(gameGUI.getMap().field[gameGUI.getMainCanvas().getSelect().x]
					[gameGUI.getMainCanvas().getSelect().y].getUnitType());
			unit.setPosition(gameGUI.getMainCanvas().getFieldSelect().x,gameGUI.getMainCanvas().getFieldSelect().y);
			unit.setAttack(50);
			unit.setHp(2000);
			unit.speed = 1;
			unit.unitmove(1);
			unit.start();
			
			// mainCanvas에 unit 추가
            gameGUI.getMainCanvas().addUnit(unit);
		}
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

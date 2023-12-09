package field;

import main.gameGUI;
import unit.unit;

public abstract class field implements Runnable {
	public int type;
	public String name;
	private int buttonCount;
	protected String buttonName1, buttonName2, buttonName3, buttonName4;
	private int fieldOwner;
	public int unitCount = 5000;
	private boolean running = false;
	private Thread worker;
	private int unitType;
	private int unitMax = 5000;
	

	field(int type, String name, int buttonCount) {
		this.type = type;
		this.name = name;
		this.buttonCount = buttonCount;
	}

	void setUnit(int player, int p[]) {

	}

	void setType(int type) {
		this.type = type;
	}

	public void start() {
		if (!running) {
			running = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		while (running) {
			try {
				unitproduction();
				worker.sleep(1000); // 원하는 갱신 주기로 조절

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract void button1();

	public abstract void button2();

	public abstract void button3();

	public abstract void button4();

	public int getbuttonCount() {
		return buttonCount;
	}

	public String getButtonName(int number) {
		if (number == 1) {
			return buttonName1;
		} else if (number == 2) {
			return buttonName2;
		} else if (number == 3) {
			return buttonName3;
		} else if (number == 4) {
			return buttonName4;
		} else {
			return "None";
		}
	}

	public int getFieldOwner() {
		return fieldOwner;
	}

	public void setFieldOwner(int fieldOwner) {
		this.fieldOwner = fieldOwner;
	}

	private void unitproduction() {
		if (type != 0) {
			if (unitMax > unitCount) {
				unitCount += 5;
				System.out.println(unitCount);
			}
			if (unitMax <= unitCount) {
				unitCount = unitMax;
				System.out.println(unitCount);
			}
		}
	}

	public int getUnitType() {
		return unitType;
	}

	public void unitSummon() {
		if (unitCount > 50) {
			if (gameGUI.getMap().field[gameGUI.getMainCanvas().getSelect().x][gameGUI.getMainCanvas().getSelect().y]
					.getFieldOwner() != 0) {
				unit unit = new unit(gameGUI.getMap().field[gameGUI.getMainCanvas().getSelect().x][gameGUI
						.getMainCanvas().getSelect().y].getUnitType());
				unit.setPosition(gameGUI.getMainCanvas().getFieldSelect().x,
						gameGUI.getMainCanvas().getFieldSelect().y);
				unit.setAttack(50);
				unitCount -= 50;
				unit.setHp(2000);
				unit.speed = 1;
				unit.unitmove(1);
				unit.start();

				// mainCanvas에 unit 추가
				gameGUI.getMainCanvas().addUnit(unit);
			}
		}
		else{
			System.out.println("유닛 수 부족");
		}
	}
	public void info() {
//		gameGUI.getSubCanvas().getButton(1).visible=
	}
	
}

package field;

import main.gameGUI;
import unit.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class field implements Runnable {
	public int type;
	public String name;
	private int buttonCount;
	protected String buttonName1 = "";
	protected String buttonName2 = "";
	protected String buttonName3 = ""; 
	protected String buttonName4 = "";
	private int Owner;
	public int unitCount = 5000;
	private boolean running = false;
	private Thread worker;
	private int unitType;
	private int unitMax = 5000;
	private boolean summonCooldown = false;
	private boolean fieldActivate = true;
	private boolean dirActivate = false;

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

	private void unitproduction() {
		if (type != 0) {
			if (unitMax > unitCount) {
				unitCount += 5;
			}
			if (unitMax <= unitCount) {
				unitCount = unitMax;
			}
		}
	}

	public int getUnitType() {
		return unitType;
	}

	public void startSummonCooldown() {
		summonCooldown = true;
		Timer cooldownTimer = new Timer(10000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				summonCooldown = false;
				((Timer) e.getSource()).stop();
			}
		});
		cooldownTimer.setRepeats(false); // 한 번만 실행되도록 설정
		cooldownTimer.start();
	}
	
	public boolean getFieldActivate() {
		return fieldActivate;
	}

	public boolean getDirActivate() {
		return dirActivate;
	}

	
	public void setFieldActivate(boolean fieldActivate) {
		this.fieldActivate = fieldActivate;
	}

	public void setDirActivate(boolean dirActivate) {
		this.dirActivate = dirActivate;
	}


	public void info() {
//		gameGUI.getSubCanvas().getButton(1).visible=
	}

	public int getOwner() {
		return Owner;
	}
	
	public int getUnitCount() {
		return unitCount;
	}

	public void setOwner(int Owner) {
		this.Owner = Owner;
	}
	public boolean getsummonCooldown() {
		return summonCooldown;
	}
}

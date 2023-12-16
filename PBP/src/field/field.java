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
	private int owner;
	public int unitCount = 0;
	private boolean running = false;
	private Thread worker;
	private int unitType;
	private int unitMax = 5000;
	private boolean summonCooldown = false;
	private boolean fieldActivate = true;
	private boolean dirActivate = false;
	private boolean isProduction = true;
	private boolean isBuilding = false;
	public int buildingTime = 0;

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
				worker.sleep(gameGUI.getData().getPlayer(owner).getBrood().getpopProdSpeed()); // 원하는 갱신 주기로 조절

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
		if (owner != 0 && isProduction == true) {
			if (unitMax > unitCount) {
				unitCount += gameGUI.getData().getPlayer(owner).getBrood().getpopProdRate();
				if(unitCount >= unitMax) {
					unitCount = unitMax;
				}
			}
		}
	}

	public int getUnitType() {
		return unitType;
	}
	
	public int getType() {
		return type;
	}

	public void startSummonCooldown() {
		summonCooldown = true;
		Timer cooldownTimer = new Timer(5000, new ActionListener() {
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

	public int getOwner() {
		return owner;
	}
	
	public int getUnitCount() {
		return unitCount;
	}
	
	public int getUnitMax() {
		return unitMax;
	}
	
	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}
	
	public void setIsBuilding(boolean isBuilding) {
		this.isBuilding = isBuilding;
	}
	
	public boolean getIsBuilding() {
		return isBuilding;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public boolean getsummonCooldown() {
		return summonCooldown;
	}
	public void changeUnitCount(int unitCount) {
		this.unitCount += unitCount;
	}
	public void setIsProduction(boolean isProduction) {
		this.isProduction = isProduction;
	}
	public int getBuildingTime() {
		return buildingTime;
	}
	public void setbuildingTime(int buildingTime) {
		this.buildingTime = buildingTime;
	}
	public void changeBuildingTime(int buildingTime) {
		this.buildingTime += buildingTime;
	}
	
	public void unitSummon(){
		if(gameGUI.getMainCanvas().getSelectField().getIsBuilding() ==false) {
			gameGUI.getMainCanvas().getSelectField().setFieldActivate(false);
			gameGUI.getMainCanvas().getSelectField().setDirActivate(true);
		}
		else {
			System.out.println("지금은 유닛을 소환할 수 없습니다.");
		}
	}
}

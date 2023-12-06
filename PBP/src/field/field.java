package field;

import main.gameGUI;

public abstract class field implements Runnable {
	public int type;
	public String name;
	private int buttonCount;
	protected String buttonName1, buttonName2, buttonName3, buttonName4;
	private int fieldOwner;
	private boolean running = false;
	private Thread worker;
	private int unitType;
	
	field(int type, String name, int buttonCount){
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
		if(number==1) {return buttonName1;}
		else if(number==2) {return buttonName2;}
		else if(number==3) {return buttonName3;}
		else if(number==4) {return buttonName4;}
		else {return "None";}
	}
	
	public int getFieldOwner() {
		return fieldOwner;
	}
	
	public void setFieldOwner(int fieldOwner) {
		this.fieldOwner = fieldOwner;
	}
	
	private void unitproduction() {
    	for (int i = 0; i < gameGUI.getMap().max_x; i++) {
            for (int j = 0; j < gameGUI.getMap().max_y; j++) {
                if(fieldOwner !=0) {
                	
                }
            }
        }
    }
	
	public int getUnitType() {
		return unitType;
	}
}

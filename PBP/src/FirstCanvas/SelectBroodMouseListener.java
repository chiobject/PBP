package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import main.gameGUI;


public class SelectBroodMouseListener extends MouseAdapter implements MouseMotionListener {
	SelectBrood selectBrood;
	private boolean allowMouseInput = true; // 마우스 입력 허용 여부

	public SelectBroodMouseListener(SelectBrood selectBrood) {
		this.selectBrood = selectBrood;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!allowMouseInput) {
			return; // 마우스 입력 비허용 상태일 때는 아무 동작도 수행하지 않음
		}
		int mouseX = e.getX();
		int mouseY = e.getY();
		handleMouseClick(mouseX, mouseY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!allowMouseInput) {
			return; // 마우스 입력 비허용 상태일 때는 아무 동작도 수행하지 않음
		}
		int mouseX = e.getX();
		int mouseY = e.getY();
		handleMouseMovement(mouseX, mouseY);
		selectBrood.repaint();
	}

	private void handleMouseClick(int mouseX, int mouseY) {
		handleMouseMovement(mouseX, mouseY);
		performAction();
	}

	private void handleMouseMovement(int mouseX, int mouseY) {
		if (mouseX >= 70 && mouseX <= 220) {
			if (mouseY >= 160 && mouseY <= 240) {
				selectBrood.chose = 0;
			} else if (mouseY >= 260 && mouseY <= 340) {
				selectBrood.chose = 1;
			} else if (mouseY >= 360 && mouseY <= 440) {
				selectBrood.chose = 2;
			}
		} else if (mouseX >= 1080 && mouseX <= 1280) {
			if (mouseX >= 1050 && mouseX <= 1250) {
				if (mouseY >= 720 && mouseY <= 770) {
					selectBrood.chose = 3;
				} else if (mouseY >= 200 && mouseY <= 280) {
					selectBrood.chose = 5;
				}
			}
		}
		selectBrood.repaint();
	}

	private void performAction() {
		if (selectBrood.chose >= 0 && selectBrood.chose <= 2) {
			// 클릭 시 캐릭터 선택 정보 확정, SendData 클래스를 통해 서버로 전송
			selectBrood.selectedBroodName = selectBrood.broodNames[selectBrood.chose];
			Connect_Create_ServerMouseListener.getOriginalCleint().brood = String.valueOf(selectBrood.chose+1);

		} else if (selectBrood.chose == 3) {
			selectBrood.BackIntro();
		} else if (selectBrood.chose == 5) {
			
		        Timer timer = new Timer();
		        timer.scheduleAtFixedRate(new TimerTask() {
		            @Override
		            public void run() {
		            	Connect_Create_ServerMouseListener.originalrefresh();
		            	if (selectBrood.chose != 5) {
		                    timer.cancel(); // 타이머 중지
		                }
		            }
		        }, 0, 60);

			// ready 버튼 클릭 시 상태 변경
			selectBrood.isReady = !selectBrood.isReady;
			Connect_Create_ServerMouseListener.getOriginalCleint().ready = "true";
			
			// 상대방에게 레디 상태를 전송하는 코드가 있다면 여기에 추가
			
			// 예: sendOpponentReadyStatus(selectBrood.isReady);
		} 
		selectBrood.repaint();
	}

}

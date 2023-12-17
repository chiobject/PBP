package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
        if (mouseX >= 50 && mouseX <= 200) {
            if (mouseY >= 160 && mouseY <= 240) {
                selectBrood.chose = 0;
            } else if (mouseY >= 260 && mouseY <= 340) {
                selectBrood.chose = 1;
            } else if (mouseY >= 360 && mouseY <= 460) {
                selectBrood.chose = 2;
            }
        } else if (mouseX >= 1000 && mouseX <= 1200) {
            if (mouseY >= 650 && mouseY <= 800) {
                selectBrood.chose = 3;
            }
        } 
        selectBrood.repaint();
    }

    private void performAction() {
        if (selectBrood.chose >= 0 && selectBrood.chose <= 2) {
            // 클릭 시 캐릭터 선택 정보 확정, SendData 클래스를 통해 서버로 전송
            System.out.println("캐릭터 선택 정보: " + selectBrood.chose);

            // 서버 정보
            String serverHost = "127.0.0.1"; // 서버 호스트 주소
            int serverPort = 0302; // 서버 포트

            // SendData 객체 생성 및 데이터 전송
            SendData sendData = new SendData(selectBrood.chose);
            sendData.sendDataToServer(serverHost, serverPort);
        } else if (selectBrood.chose == 3) {
            selectBrood.BackIntro();
        }
    }
    
}
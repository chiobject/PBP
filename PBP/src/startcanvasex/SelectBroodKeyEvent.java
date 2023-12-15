package startcanvasex;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SelectBroodKeyEvent implements KeyListener {
    private SelectBrood selectBrood;
    private boolean allowKeyInput = true; // 키 입력 허용 여부

    public SelectBroodKeyEvent(SelectBrood selectBrood) {
        this.selectBrood = selectBrood;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!allowKeyInput) {
            return; // 키 입력 비허용 상태일 때는 아무 동작도 수행하지 않음
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            selectBrood.chose = (selectBrood.chose - 1 + 4) % 4; // 이전 선택지로 이동
            System.out.println("새로운 chose 값: " + selectBrood.chose);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            selectBrood.chose = (selectBrood.chose + 1) % 4; // 다음 선택지로 이동
            System.out.println("새로운 chose 값: " + selectBrood.chose);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 엔터 키를 누를 때 캐릭터 선택 정보 확정
            if (selectBrood.chose >= 0 && selectBrood.chose <= 2) {
                // 여기서 서버로 캐릭터 선택 정보를 보내는 로직을 추가할 수 있습니다.
                System.out.println("캐릭터 선택 정보: " + selectBrood.chose);
                allowKeyInput = false; // 키 입력 비허용 상태로 전환
            } else if (selectBrood.chose == 3) {
                selectBrood.BackIntro();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // esc 키를 누를 때 다시 선택 가능하도록
            selectBrood.chose = 0;
            allowKeyInput = true; // 키 입력 허용 상태로 전환
        }

        // 패널을 다시 그려준다.
        selectBrood.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 필요에 따라 구현
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 필요에 따라 구현
    }
}

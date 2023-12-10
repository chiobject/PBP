package startcanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class IntroKeyEvent implements KeyListener{
	IntroPanel introPanel; //패널을 생성자로 받음.
	public IntroKeyEvent(IntroPanel introPanel) {
		this.introPanel = introPanel;
	}
	@Override
	public void keyPressed(KeyEvent e) {
	    // 키값이 38 즉, 방향키 위쪽이면
	    if (e.getKeyCode() == 38) {
	        introPanel.select = (introPanel.select - 1 + 3) % 3; // 이전 선택지로 이동
	        System.out.println("헉: " + introPanel.select);
	    } else if (e.getKeyCode() == 40) { // 키값이 40 즉, 방향키 아래쪽이면
	        introPanel.select = (introPanel.select + 1) % 3; // 다음 선택지로 이동
	        System.out.println("헉 " + introPanel.select);
	    } else if (e.getKeyCode() == 10) { // 엔터 키
	        // select 값에 따라 다른 동작 수행
	        if (introPanel.select == 0) {
	            introPanel.gameStart();
	        } else if (introPanel.select == 1) {
	            introPanel.How_To_Play();
	        } else if (introPanel.select == 2) {
	            System.exit(0);
	        }
	    }
		//패널을 다시 그려준다.
		introPanel.repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

}

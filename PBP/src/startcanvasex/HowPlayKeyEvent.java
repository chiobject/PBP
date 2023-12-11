package startcanvasex;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HowPlayKeyEvent implements KeyListener {
	 private HowPlay HowPlay;

	 public HowPlayKeyEvent(HowPlay HowPlay) {
	  this.HowPlay = HowPlay;
	 }

	 @Override
	 public void keyPressed(KeyEvent e) {

		    if (e.getKeyCode() == 39) {
		    	HowPlay.chose1 = (HowPlay.chose1 - 1+3) % 3; // 이전 선택지로 이동
		    	System.out.println("새로운 chose 값: " + HowPlay.chose1);
		    } else if (e.getKeyCode() == 37) { // 키값이 40 즉, 방향키 아래쪽이면
		    	HowPlay.chose1 = (HowPlay.chose1 + 1) % 3; // 다음 선택지로 이동
		    	System.out.println("새로운 chose 값: " + HowPlay.chose1);
		    	
		    } else if (e.getKeyCode() == 10) { // 엔터 키
		        // select 값에 따라 다른 동작 수행
		        if (HowPlay.chose1 == 0) {
		        	HowPlay.next();
		        } else if (HowPlay.chose1 == 1) {
		        	HowPlay.previous();
		        } else if (HowPlay.chose1 == 2) {
		        	HowPlay.BackInformation();
		    }
		    }

	   // 패널을 다시 그려준다.
		    HowPlay.repaint();
		    
	  
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
package FirstCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class A_WarKeyEvent implements KeyListener {
	 private A_War A_War;

	 public A_WarKeyEvent(A_War A_War) {
	  this.A_War = A_War;
	 }

	 @Override
	 public void keyPressed(KeyEvent e) {

		    if (e.getKeyCode() == 39) {
		    	A_War.chose3 = (A_War.chose3 - 1+3) % 3; // 이전 선택지로 이동
		    	System.out.println("새로운 chose 값: " + A_War.chose3);
		    } else if (e.getKeyCode() == 37) { // 키값이 40 즉, 방향키 아래쪽이면
		    	A_War.chose3 = (A_War.chose3 + 1) % 3; // 다음 선택지로 이동
		    	System.out.println("새로운 chose 값: " + A_War.chose3);
		    	
		    } else if (e.getKeyCode() == 10) { // 엔터 키
		        // select 값에 따라 다른 동작 수행
		        if (A_War.chose3 == 0) {
		        	A_War.next();
		        } else if (A_War.chose3 == 1) {
		        	A_War.previous();
		        } else if (A_War.chose3 == 2) {
		        	A_War.BackInformation();
		    }
		    }

	   // 패널을 다시 그려준다.
		    A_War.repaint();
		    
	  
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
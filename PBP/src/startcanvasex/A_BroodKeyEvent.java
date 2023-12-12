package startcanvasex;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class A_BroodKeyEvent implements KeyListener {
	 private A_Brood A_Brood;

	 public A_BroodKeyEvent(A_Brood A_Brood) {
	  this.A_Brood = A_Brood;
	 }

	 @Override
	 public void keyPressed(KeyEvent e) {

		    if (e.getKeyCode() == 39) {
		    	A_Brood.chose2 = (A_Brood.chose2 - 1+3) % 3; // 이전 선택지로 이동
		    	System.out.println("새로운 chose 값: " + A_Brood.chose2);
		    } else if (e.getKeyCode() == 37) { // 키값이 40 즉, 방향키 아래쪽이면
		    	A_Brood.chose2 = (A_Brood.chose2 + 1) % 3; // 다음 선택지로 이동
		    	System.out.println("새로운 chose 값: " + A_Brood.chose2);
		    	
		    } else if (e.getKeyCode() == 10) { // 엔터 키
		        // select 값에 따라 다른 동작 수행
		        if (A_Brood.chose2 == 0) {
		        	A_Brood.next();
		        } else if (A_Brood.chose2 == 1) {
		        	A_Brood.previous();
		        } else if (A_Brood.chose2 == 2) {
		        	A_Brood.BackInformation();
		    }
		    }

	   // 패널을 다시 그려준다.
		    A_Brood.repaint();
		    
	  
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
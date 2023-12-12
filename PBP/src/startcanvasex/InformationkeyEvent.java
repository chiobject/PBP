package startcanvasex;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InformationkeyEvent implements KeyListener {
	 private Information Information;

	 public InformationkeyEvent(Information Information) {
	  this.Information = Information;
	 }

	 @Override
	 public void keyPressed(KeyEvent e) {

		    if (e.getKeyCode() == 38) {
		    	Information.chose = (Information.chose - 1+4) % 4; // 이전 선택지로 이동
		    	System.out.println("새로운 chose 값: " + Information.chose);
		    } else if (e.getKeyCode() == 40) { // 키값이 40 즉, 방향키 아래쪽이면
		    	Information.chose = (Information.chose + 1) % 4; // 다음 선택지로 이동
		    	System.out.println("새로운 chose 값: " + Information.chose);
		    	
		    } else if (e.getKeyCode() == 10) { // 엔터 키
		        // select 값에 따라 다른 동작 수행
		        if (Information.chose == 0) {
		        	Information.howtoplay();
		        } else if (Information.chose == 1) {
		        	Information.Brood();
		        } else if (Information.chose == 2) {
		        	Information.War();
		        }else if (Information.chose ==3) {
		        	Information.goBack();
		        }
		    }

	   // 패널을 다시 그려준다.
		    Information.repaint();
		    
	  
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
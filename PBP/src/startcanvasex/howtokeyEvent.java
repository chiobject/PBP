package startcanvasex;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class howtokeyEvent implements KeyListener {
	 private howToPlay howPlay;

	 public howtokeyEvent(howToPlay howToPlay) {
	  this.howPlay = howToPlay;
	 }

	 @Override
	 public void keyPressed(KeyEvent e) {

		    if (e.getKeyCode() == 38) {
		    	howPlay.chose = (howPlay.chose - 1+4) % 4; // 이전 선택지로 이동
		    	System.out.println("새로운 chose 값: " + howPlay.chose);
		    } else if (e.getKeyCode() == 40) { // 키값이 40 즉, 방향키 아래쪽이면
		    	howPlay.chose = (howPlay.chose + 1) % 4; // 다음 선택지로 이동
		    	System.out.println("새로운 chose 값: " + howPlay.chose);
		    	
		    } else if (e.getKeyCode() == 10) { // 엔터 키
		        // select 값에 따라 다른 동작 수행
		        if (howPlay.chose == 0) {
		        //	howtoplay.gameStart();
		        } else if (howPlay.chose == 1) {
		        //	howtoplay.How_To_Play();
		        } else if (howPlay.chose == 2) {
		        //    System.exit(0);
		        }else if (howPlay.chose ==3) {
		        	howPlay.goBack();
		        }
		    }

	   // 패널을 다시 그려준다.
		    howPlay.repaint();
		    
	  
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
package FirstCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Connect_Create_ServerKeyEvent implements KeyListener {
	 private Connect_Create_Server Connect_Create_Server;

	 public Connect_Create_ServerKeyEvent(Connect_Create_Server Connect_Create_Server) {
	  this.Connect_Create_Server = Connect_Create_Server;
	 }

	 @Override
	 public void keyPressed(KeyEvent e) {

		    if (e.getKeyCode() == 38) {
		    	Connect_Create_Server.chose = (Connect_Create_Server.chose - 1+4) % 4; // 이전 선택지로 이동
		    	System.out.println("새로운 chose 값: " + Connect_Create_Server.chose);
		    } else if (e.getKeyCode() == 40) { // 키값이 40 즉, 방향키 아래쪽이면
		    	Connect_Create_Server.chose = (Connect_Create_Server.chose + 1) % 4; // 다음 선택지로 이동
		    	System.out.println("새로운 chose 값: " + Connect_Create_Server.chose);
		    	
		    } else if (e.getKeyCode() == 10) { // 엔터 키
		        // select 값에 따라 다른 동작 수행
		        if (Connect_Create_Server.chose == 0) {
		        	Connect_Create_Server.showServer();
		        } else if (Connect_Create_Server.chose == 1) {
		        	Connect_Create_Server.showLogin();
		        } else if (Connect_Create_Server.chose == 2) {
		        	Connect_Create_Server.gameStart();
		        }else if (Connect_Create_Server.chose ==3) {
		        	Connect_Create_Server.BackIntro();
		        }
		    }

	   // 패널을 다시 그려준다.
		    Connect_Create_Server.repaint();
		    
	  
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
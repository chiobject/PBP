package startcanvasex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import server.ClientDialog;
import server.ServerDialog;


import javax.swing.JPanel;

public class Connect_Create_Server extends JPanel {
    int chose = 0;
    Image intro_image;
    viewController controller;
    int currentPage = 0;  // 현재 페이지를 나타내는 변수

    public Connect_Create_Server(viewController controller) {
        this.controller = controller;
        
        intro_image = Toolkit.getDefaultToolkit().getImage("images/main.png");

        // 패널이 포커스를 받을 수 있도록 설정
        setFocusable(true);

        // KeyListener를 패널에 추가
        addKeyListener(new Connect_Create_ServerKeyEvent(this));
        Connect_Create_ServerMouseListener mouseListener = new Connect_Create_ServerMouseListener(this);
        this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
    }

	public void paint(Graphics g) {
		//이미지를 그려준다. 이미지객체,시작x,시작y,가로,세로,보여줄 옵저버;
		g.drawImage(intro_image,0,0,1310,780,this);
		//g객체에 폰트스타일을 바꾸어준다. 이코드에서는 굵은글씨에 30포인트
		g.setFont(new Font("myFont",Font.BOLD ,30));
//		//폰트색을 빨강으로 변경
		g.setColor(Color.red);
//		//문자열 삽입
		g.drawString("Back", 1200, 700);
		//폰트변경후 문자열 삽입.
		g.setFont(new Font("secondFont",Font.PLAIN,60));
		g.setColor(Color.WHITE);
		g.drawString("Create Server",460,450);
		g.drawString("Connect Server", 450, 585);
		g.drawString("Select Brood",460,710);
		//기본값 게임시작바로 옆에 화살표를 위치시킨다
		if(chose ==0)
			g.drawString("→", 400, 450);
		else if(chose == 1)
			g.drawString("→", 400, 585);
		else if(chose == 2)
			g.drawString("→", 400, 710);
		else
			g.drawString("→", 1150, 700);

	}

    public void BackIntro() {
        controller.goBack(this);
    }
    
    public void showClientDialog() {
        ClientDialog clientDialog = new ClientDialog();
        clientDialog.setVisible(true);
    }
    public void showServerDialog() {
    	ServerDialog serverDialog = new ServerDialog();
    	serverDialog.setVisible(true);
    }
    
	public void gameStart(){
		controller.showCCServer();
	}
    
    
}

package startcanvasex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;


//Panel단위로 화면이동을 하기위해 각화면은 Panel을 
//상속받아 클래스를 작성한다
public class IntroPanel extends JPanel{
	int select =0;
	Image intro_image;
	viewController controller;
	public IntroPanel(viewController controller) {
		this.controller = controller; //<-- 받은 생성자를 여기에서 controller 라는 변수로 사용
		//기본툴킷 객체로부터 getImage메소드를 활용하여 이미지를 불러온다.
		intro_image = Toolkit.getDefaultToolkit().getImage("images/main.png");
		this.addKeyListener(new IntroKeyEvent(this));
		IntroMouseListener mouseListener = new IntroMouseListener(this);
		this.addMouseMotionListener(mouseListener);
		this.addMouseListener(mouseListener);
	}	
	//패널의 paint 메소드를 오버라이드 하여 그려줌
	//메소드가 실행될때 Graphics타입의 g를 인자로 받는다
	//g를 이용하여 패널에 그려준다.
	@Override
	public void paint(Graphics g) {
		//이미지를 그려준다. 이미지객체,시작x,시작y,가로,세로,보여줄 옵저버;
		g.drawImage(intro_image,0,0,1310,780,this);
		//g객체에 폰트스타일을 바꾸어준다. 이코드에서는 굵은글씨에 30포인트
//		g.setFont(new Font("myFont",Font.BOLD ,30));
//		//폰트색을 빨강으로 변경
//		g.setColor(Color.red);
//		//문자열 삽입
//		g.drawString("청계부족전쟁", 120, 100);
		//폰트변경후 문자열 삽입.
		g.setFont(new Font("secondFont",Font.PLAIN,60));
		g.setColor(Color.WHITE);
		g.drawString("GameStart",500,450);
		g.drawString("Information", 500, 585);
		g.drawString("Quit",590,710);
		//기본값 게임시작바로 옆에 화살표를 위치시킨다
		if(select ==0)
			g.drawString("→", 450, 450);
		else if(select == 1)
			g.drawString("→", 450, 585);
		else 
			g.drawString("→", 540, 710);

	}
	
	public void gameStart(){
		controller.showGamePanel();
	}
	
	public void How_To_Play() {
		controller.showInformation();
	}

}

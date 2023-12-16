package FirstCanvas;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	viewController controller;
	//생성자로 프레임의 이름을 받아 그대로 부모클래스로 전달해준다.
	public MainFrame(String title) {
		super(title);
		//윈도우상의 창의 위치를 잡아준다.
		this.setLocation(new Point(700,350));
		//프레임을 보여준다. default = false
		this.setVisible(true);
		//프레임의 창크기를 설정한다.
		this.setPreferredSize(new Dimension(1310,800));
		//컴포넌트 크기만큼 창의 크기를 잡아준다.
		this.pack();
		//프레임창을 닫을 경우 프로세스를 종료하기 위해 사용해야한다. 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//사진
		this.setResizable(false);
	//	this.getContentPane().add(new IntroPanel(controller));
		//뷰컨트롤러
		controller = new viewController(this);


	}
	//매인클래스.
	public static void main(String args[]){
		//창을 띄우려면 객체생성 해야 하므로 자기자신클래스를 생성
		MainFrame mainFrame = new MainFrame("Brood War");
	}

}
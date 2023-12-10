package startcanvas;

import java.awt.Container;
public class viewController {
	MainFrame mainFrame;  
	Container contentPane; 
	IntroPanel introPanel; 
	howToPlay howPlay;
	viewController controller;
	public viewController(MainFrame mainFrame) {
		this.mainFrame = mainFrame; //생성자로 매인프레임을 받는다
		init();
	}
	private void init(){
		introPanel = new IntroPanel(this);
		contentPane = mainFrame.getContentPane(); //매인프레임으로부터 컨텐트페인을 가져온다
		contentPane.add(introPanel); //컨텐트패인에 시작패널 추가
		introPanel.requestFocus();   //키이벤트를 받기위해 패널을 포커싱해준다.(패널에 이벤트가 달려있기때문에)
	}
	
	public void showGamePanel(){
		contentPane.remove(introPanel); //introPanel을 제거한다
		contentPane.add(new GamePanel()); //새로운 Panel을 추가한다.
		mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		mainFrame.setVisible(true); //다시 보이게 한다.
		System.out.println("헉");
	}

	public void showHowPlay() {
		 contentPane.remove(introPanel);
		 howPlay = new howToPlay(controller);
		 howPlay.addKeyListener(new howtokeyEvent(howPlay));
		 contentPane.add(howPlay);
		 mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		 mainFrame.setVisible(true);
		 System.out.println("헉");
	}
	
	public void goBack() {
	    contentPane.remove(howPlay);
	    contentPane.add(introPanel);
	    mainFrame.setVisible(false);
	    mainFrame.setVisible(true);
	    introPanel.requestFocus(); // IntroPanel로 돌아갈 때 포커스를 설정
	}

}

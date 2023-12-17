package FirstCanvas;

import java.awt.Container;
import javax.swing.JPanel; 

public class viewController {
	MainFrame mainFrame;  
	Container contentPane; 
	IntroPanel introPanel; 
	Information Information;
	HowPlay HowPlay;
	viewController controller;
	A_Brood A_Brood;
	A_War A_War;
	Connect_Create_Server Connect_Create_Server;
	SelectBrood SelectBrood;
	
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

	public void showInformation() {
		 contentPane.remove(introPanel);
		 Information = new Information(this);
		// howPlay.addKeyListener(new howtokeyEvent(howPlay));
		 contentPane.add(Information);
		 mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		 mainFrame.setVisible(true);
		 System.out.println("헉");
	}
	
	public void showHowPlay() {
		HowPlay = new HowPlay(this);
		contentPane.remove(Information);
		contentPane.add(HowPlay);
		
		mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		mainFrame.setVisible(true);
	}
	
	public void showA_Brood() {
		A_Brood = new A_Brood(this);
		contentPane.remove(Information);
		contentPane.add(A_Brood);
		
		mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		mainFrame.setVisible(true);
	}
	
	
	public void showA_War() {
		A_War = new A_War(this);
		contentPane.remove(Information);
		contentPane.add(A_War);
		
		mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		mainFrame.setVisible(true);
	}
	
	public void showCCServer() {
		 contentPane.remove(introPanel);
		 Connect_Create_Server = new Connect_Create_Server(this);
		// howPlay.addKeyListener(new howtokeyEvent(howPlay));
		 contentPane.add(Connect_Create_Server);
		 mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		 mainFrame.setVisible(true);
		 System.out.println("헉");
	}
	
	public void selectBrood() {
		 contentPane.remove(Connect_Create_Server);
		 SelectBrood = new SelectBrood(this);
		// howPlay.addKeyListener(new howtokeyEvent(howPlay));
		 contentPane.add(SelectBrood);
		 mainFrame.setVisible(false); //메인프레임을 보이지 않게한 후 
		 mainFrame.setVisible(true);
		 System.out.println("헉");
	}
	
	public void goBack(JPanel panelToRemove) {
	    contentPane.remove(panelToRemove);
	    contentPane.add(introPanel);
	    mainFrame.setVisible(false);
	    mainFrame.setVisible(true);
	    introPanel.requestFocus(); // IntroPanel로 돌아갈 때 포커스를 설정
	    System.out.println("헉");
	}
	
	public void BackInformation(JPanel panelToRemove) {
	    contentPane.remove(panelToRemove);
	    contentPane.add(Information);
	    mainFrame.setVisible(false);
	    mainFrame.setVisible(true);
	    Information.requestFocus();
	    System.out.println("헉");
	}
	

}

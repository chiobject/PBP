package FirstCanvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;

public class SelectBrood extends JPanel {    
	int chose = 0;
	Image intro_image;
	Image[] buttonImages;
	Image[]	Unit;
	viewController controller;
	int currentPage = 0;  // 현재 페이지를 나타내는 변수
	String[] broodNames = {"Human", "Elf", "Goblin"};
	String selectedBroodName = "";
    boolean isReady = false; // 레디 여부 플래그
    boolean opponentReady = false; // 상대방 레디 여부 플래그
    boolean gameStarted = false; // 게임 시작 여부 플래그
    static int secondsLeft = 10;
	
	
	public SelectBrood(viewController controller) {
	    this.controller = controller;
	    
	    intro_image = Toolkit.getDefaultToolkit().getImage("images/explain.png");
	    buttonImages = new Image[3];
        buttonImages[0] = Toolkit.getDefaultToolkit().getImage("images/button.png");
        buttonImages[1] = Toolkit.getDefaultToolkit().getImage("images/button.png");
        buttonImages[2] = Toolkit.getDefaultToolkit().getImage("images/button.png");
        
        Unit = new Image[3];
        Unit[0] = Toolkit.getDefaultToolkit().getImage("unitImage/human.png");
        Unit[1] = Toolkit.getDefaultToolkit().getImage("unitImage/elf.png");
        Unit[2] = Toolkit.getDefaultToolkit().getImage("unitImage/goblin.png");
	
	    // 패널이 포커스를 받을 수 있도록 설정
	    setFocusable(true);
	
	    SelectBroodMouseListener mouseListener = new SelectBroodMouseListener(this);
	    this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
	}
	
	public void paint(Graphics g) {
	    // 이미지를 그려줌
	    g.drawImage(intro_image, 0, 0, 1310, 775, this);
        g.drawImage(buttonImages[0], 50, 165, 200, 50, this);
        g.drawImage(buttonImages[1], 50, 265, 200, 50, this);
        g.drawImage(buttonImages[2], 50, 365, 200, 50, this);
        drawInstructionsOverlay(g);
	    g.setFont(new Font("myFont", Font.BOLD, 40));
	    g.setColor(Color.BLACK);
	    g.drawString("choose your Brood", 50, 50);
	    g.setFont(new Font("secondFont", Font.ITALIC, 20));
	    g.setColor(Color.RED);
	    g.drawString("Return Main", 1100, 750);
	    g.setFont(new Font("thirdFont", Font.BOLD ,20));
	    g.setColor(Color.black);
	    g.drawString("Human", 100, 200);
	    g.drawString("Elf", 100, 300);
	    g.drawString("Goblin", 100, 400);
	    g.setFont(new Font("choseplayer", Font.BOLD ,30));
	    g.drawString("Ready", 1100,250);
	    g.drawString("Opponent", 1100, 300);
        if (!selectedBroodName.isEmpty()) {
            g.setFont(new Font("selectedBrood", Font.BOLD, 30));
            g.drawString("종족 : " + selectedBroodName, 1100, 450);
        }
        if (isReady) {
            g.setFont(new Font("readyStatus", Font.BOLD, 30));
            g.drawString("준비완료!", 1100, 500);
        }

	    // 선택한 메뉴에 따라 화살표를 그림
	    if (chose == 0)
	        g.drawString("←", 180, 200);
	    else if (chose == 1)
	        g.drawString("←", 180, 300);
	    else if (chose == 2)
	        g.drawString("←", 180, 400);
	    else if (chose == 3)
	    	g.drawString("→", 1060, 750);
	    else if (chose == 5)
	    	g.drawString("→", 1060, 250);

	    

	    g.setFont(new Font("instructionFont", Font.PLAIN, 18));
	    g.setColor(Color.WHITE);
	    if (chose == 0) {
	    	g.drawImage(Unit[0], 230, 70, 450, 400, this);
	    	g.drawString("인간 - 다재다능", 650, 200);
	    	g.drawString("공격력 : 60", 650, 250);
	    	g.drawString("생산력 : 50", 650, 300);
	    	g.drawString("이동력 : 150", 650, 350);

	    } else if (chose == 1) {
	        g.drawImage(Unit[1], 230, 70, 450, 400, this);
	    	g.drawString("엘프 - 신속", 650, 200);
	    	g.drawString("공격력 : 50 ", 650, 250);
	    	g.drawString("생산력 : 50", 650, 300);
	    	g.drawString("이동력 : 200", 650, 350);

	    }else if (chose == 2) {
	    	g.drawImage(Unit[2],230, 70, 450, 400, this);
	    	g.drawString("고블린 - 다산", 650, 200);
	 	    g.drawString("공격력 : 35", 650, 250);
	 	    g.drawString("생산력 : 80", 650, 300);
	 	    g.drawString("이동력 : 150 ", 650, 350);

	    }
	    		
	}	
	
	public void BackIntro() {
	    controller.goBack(this);
	}
	
	private void drawInstructionsOverlay(Graphics g) {
	    // 반투명한 회색 오버레이 그리기
	    Color overlayColor = new Color(128, 128, 128, 128);
	    g.setColor(overlayColor);
	    g.fillRect(300, 60, 700, 600);
	
	    // 추가적인 설명 텍스트 그리기...
	}
    public void setOpponentReady(boolean ready) {
        this.opponentReady = ready;
        repaint();
    }
    
    public static void startGameTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (secondsLeft > 0) {
                    secondsLeft--;
                } else {
                    System.out.println("게임 시작!");
                    timer.cancel(); // 타이머 종료
                    // 게임 시작에 필요한 추가 동작 수행
                }
            }
        }, 0, 1000); // 1초마다 실행
    }
	

//	public void next() {
//	    // 다음 페이지로 이동
//	    currentPage++;
//	    if (currentPage >= getMaxPages()) {
//	        currentPage = getMaxPages() - 1;
//	    }
//	    repaint(); // 화면 다시 그리기
//	}
//	
//	public void previous() {
//	    // 이전 페이지로 이동
//	    currentPage--;
//	    if (currentPage < 0) {
//	        currentPage = 0;
//	    }
//	    repaint(); // 화면 다시 그리기
//	}
//	
//	private int getMaxPages() {
//	    // 전체 페이지 수 반환 (최대 페이지 수)
//	    return 3;  // 예시로 2 페이지까지 있다고 가정
//	}
}
package FirstCanvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

public class A_Brood extends JPanel {
    int chose2 = 0;
    Image intro_image;
    viewController controller;
    int currentPage1 = 0;  // 현재 페이지를 나타내는 변수
    Image[]	Unit;

    public A_Brood(viewController controller) {
        this.controller = controller;
        
        intro_image = Toolkit.getDefaultToolkit().getImage("images/explain.png");
        
        Unit = new Image[3];
        Unit[0] = Toolkit.getDefaultToolkit().getImage("unitImage/human.png");
        Unit[1] = Toolkit.getDefaultToolkit().getImage("unitImage/elf.png");
        Unit[2] = Toolkit.getDefaultToolkit().getImage("unitImage/goblin.png");

        // 패널이 포커스를 받을 수 있도록 설정
        setFocusable(true);

        A_BroodMouseListener mouseListener = new A_BroodMouseListener(this);
        this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
    }

    public void paint(Graphics g) {
        // 이미지를 그려줌
        g.drawImage(intro_image, 0, 0, 1310, 775, this);
        g.setFont(new Font("myFont", Font.BOLD, 40));
        g.setColor(Color.BLACK);
        g.drawString("About Brood", 50, 50);
        g.setFont(new Font("secondFont", Font.ITALIC, 20));
        g.setColor(Color.RED);
        g.drawString("Return Information", 1100, 750);
        g.setFont(new Font("thirdFont", Font.BOLD ,20));
        g.setColor(Color.black);
        g.drawString("Next", 1180, 650);
        g.drawString("Back", 1080, 650);
        // 선택한 메뉴에 따라 화살표를 그림
        if (chose2 == 0)
            g.drawString("→", 1160, 650);
        else if (chose2 == 1)
            g.drawString("→", 1060, 650);
        else if (chose2 == 2)
            g.drawString("→", 1080, 750);
        
        if (currentPage1 == 0) {
            drawInstructionsOverlay(g, "인간 - 다재다능");
	    	g.drawImage(Unit[0], 230, 70, 450, 400, this);
	    	g.drawString("공격력 : 60", 650, 250);
	    	g.drawString("생산력 : 50", 650, 300);
	    	g.drawString("이동력 : 150", 650, 350);
	    	g.drawString("인간은 특출난 능력이 없는 대신 모든 수치가 균등하게 분배되어 있습니다.", 70, 500);

        } else if (currentPage1 == 1) {
            drawInstructionsOverlay(g, "엘프 - 신속");
	        g.drawImage(Unit[1], 230, 70, 450, 400, this);
	    	g.drawString("공격력 : 50 ", 650, 250);
	    	g.drawString("생산력 : 50", 650, 300);
	    	g.drawString("이동력 : 200", 650, 350);
	    	g.drawString("엘프는 인간과 고블린에 비해 생산력이나 공격력이 떨어지지만, 이동력이 빠릅니다.", 70, 500);

        }else if (currentPage1 == 2) {
        	drawInstructionsOverlay(g, "고블린 - 다산");
        	g.drawImage(Unit[2],230, 70, 450, 400, this);
	 	    g.drawString("공격력 : 35", 650, 250);
	 	    g.drawString("생산력 : 80", 650, 300);
	 	    g.drawString("이동력 : 150 ", 650, 350);
	 	    g.drawString("고블린은 인간과 엘프에 비해 공격력과 이동력이 떨어지지만, 그것을 메울수 있는 생산력이 있습니다.", 70, 500);

        }
   }	

    public void BackInformation() {
        controller.BackInformation(this); 
    }
    private void drawInstructionsOverlay(Graphics g, String instructionText) {
        // 반투명한 회색 오버레이 그리기
        Color overlayColor = new Color(128, 128, 128, 128);
        g.setColor(overlayColor);
        g.fillRect(50, 60, 1200, 600);

        // 게임 방법 설명 텍스트 그리기
        g.setFont(new Font("instructionFont", Font.PLAIN, 18));
        g.setColor(Color.WHITE);
        g.drawString(instructionText, 70, 100);

        // 추가적인 설명 텍스트 그리기...
    }
    public void next() {
        // 다음 페이지로 이동
        currentPage1++;
        if (currentPage1 >= getMaxPages()) {
            currentPage1 = getMaxPages() - 1;
        }
        repaint(); // 화면 다시 그리기
    }
    
    public void previous() {
        // 이전 페이지로 이동
        currentPage1--;
        if (currentPage1 < 0) {
            currentPage1 = 0;
        }
        repaint(); // 화면 다시 그리기
    }

    private int getMaxPages() {
        // 전체 페이지 수 반환 (최대 페이지 수)
        return 3;  // 예시로 2 페이지까지 있다고 가정
    }
    
    
}

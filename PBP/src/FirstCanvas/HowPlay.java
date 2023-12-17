package FirstCanvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

public class HowPlay extends JPanel {
    int chose1 = 0;
    Image intro_image;
    viewController controller;
    int currentPage = 0;  // 현재 페이지를 나타내는 변수

    public HowPlay(viewController controller) {
        this.controller = controller;
        
        intro_image = Toolkit.getDefaultToolkit().getImage("images/explain.png");

        // 패널이 포커스를 받을 수 있도록 설정
        setFocusable(true);
        
        HowPlayMouseListener mouseListener = new HowPlayMouseListener(this);
        this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
    }

    public void paint(Graphics g) {
        // 이미지를 그려줌
        g.drawImage(intro_image, 0, 0, 1310, 775, this);
        g.setFont(new Font("myFont", Font.BOLD, 40));
        g.setColor(Color.BLACK);
        g.drawString("How Play", 50, 50);
        g.setFont(new Font("secondFont", Font.ITALIC, 20));
        g.setColor(Color.RED);
        g.drawString("Return Information", 1100, 750);
        g.setFont(new Font("thirdFont", Font.BOLD ,20));
        g.setColor(Color.black);
        // 선택한 메뉴에 따라 화살표를 그림
        if (chose1 == 2)
            g.drawString("→", 1080, 750);
        
        if (currentPage == 0) {
            drawInstructionsOverlay(g, "게임 방법 설명: 유닛을 소환하여 상대방 기지를 점령하는 게임입니다!");
            g.drawString("기본 조작 : 오른쪽 패널의 방향키와 버튼을 클릭하여 유닛을 조작할 수 있습니다.", 70, 150);
            g.drawString("필드 정보: 본진 / 공터 / 빈 성 ", 70, 200);
            g.drawString("본진 : 내 유닛의 시작 지점", 70, 230);
            g.drawString("공터 : 아무 기능 없는 공터", 70, 250);
            g.drawString("빈성 : 점령하여 본진과 동일한 시스템 사용 가능", 70, 270);
            g.drawString("유닛 : 인간 / 엘프 / 고블린", 70, 320);
            g.drawString("승리 조건 : 상대방의 기지를 점령", 70, 370);
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


    
    
}

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
        g.drawString("Next", 1180, 650);
        g.drawString("Back", 1080, 650);
        // 선택한 메뉴에 따라 화살표를 그림
        if (chose1 == 0)
            g.drawString("→", 1160, 650);
        else if (chose1 == 1)
            g.drawString("→", 1060, 650);
        else if (chose1 == 2)
            g.drawString("→", 1080, 750);
        
        if (currentPage == 0) {
            drawInstructionsOverlay(g, "게임 방법 설명: 페이지 1");
        } else if (currentPage == 1) {
            drawInstructionsOverlay(g, "게임 방법 설명: 페이지 2");
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
        currentPage++;
        if (currentPage >= getMaxPages()) {
            currentPage = getMaxPages() - 1;
        }
        repaint(); // 화면 다시 그리기
    }
    
    public void previous() {
        // 이전 페이지로 이동
        currentPage--;
        if (currentPage < 0) {
            currentPage = 0;
        }
        repaint(); // 화면 다시 그리기
    }

    private int getMaxPages() {
        // 전체 페이지 수 반환 (최대 페이지 수)
        return 2;  // 예시로 2 페이지까지 있다고 가정
    }
    
    
}

package FirstCanvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

public class A_War extends JPanel {
    int chose3 = 0;
    Image intro_image;
    viewController controller;
    int currentPage2 = 0;  // 현재 페이지를 나타내는 변수

    public A_War(viewController controller) {
        this.controller = controller;
        
        intro_image = Toolkit.getDefaultToolkit().getImage("images/explain.png");

        // 패널이 포커스를 받을 수 있도록 설정
        setFocusable(true);

        A_WarMouseListener mouseListener = new A_WarMouseListener(this);
        this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
    }

    public void paint(Graphics g) {
        // 이미지를 그려줌
        g.drawImage(intro_image, 0, 0, 1310, 775, this);
        g.setFont(new Font("myFont", Font.BOLD, 40));
        g.setColor(Color.BLACK);
        g.drawString("About War", 50, 50);
        g.setFont(new Font("secondFont", Font.ITALIC, 20));
        g.setColor(Color.RED);
        g.drawString("Return Information", 1100, 750);
        g.setFont(new Font("thirdFont", Font.BOLD ,20));
        g.setColor(Color.black);
        if (chose3 == 2)
            g.drawString("→", 1080, 750);
        
        if (currentPage2 == 0) {
            drawInstructionsOverlay(g, "대전쟁의 발생");
            g.drawString("한 때 친했던 세 종족이 있었습니다. 각각 \"인간\", \"엘프\", 그리고 \"고블린\"이라 불리었습니다.", 70, 150);
            g.drawString("이들은 수세기 동안 평화롭게 공존해왔고 자연의 섭리에 따라 자원을 나누어 사용하여 번영하고 있었습니다.",70,170);
            g.drawString("그러나 어느 날, 그들의 땅에 이상한 현상이 일어났습니다. 식물이 죽어가고 동물들이 이탈하며 물은 변질되고 있었습니다.",70,190);
            g.drawString("세 종족은 각자 자신들이 다른 부족의 잘못된 행동 때문이라고 의심하기 시작했습니다.",70,210);
            g.drawString("\"만드는걸 좋아하는 인간이 그랬어!!\", \"더럽게 생활하는 고블린이 그랬어!!!\",\" 크릭케륽륵(엘프가 물에 이상한 짓을 하는걸 봤다)\"",70,260);
            g.drawString("라며 세 종족은 각자 자신들의 피해가 다른 부족의 행동으로 발생했다며 의심하였습니다.",70,310);
            g.drawString("이에따라 각 종족은 자신들의 주장을 입증하려 노력하였지만, 이상한 현상은 더욱 악화되었습니다.",70,330);
            g.drawString("각 종족은 자신들의 생존을 위해 더 많은 자원을 축적하려 하였고, 자연의 섭리에 따르지 않는 행동을 취하기 시작하였습니다.",70,350);
            g.drawString("이러한 갈등으로 인해 세 부족간의 싸움은 불거지게 되었습니다. 자원 경쟁, 영토 분쟁, ",70,370);
            g.drawString("그리고 상호간의 공격가 방어가 이루어지며 예전의 평화로운 공동체는 사라져갔습니다.",70,390);
            g.setFont(new Font("job", Font.BOLD ,30));
            g.drawString("이 대전쟁에서 당신은 특정 종족의 지휘관이자 왕이자 우두머리입니다.",70,490);
            g.drawString("당신의 종족을 이끌어 대전쟁에서 승리하십시오.",70,550);
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
        g.drawString(instructionText, 600, 100);

        // 추가적인 설명 텍스트 그리기...
    }
    
    
}

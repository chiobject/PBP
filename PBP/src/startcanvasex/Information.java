package startcanvasex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import javax.swing.JPanel;

public class Information extends JPanel {
    int chose = 0;
    Image intro_image;
    viewController controller;

    public Information(viewController controller) {
        this.controller = controller;
        
        intro_image = Toolkit.getDefaultToolkit().getImage("images/background.png");

        // 패널이 포커스를 받을 수 있도록 설정
        setFocusable(true);

        // KeyListener를 패널에 추가
        addKeyListener(new InformationkeyEvent(this));
        InformationMouseListener mouseListener = new InformationMouseListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }

    public void paint(Graphics g) {
        // 이미지를 그려줌
        g.drawImage(intro_image, 0, 0, 1310, 800, this);
        g.setFont(new Font("myFont", Font.BOLD, 40));
        g.setColor(Color.BLACK);
        g.drawString("Information", 200, 400);
        g.setFont(new Font("secondFont", Font.ITALIC, 20));
        g.setColor(Color.BLACK);
        g.drawString("How Play", 200, 450);
        g.drawString("About Brood", 200, 500);
        g.drawString("About War", 200, 550);
        g.drawString("Back", 200, 600);

        // 선택한 메뉴에 따라 화살표를 그림
        if (chose == 0)
            g.drawString("→", 180, 450);
        else if (chose == 1)
            g.drawString("→", 180, 500);
        else if (chose == 2)
            g.drawString("→", 180, 550);
        else
        	g.drawString("→", 180, 600);
   }	

    public void goBack() {
        controller.goBack(this); // viewController에게 goBack 메서드 호출을 요청
    }
    
	public void howtoplay() {
		controller.showHowPlay();
	}
	
	public void Brood() {
		controller.showA_Brood();
	}
	
	public void War() {
		controller.showA_War();
	}
    
}

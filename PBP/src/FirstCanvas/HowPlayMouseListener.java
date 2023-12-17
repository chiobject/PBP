package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HowPlayMouseListener extends MouseAdapter implements MouseMotionListener {
	HowPlay HowPlay;

    public HowPlayMouseListener(HowPlay HowPlay) {
        this.HowPlay = HowPlay;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        handleMouseClick(mouseX, mouseY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        handleMouseMovement(mouseX, mouseY);
        HowPlay.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
        if (mouseX >= 1170 && mouseX <= 1230) {
            if (mouseY >= 630 && mouseY <= 660) {
            	HowPlay.chose1 = 0;
            }
        	}else if (mouseX >= 1075 && mouseX <= 1135){
            	if(mouseY >= 630 && mouseY <= 660) {
            		HowPlay.chose1 = 1;
            	}
            } else if (mouseX >= 1080 && mouseX <= 1280) { // Return Information 버튼 좌표 범위
                if (mouseY >= 720 && mouseY <= 790) {
                    HowPlay.chose1 = 2;
                }
            }
        HowPlay.repaint();
}

    private void performAction() {
        if (HowPlay.chose1 == 0) {
        	HowPlay.next();
        } else if (HowPlay.chose1 == 1) {
        	HowPlay.previous();
        } else if (HowPlay.chose1 == 2) {
        	HowPlay.BackInformation();
        }
    }
}
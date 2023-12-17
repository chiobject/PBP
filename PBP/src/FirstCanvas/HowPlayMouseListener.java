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
            if (mouseX >= 1080 && mouseX <= 1280) { // Return Information 버튼 좌표 범위
                if (mouseY >= 720 && mouseY <= 790) {
                    HowPlay.chose1 = 2;
                }
            }
        HowPlay.repaint();
}

    private void performAction() {
    	if (HowPlay.chose1 == 2) {
        	HowPlay.BackInformation();
        }
    }
}
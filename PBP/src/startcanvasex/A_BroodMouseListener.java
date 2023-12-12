package startcanvasex;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class A_BroodMouseListener extends MouseAdapter implements MouseMotionListener {
	A_Brood A_Brood;

    public A_BroodMouseListener(A_Brood A_Brood) {
        this.A_Brood = A_Brood;
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
        A_Brood.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
        if (mouseX >= 1180 && mouseX <= 1280) {
            if (mouseY >= 600 && mouseY <= 700) {
            	A_Brood.chose2 = 0;
            }
        }else if (mouseX >= 1050 && mouseX <= 1130){
            	if(mouseY >= 600 && mouseY <= 700) {
            		A_Brood.chose2 = 1;
            	}
            } else if (mouseX >= 1000 && mouseX <= 1200) { // Return Information 버튼 좌표 범위
                if (mouseY >= 730 && mouseY <= 800) {
                	A_Brood.chose2 = 2;
                }
            }
         else {
        	 A_Brood.chose2 = 0;
        }
        A_Brood.repaint();
}

    private void performAction() {
        if (A_Brood.chose2 == 0) {
        	A_Brood.next();
        } else if (A_Brood.chose2 == 1) {
        	A_Brood.previous();
        } else if (A_Brood.chose2 == 2) {
        	A_Brood.BackInformation();
        }
    }
}
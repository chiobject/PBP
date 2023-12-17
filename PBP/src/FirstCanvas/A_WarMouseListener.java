package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class A_WarMouseListener extends MouseAdapter implements MouseMotionListener {
	A_War A_War;

    public A_WarMouseListener(A_War A_War) {
        this.A_War = A_War;
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
        A_War.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
    	if (mouseX >= 1000 && mouseX <= 1200) { // Return Information 버튼 좌표 범위
                if (mouseY >= 730 && mouseY <= 800) {
                	A_War.chose3 = 2;
                }
            }
         else {
        	 A_War.chose3 = 0;
        }
        A_War.repaint();
}

    private void performAction() {
    	if (A_War.chose3 == 2) {
        	A_War.BackInformation();
        }
    }
}
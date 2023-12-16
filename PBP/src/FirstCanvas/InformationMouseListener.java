package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class InformationMouseListener extends MouseAdapter implements MouseMotionListener {
	Information Information;

    public InformationMouseListener(Information Information) {
        this.Information = Information;
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
        Information.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
        if (mouseX >= 200 && mouseX <= 400) {
            if (mouseY >= 400 && mouseY <= 450) {
            	Information.chose = 0;
            } else if (mouseY >= 455 && mouseY <= 505) {
            	Information.chose = 1;
            } else if (mouseY >= 510 && mouseY <= 560) {
            	Information.chose = 2;
            } else {
            	Information.chose = -1;
            }
        } else {
        	Information.chose = 0;
        }
        Information.repaint();
    }

    private void performAction() {
        if (Information.chose == 0) {
        	Information.howtoplay();
        } else if (Information.chose == 1) {
        	Information.Brood();
        } else if (Information.chose == 2) {
        	Information.War();
        }
        else {
        	Information.goBack();
        }
    }
}


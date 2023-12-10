package startcanvasex;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroMouseListener extends MouseAdapter {
    IntroPanel introPanel;

    public IntroMouseListener(IntroPanel introPanel) {
        this.introPanel = introPanel;
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
        introPanel.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
        if (mouseX >= 450 && mouseX <= 650) {
            if (mouseY >= 400 && mouseY <= 500) {
                introPanel.select = 0;
            } else if (mouseY >= 535 && mouseY <= 635) {
                introPanel.select = 1;
            } else if (mouseY >= 680 && mouseY <= 780) {
                introPanel.select = 2;
            } else {
                introPanel.select = -1;
            }
        } else {
            introPanel.select = -1;
        }
        introPanel.repaint();
    }

    private void performAction() {
        if (introPanel.select == 0) {
            introPanel.gameStart();
        } else if (introPanel.select == 1) {
            introPanel.How_To_Play();
        } else if (introPanel.select == 2) {
            System.exit(0);
        }
    }
}


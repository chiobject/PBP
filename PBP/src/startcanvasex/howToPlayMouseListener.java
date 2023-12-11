package startcanvasex;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class howToPlayMouseListener extends MouseAdapter {
	howToPlay howToPlay;
	howtoplayExplain howtoplayExplain;

    public howToPlayMouseListener(howToPlay howToPlay) {
        this.howToPlay = howToPlay;
    }
    
    public HTPEMouseListener(howtoplayExplain howtoplayExplain) {
    	this.howtoplayExplain =  howtoplayExplain;
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
        howToPlay.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
        if (mouseX >= 200 && mouseX <= 400) {
            if (mouseY >= 400 && mouseY <= 450) {
            	howToPlay.chose = 0;
            } else if (mouseY >= 455 && mouseY <= 505) {
            	howToPlay.chose = 1;
            } else if (mouseY >= 510 && mouseY <= 560) {
            	howToPlay.chose = 2;
            } else {
            	howToPlay.chose = -1;
            }
        } else {
        	howToPlay.chose = -1;
        }
        howToPlay.repaint();
    }

    private void performAction() {
        if (howToPlay.chose == 0) {
        //	howToPlay.gameStart();
        } else if (howToPlay.chose == 1) {
        //	howToPlay.How_To_Play();
        } else if (howToPlay.chose == 2) {
        //    System.exit(0);
        }
        else {
        	howToPlay.goBack();
        }
    }
}


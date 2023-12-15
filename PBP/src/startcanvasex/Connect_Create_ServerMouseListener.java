package startcanvasex;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Connect_Create_ServerMouseListener extends MouseAdapter implements MouseMotionListener {
	Connect_Create_Server Connect_Create_Server;

    public Connect_Create_ServerMouseListener(Connect_Create_Server Connect_Create_Server) {
        this.Connect_Create_Server = Connect_Create_Server;
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
        Connect_Create_Server.repaint();
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        handleMouseMovement(mouseX, mouseY);
        performAction();
    }

    private void handleMouseMovement(int mouseX, int mouseY) {
        if (mouseX >= 400 && mouseX <= 650) {
            if (mouseY >= 400 && mouseY <= 500) {
            	Connect_Create_Server.chose = 0;
            } else if (mouseY >= 535 && mouseY <= 635) {
            	Connect_Create_Server.chose = 1;
            } else if (mouseY >= 640 && mouseY <= 750) {
            	Connect_Create_Server.chose = 2;
            }
        }else if(mouseX >= 1200 && mouseX <= 1300) {
        	 if (mouseY >= 600 && mouseY <= 700) {
             	Connect_Create_Server.chose = 3;
             }
        }
        Connect_Create_Server.repaint();
    }

    private void performAction() {
        if (Connect_Create_Server.chose == 0) {
        	Connect_Create_Server.showServerDialog();
        } else if (Connect_Create_Server.chose == 1) {
        	Connect_Create_Server.showClientDialog();
        } else if (Connect_Create_Server.chose == 2) {
        	Connect_Create_Server.gameStart();
        }
        else {
        	Connect_Create_Server.BackIntro();
        }
    }
}


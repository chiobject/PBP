package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.UUID;

import main.gameGUI;
import server2.ClientDialog;
import server2.ServerDialog;
import server2.TetrisClient;
import server2.TetrisServer;


public class Connect_Create_ServerMouseListener extends MouseAdapter implements MouseMotionListener {
	Connect_Create_Server Connect_Create_Server;
	
	private static TetrisClient originalclient = null;
	
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
        if (mouseX >= 400 && mouseX <= 920) {
            if (mouseY >= 400 && mouseY <= 480) {
            	Connect_Create_Server.chose = 0;
            } else if (mouseY >= 490 && mouseY <= 610) {
            	Connect_Create_Server.chose = 1;
            } else if (mouseY >= 630 && mouseY <= 750) {
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
        	ServerDialog dialog = new ServerDialog();
			dialog.setLocationRelativeTo(null); 
			dialog.setVisible(true);
			if (dialog.userChoice == ServerDialog.Choice.OK) {
				TetrisServer tetrisserver = new TetrisServer(dialog.getPortNumber());
				tetrisserver.start();
			}
        } else if (Connect_Create_Server.chose == 1) {
        	ClientDialog dialog = new ClientDialog();
			dialog.setLocationRelativeTo(null); 
			dialog.setVisible(true);
			if (dialog.userChoice == ClientDialog.Choice.OK) {
				UUID uuid = UUID.randomUUID();
				originalclient = new TetrisClient(dialog.getHost(),dialog.getPortNumber(),uuid);
				originalclient.start();
			}
            gameGUI gamegui = new gameGUI();
        } else if (Connect_Create_Server.chose == 2) {
        	Connect_Create_Server.gameStart();
        }
        else {
        	Connect_Create_Server.BackIntro();
        }
    }
	// 네트워크 갱신을 위한 메서드
	public static void originalrefresh() {
		if (originalclient != null)
			originalclient.send();
	}
}


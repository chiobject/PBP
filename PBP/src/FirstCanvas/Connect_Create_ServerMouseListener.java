package FirstCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import main.gameGUI;
import server.client;
import server.clientDialog;
import server.Server;
import server.ServerDialog;


public class Connect_Create_ServerMouseListener extends MouseAdapter implements MouseMotionListener {
	Connect_Create_Server Connect_Create_Server;
	
	private static client originalclient = null;
	private static Server server = null;
	
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
				Server server = new Server(dialog.getPortNumber());
				server.start();
			}
        } else if (Connect_Create_Server.chose == 1) {
        	clientDialog dialog = new clientDialog();
			dialog.setLocationRelativeTo(null); 
			dialog.setVisible(true);
			if (dialog.userChoice == clientDialog.Choice.OK) {
				originalclient = new client(dialog.getHost(),dialog.getPortNumber());
				originalclient.start();
			}
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
	// 네트워크 갱신을 위한 메서드
	public static client getOriginalCleint() {
		return originalclient;
	}
}


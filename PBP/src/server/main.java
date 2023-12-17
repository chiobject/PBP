package server;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import main.gameGUI;

public class main {
	private static TetrisClient originalclient = null;
	
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        
     // 버튼 1에 액션 리스너 추가
        button1.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
				ServerDialog dialog = new ServerDialog();
				dialog.setLocationRelativeTo(null); 
				dialog.setVisible(true);
				if (dialog.userChoice == ServerDialog.Choice.OK) {
					TetrisServer tetrisserver = new TetrisServer(dialog.getPortNumber());
					tetrisserver.start();
				}
				
			}
        });

        // 버튼 2에 액션 리스너 추가
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				ClientDialog dialog = new ClientDialog();
				dialog.setLocationRelativeTo(null); 
				dialog.setVisible(true);
				if (dialog.userChoice == ClientDialog.Choice.OK) {
					UUID uuid = UUID.randomUUID();
					originalclient = new TetrisClient(dialog.getHost(),dialog.getPortNumber(),uuid);
					originalclient.start();
				}
                gameGUI gamegui = new gameGUI();
			}
        });
        

        panel.add(button1);
        panel.add(button2);

        frame.add(panel);

        frame.setVisible(true);
    }
    
	// 네트워크 갱신을 위한 메서드
	public static void originalrefresh() {
		if (originalclient != null)
			originalclient.send();
	}
}

package FirstCanvas;

import java.awt.FlowLayout;
import java.awt.Label;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GamePanel extends JPanel {
	private JTextArea textArea;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GamePanel() {
    	textArea = new JTextArea("메인 게임 화면");
    }
}
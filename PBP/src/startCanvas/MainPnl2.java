package startCanvas;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MainPnl2 extends JPanel implements ActionListener{
	Main_StartHere main;
	private Image backgroundImage;
	private JButton a, b;
	
	public MainPnl2(Main_StartHere main) {
		this.main=main;
		this.setSize(1300,810);
		this.setLayout(null);
		
		try {
			backgroundImage = ImageIO.read(new File("asd.jpg"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		makeBtns();
	}
	
	private void makeBtns() {
		a = new JButton();
		b = new JButton();
		
		a.setBounds(getVisibleRect());
		b.setBounds(getVisibleRect());
		
		a.setBorderPainted(getFocusTraversalKeysEnabled());
		b.setBorderPainted(getFocusTraversalKeysEnabled());
		
		a.setFocusPainted(getFocusTraversalKeysEnabled());
		b.setFocusPainted(getFocusTraversalKeysEnabled());
		
		a.setContentAreaFilled(getFocusTraversalKeysEnabled());
		b.setContentAreaFilled(getFocusTraversalKeysEnabled());
		
		this.add(a);
		this.add(b);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==a) {
			main.MainPnl2ToNext(1);
		}else if (e.getSource()==b) {
			main.MainPnl2ToNext(2);
		}
		
	}

}

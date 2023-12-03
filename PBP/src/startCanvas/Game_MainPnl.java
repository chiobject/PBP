package startCanvas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import startCanvas.Main_StartHere;

public class Game_MainPnl extends JPanel implements ActionListener {
	Main_StartHere main;
	
	private Image backgroundImage;
	private JButton btnNext;
	
	public Game_MainPnl(Main_StartHere main) {
		this.main=main;
		this.setSize(1300,810);
		this.setLayout(null);
		
		try {//배경이미지
			backgroundImage = ImageIO.read(new File ("images/start/kim.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		btnNext = new JButton();
		btnNext.setBounds(290, 705, 150, 50);
		btnNext.setBorderPainted(false);
		btnNext.setFocusPainted(false);
		btnNext.setContentAreaFilled(false);
		btnNext.addActionListener(this);
		this.add(btnNext);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, 1300, 810, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		main.Game_MainPnlToNext();
	}

}

package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.gameGUI;
import main.mainCanvas;

public class unit extends JPanel implements KeyListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int unitType;
	int attack = 0;
	int Speed = 0;
	private int x = 50;
	private int y = 50;
	public int dir = 0;
	public int diameter = 50;
	private int hp;
	private boolean running = false;
	private Thread worker;
	public double speed;
	ImageIcon seaicon2 = new ImageIcon("images\\background\\unittest.png");

	public unit(int unitType) {
		this.unitType = unitType;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setSpeed(int Speed) {
		this.Speed = Speed;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void paint() {
		System.out.println("gjr");
		gameGUI.getMainCanvas().offScreenGraphics.drawImage(seaicon2.getImage(), x,y, this);
	}
	
	public void setPosition(int x,int y) {
		this.x = x;
		this.y = y;
	}

	public void start() {
		if (!running) {
			running = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		if(dir == 0) {y+=10;}
		else if(dir==1) {x+=10;}
		else if(dir==2) {y-=10;}
		else if(dir==3) {x-=10;}
		while (running) {
			unitmove(dir);
			repaint();
			try {
				worker.sleep((long) speed * 1000); // 원하는 갱신 주기로 조절

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void unitmove(int dir) {
		switch (dir) {
		case 0:
			y += 1;
			break;
		case 1:
			x += 1;
			break;
		case 2:
			y -= 1;
			break;
		case 3:
			x -= 1;
			break;
		}
	}
}

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
import javax.swing.JPanel;

import main.mainCanvas;

public class unit extends JPanel implements KeyListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int prodTime=0;
	int prodAmount=0;
	int attack=0;
	int moveTime=0;
	private int x = 50;
	private int y = 50;
	public int dir = 0;
	public int diameter = 50;
	private boolean running = false;
	private Thread worker;
	public double speed = 1;
	
	public unit(int prodTime, int prodAmount, int attack, int moveTime) {
		this.prodTime = prodTime;
		this.prodAmount = prodAmount;
		this.attack = attack;
		this.moveTime = moveTime;
	}
	
	public void adjustProdTime(int prodTime) {
		this.prodTime += prodTime;
	}
	
	public void adjustProdAmount(int prodAmount) {
		this.prodAmount += prodAmount;
	}
	
	public void adjustAttack(int attack) {
		this.attack += attack;
	}
	
	public void adjustMoveTime(int moveTime) {
		this.moveTime += moveTime;
	}
	public void paint(Graphics g, mainCanvas screen) {
		//g.drawImage(sprite, 0, 0, screen);
		g.setColor(Color.BLUE);  // 색상을 원하는 색으로 변경
	    g.fillOval(x, y, diameter, diameter);  // fillOval로 원을 채움
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
        while (running) {
        	unitmove(dir);
            try {
                worker.sleep((long)speed*1000); // 원하는 갱신 주기로 조절
                
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

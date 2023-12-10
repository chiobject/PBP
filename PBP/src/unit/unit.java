package unit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
import java.util.List;

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
	private int x = 50;
	private int y = 50;
	private Point position = new Point(x, y);
	public int dir = 0;
	public int diameter = 50;
	private int hp;
	private boolean running = false;
	private Thread worker;
	public double speed;
	public ImageIcon seaicon2 = new ImageIcon("images\\background\\unittest.png");
	private boolean move;
	private int owner;

	public unit(int unitType, int Onwer) {
		this.unitType = unitType;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setSpeed(int Speed) {
		this.speed = Speed;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}

	public void start() {
		if (!running) {
			running = true;
			move = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		while (running) {
			checkCollisions();
			if(move == true) {
				unitmove(dir);
			}
			repaint();
			try {
				worker.sleep(100); // 원하는 갱신 주기로 조절
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
			position.y -= 1;
			break;
		case 1:
			position.x += 1;
			break;
		case 2:
			position.y += 1;
			break;
		case 3:
			position.x -= 1;
			break;
		}
	}
	
	public void checkCollisions() {
		setMove(true);
        for (unit allUnits : gameGUI.getData().units) {
            if (allUnits != this && isCollision(this, allUnits)) {
                if (this.getOwner() != allUnits.getOwner()) {
                    System.out.println("적 교집합");
                    setMove(false);
                } else {
                    System.out.println("아군 교집합");
                    setMove(false);
                }
            }
        }
    }

	public boolean isCollision(unit unit1, unit unit2) {
		int collision = 30;
		int deltaX = Math.abs(unit1.getPosition().x - unit2.getPosition().x);
		int deltaY = Math.abs(unit1.getPosition().y - unit2.getPosition().y);
		return deltaX <= collision && deltaY <= collision;
	}
	

	public Point getPosition() {
		return position;
	}
	public void setMove(boolean move) {
		this.move = move;
	}
	public boolean getMove() {
		return move;
	}
	public int getOwner(){
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
}

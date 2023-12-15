package main;

import javax.swing.*;

import field.field;
import unit.unit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class mainCanvas extends JPanel implements ActionListener, Runnable, MouseListener, KeyListener {
	private static final int RECTANGLE_SIZE = 64;
	private Thread worker;
	private Timer timer = new Timer();
	private boolean stop;
	int x, y;
	Point field = new Point(x, y);
	Point select = new Point(x, y);
	Point fieldSelect = new Point(x, y);
	private int seaIconX = 90;
	private int seaIconY = 50;
	public Graphics g;
	public unit unit;
	double time = 0;
	private Color red = Color.red;
	private Color blue = Color.blue;
	private Color black = Color.black;
	private Image offScreenImage; // 더블 버퍼링을 위한 이미지
	public Graphics offScreenGraphics; // 더블 버퍼링을 위한 그래픽스
	private Image ScreenImage; // 더블 버퍼링을 위한 이미지
	public Graphics ScreenGraphics; // 더블 버퍼링을 위한 그래픽스

	ImageIcon seaicon2 = new ImageIcon("images\\mainCanvas\\sample 60.jpg");
	ImageIcon seaicon1 = new ImageIcon("images\\mainCanvas\\sea1.png");

	public mainCanvas() {
		setLayout(new GridLayout(9, 9));

		gameGUI.getData().map.reset();
		gameGUI.getData().map.create();
		// Adding MouseListener to the panel
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
	}

	public void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
//                // 0.1초마다 변수를 0.1씩 증가
//                time += 0.1;
//                time = Math.round(time * 10.0) / 10.0;
//                // 변수 출력 (예시로 출력만 함)
//                System.out.println("변수 값: " + time);
			}
		}, 0, 100);
		stop = false;
		worker = new Thread(this);
		worker.start();
		repaint();
//		unit.start();
	}

	public void paintComponent(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = createImage(getWidth(), getHeight());
			offScreenGraphics = offScreenImage.getGraphics();
		}

		if (ScreenImage == null) {
			ScreenImage = createImage(getWidth(), getHeight());
			ScreenGraphics = ScreenImage.getGraphics();
		}

		// 그림의 크기
		int imageWidth = gameGUI.getData().map.getPosition().x * RECTANGLE_SIZE;
		int imageHeight = gameGUI.getData().map.getPosition().y * RECTANGLE_SIZE;

		// 패널의 크기
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// 그림을 화면 중앙에 그리기 위한 좌표 계산
		field.x = (panelWidth - imageWidth) / 2;
		field.y = (panelHeight - imageHeight) / 2;

		// 그림 그리기
		for (int i = 0; i < gameGUI.getData().map.getPosition().x * 2; i++) {
			for (int j = 0; j < gameGUI.getData().map.getPosition().y * 1.5; j++) {
				offScreenGraphics.drawImage(seaicon1.getImage(), i * RECTANGLE_SIZE, j * RECTANGLE_SIZE - 7, this);
			}
		}

		for (int i = 0; i < gameGUI.getData().map.getPosition().x; i++) {
			for (int j = 0; j < gameGUI.getData().map.getPosition().y; j++) {
				if (gameGUI.getData().map.field[i][j].getOwner() == 1) {
					offScreenGraphics.setColor(red);
				} else if (gameGUI.getData().map.field[i][j].getOwner() == 2) {
					offScreenGraphics.setColor(blue);
				} else {
					offScreenGraphics.setColor(black);
				}
				if (gameGUI.getData().map.field[i][j].type != 0) {
					Rectangle rectangle = new Rectangle(field.x + i * RECTANGLE_SIZE + 1,
							field.y + j * RECTANGLE_SIZE + 1, RECTANGLE_SIZE, RECTANGLE_SIZE);
					offScreenGraphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

					if (rectangle.intersects(
							new Rectangle(seaIconX, seaIconY, seaicon2.getIconWidth(), seaicon2.getIconHeight()))) {
						// 부딪칠 경우 seaicon2 이미지를 숨김
						seaIconX = -seaicon2.getIconWidth();
					} else {
						offScreenGraphics.drawImage(seaicon2.getImage(), seaIconX, seaIconY, this);
					}
				}
				offScreenGraphics.drawString(gameGUI.getData().map.field[i][j].name,
						field.x + i * RECTANGLE_SIZE + RECTANGLE_SIZE / 2,
						field.y + j * RECTANGLE_SIZE + RECTANGLE_SIZE / 2);
			}
		}
		unitPaint(offScreenGraphics);
		g.drawImage(offScreenImage, 0, 0, this);
	}

	// Implementing the mouseClicked method
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	public void run() {
		while (!stop) {
			repaint();
			try {
				worker.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Point getSelect() {
		return select;
	}

	public Point getFieldSelectPoint() {
		fieldSelect.x = field.x + select.x * RECTANGLE_SIZE + 1;
		fieldSelect.y = field.y + select.y * RECTANGLE_SIZE + 1;
		return fieldSelect;
	}

	// Helper method to check if the mouse is inside a rectangle
	private boolean isMouseInsideRect(int rectX, int rectY, int rectWidth, int rectHeight, int mouseX, int mouseY) {
		return mouseX >= rectX && mouseX <= rectX + rectWidth && mouseY >= rectY && mouseY <= rectY + rectHeight;
	}

	// Other methods from the interfaces (not implemented here)
	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		// Check if the click is inside any field
		for (int i = 0; i < gameGUI.getData().map.getPosition().x; i++) {
			for (int j = 0; j < gameGUI.getData().map.getPosition().y; j++) {
				if (isMouseInsideRect(field.x + i * RECTANGLE_SIZE + 1, field.y + j * RECTANGLE_SIZE + 1,
						RECTANGLE_SIZE, RECTANGLE_SIZE, mouseX, mouseY)) {
					for (int k = 0; k < gameGUI.getData().map.getPosition().x; k++) {
						for (int l = 0; l < gameGUI.getData().map.getPosition().y; l++) {
							gameGUI.getData().map.field[k][l].setDirActivate(false);
							gameGUI.getData().map.field[k][l].setFieldActivate(true);
						}
					}
					System.out.println("Field Clicked at: (" + i + ", " + j + ") class : ("
							+ gameGUI.getData().map.field[i][j].name + ")");
					select.x = i;
					select.y = j;
					break;
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void unitPaint(Graphics offScreenGraphics) {
		for (unit unit : gameGUI.getData().units) {
			gameGUI.getMainCanvas().offScreenGraphics.drawImage(unit.seaicon2.getImage(), unit.getPosition().x,
					unit.getPosition().y, this);
		}
	}
	
	public field getSelectField() {
		return gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y];
	}
}
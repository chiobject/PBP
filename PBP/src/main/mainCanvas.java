package main;

import javax.swing.*;

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
	private ArrayList<unit> units = new ArrayList<>();
	

	ImageIcon seaicon2 = new ImageIcon("images\\background\\sample 60.jpg");
	ImageIcon seaicon1 = new ImageIcon("images\\background\\sea1.png");

	public mainCanvas() {
		setLayout(new GridLayout(9, 9));

		gameGUI.getMap().reset();
		gameGUI.getMap().create();
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
		int imageWidth = gameGUI.getMap().max_x * RECTANGLE_SIZE;
		int imageHeight = gameGUI.getMap().max_y * RECTANGLE_SIZE;

		// 패널의 크기
		int panelWidth = getWidth();
		int panelHeight = getHeight();

		// 그림을 화면 중앙에 그리기 위한 좌표 계산
		field.x = (panelWidth - imageWidth) / 2;
		field.y = (panelHeight - imageHeight) / 2;

		// 그림 그리기
		for (int i = 0; i < gameGUI.getMap().max_x * 2; i++) {
			for (int j = 0; j < gameGUI.getMap().max_y * 1.5; j++) {
				offScreenGraphics.drawImage(seaicon1.getImage(), i * RECTANGLE_SIZE, j * RECTANGLE_SIZE - 7, this);
			}
		}

		for (int i = 0; i < gameGUI.getMap().max_x; i++) {
			for (int j = 0; j < gameGUI.getMap().max_y; j++) {
				if (gameGUI.getMap().field[i][j].getFieldOwner() == 1) {
					offScreenGraphics.setColor(red);
				} else if (gameGUI.getMap().field[i][j].getFieldOwner() == 2) {
					offScreenGraphics.setColor(blue);
				} else {
					offScreenGraphics.setColor(black);
				}
				if (gameGUI.getMap().field[i][j].type != 0) {
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
				offScreenGraphics.drawString(gameGUI.getMap().field[i][j].name,
						field.x + i * RECTANGLE_SIZE + RECTANGLE_SIZE / 2,
						field.y + j * RECTANGLE_SIZE + RECTANGLE_SIZE / 2);
			}
		}
		for (unit u : units) {
            u.paint();
        }
		g.drawImage(offScreenImage, 0, 0, this);
//        unit.paint(g,this);
	}

	// Implementing the mouseClicked method
	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		// Check if the click is inside any field
		for (int i = 0; i < gameGUI.getMap().max_x; i++) {
			for (int j = 0; j < gameGUI.getMap().max_y; j++) {
				if (isMouseInsideRect(field.x + i * RECTANGLE_SIZE + 1, field.y + j * RECTANGLE_SIZE + 1,
						RECTANGLE_SIZE, RECTANGLE_SIZE, mouseX, mouseY)) {
					System.out.println("Field Clicked at: (" + i + ", " + j + ") class : ("
							+ gameGUI.getMap().field[i][j].name + ")");
					select.x = i;
					select.y = j;
					break;
				}
			}
		}
	}

	public void run() {
		while (!stop) {
//    		seaIconX += 5;
			repaint();
			try {
				worker.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Point getSelect() {
		return select;
	}

	public Point getFieldSelect() {
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
	
    public void addUnit(unit newUnit) {
        units.add(newUnit);
    }

}

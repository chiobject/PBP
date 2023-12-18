package main;

import javax.swing.*;
import javax.swing.Timer;

import FirstCanvas.Connect_Create_ServerMouseListener;

import field.field;
import unit.unit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mainCanvas extends JPanel implements ActionListener, Runnable, MouseListener, KeyListener {
	private static final int RECTANGLE_SIZE = 64;
	private Thread worker;
	private boolean stop;
	int x, y;
	Point field = new Point(x, y);
	Point select = new Point(x, y);
	Point fieldSelect = new Point(x, y);
	public Graphics g;
	public unit unit;
	private Image offScreenImage; // 더블 버퍼링을 위한 이미지
	public Graphics offScreenGraphics; // 더블 버퍼링을 위한 그래픽스
	public Graphics ScreenGraphics; // 더블 버퍼링을 위한 그래픽스
	public int seaSel = 0;
	private boolean timerIsRunning = false;

	ImageIcon seaIcon = new ImageIcon("images/mainCanvas/sea1.png");
	ImageIcon castle0Icon = new ImageIcon("images/field/castle0.png");
	ImageIcon castle1_1Icon = new ImageIcon("images/field/castle1-1.png");
	ImageIcon castle1_2Icon = new ImageIcon("images/field/castle1-2.png");
	ImageIcon castle1_3Icon = new ImageIcon("images/field/castle1-3.png");
	ImageIcon castle1_4Icon = new ImageIcon("images/field/castle1-4.png");
	ImageIcon castle2_1Icon = new ImageIcon("images/field/castle2-1.png");
	ImageIcon castle2_2Icon = new ImageIcon("images/field/castle2-2.png");
	ImageIcon castle2_3Icon = new ImageIcon("images/field/castle2-3.png");
	ImageIcon castle2_4Icon = new ImageIcon("images/field/castle2-4.png");
	
	ImageIcon plainIcon = new ImageIcon("images/field/plain.png");

	private sound click = new sound("sounds//전쟁시대 브금.wav", -30);

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
		stop = false;
		worker = new Thread(this);
		worker.start();
		repaint();
		}
	public void stop() {
		stop = true;
		worker.stop();
	}

	public void paintComponent(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = createImage(getWidth(), getHeight());
			offScreenGraphics = offScreenImage.getGraphics();
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
				offScreenGraphics.drawImage(seaIcon.getImage(), i * RECTANGLE_SIZE, j * RECTANGLE_SIZE - 7, this);
			}
		}

		for (int i = 0; i < gameGUI.getData().map.getPosition().x; i++) {
			for (int j = 0; j < gameGUI.getData().map.getPosition().y; j++) {
				int getOwner = gameGUI.getData().map.getField(i, j).getOwner();
				int getType = gameGUI.getData().map.getField(i, j).getType();
				boolean getIsBuilding = gameGUI.getData().map.getField(i, j).getIsBuilding();
				if (getOwner == 0 && (getType == 1 || getType == 2)) {
					offScreenGraphics.drawImage(castle0Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
							field.y + j * RECTANGLE_SIZE + 1, this);
				} else if (getOwner == 1 && getType != 0) {
					if (getIsBuilding == false) {
						offScreenGraphics.drawImage(castle1_4Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
								field.y + j * RECTANGLE_SIZE + 1, this);
					} else {
						if(timerIsRunning == false) {
							buildingImage(i,j);
						}
						if(gameGUI.getData().map.getField(i, j).getBuildingTime()  < 4000) {
		                	offScreenGraphics.drawImage(castle1_1Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
									field.y + j * RECTANGLE_SIZE + 1, this);
		                }
		                else if(gameGUI.getData().map.getField(i, j).getBuildingTime() < 7000) {
		                	offScreenGraphics.drawImage(castle1_2Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
									field.y + j * RECTANGLE_SIZE + 1, this);
		                }
		                else if (gameGUI.getData().map.getField(i, j).getBuildingTime() <  10000) {
		                	offScreenGraphics.drawImage(castle1_3Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
									field.y + j * RECTANGLE_SIZE + 1, this);

		                }
					}
				} else if (getOwner == 2 && getType != 0) {
					if (getIsBuilding == false) {
						offScreenGraphics.drawImage(castle2_4Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
								field.y + j * RECTANGLE_SIZE + 1, this);
					} else {
						if(timerIsRunning == false) {
							buildingImage(i,j);
						}
						if(gameGUI.getData().map.getField(i, j).getBuildingTime()  < 4000) {
		                	offScreenGraphics.drawImage(castle2_1Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
									field.y + j * RECTANGLE_SIZE + 1, this);
		                }
		                else if(gameGUI.getData().map.getField(i, j).getBuildingTime() < 7000) {
		                	offScreenGraphics.drawImage(castle2_2Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
									field.y + j * RECTANGLE_SIZE + 1, this);
		                }
		                else if (gameGUI.getData().map.getField(i, j).getBuildingTime() <  10000) {
		                	offScreenGraphics.drawImage(castle2_3Icon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
									field.y + j * RECTANGLE_SIZE + 1, this);

		                }
					}
				} else if (getType == 3) {
					offScreenGraphics.drawImage(plainIcon.getImage(), field.x + i * RECTANGLE_SIZE + 1,
							field.y + j * RECTANGLE_SIZE + 1, this);
				}

				offScreenGraphics.drawString(gameGUI.getData().map.getField(i, j).name,
						field.x + i * RECTANGLE_SIZE + RECTANGLE_SIZE / 2,
						field.y + j * RECTANGLE_SIZE + RECTANGLE_SIZE / 2);
			}
		}
		
		for (unit unit : gameGUI.getData().units) {
			unit.paint(offScreenGraphics);
		}
		g.drawImage(offScreenImage, 0, 0, this);
	}

	public void run() {
		while (!stop) {
			int n1 = 0;
			int n2 = 0;
			for (int i = 0; i < gameGUI.getData().map.getPosition().x; i++) {
				for (int j = 0; j < gameGUI.getData().map.getPosition().y; j++) {
					if(gameGUI.getData().map.getField(i, j).getType() == 1 && gameGUI.getData().map.getField(i, j).getOwner() == 1) {
						n1++;
					}
					else if(gameGUI.getData().map.getField(i, j).getType() == 1 && gameGUI.getData().map.getField(i, j).getOwner() == 2) {
						n2++;
					}
				}
			}
			if(n1 ==0 ) {
				System.out.println("player1의 패배");
				stop();
			}
			else if(n2 == 0 ) {
				System.out.println("player2의 패배");
				stop();
			}

			repaint();
			seaSel++;
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

	// Implementing the mouseClicked method
	@Override
	public void mouseClicked(MouseEvent e) {
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
							gameGUI.getData().map.getField(k, l).setDirActivate(false);
							gameGUI.getData().map.getField(k, l).setFieldActivate(true);
						}
					}
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

	public field getSelectField() {
		return gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x, gameGUI.getMainCanvas().select.y);
	}

	public Point getFieldPosition(int x, int y) {
		int centerX = field.x + (x * RECTANGLE_SIZE) + (RECTANGLE_SIZE / 2);
		int centerY = field.y + (y * RECTANGLE_SIZE) + (RECTANGLE_SIZE / 2);
		return new Point(centerX, centerY);
	}
	
	public void buildingImage(int i,int j) {
    	timerIsRunning = true;
		ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // 10초까지만 타이머 동작
            	gameGUI.getData().map.getField(i, j).changeBuildingTime(1000);
                if(gameGUI.getData().map.getField(i, j).getBuildingTime() >= 10000) {
                    gameGUI.getData().map.getField(i, j).setIsBuilding(false);
                    gameGUI.getData().map.getField(i, j).setIsProduction(true);
                    timerIsRunning = false;
                    ((Timer) evt.getSource()).stop();
                }
            }
        };

        // 타이머 생성 및 시작
        Timer timer = new Timer(1000, taskPerformer);
        timer.setInitialDelay(0);
        timer.start();

        // while 루프
        while (!timer.isRunning()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

	}
}
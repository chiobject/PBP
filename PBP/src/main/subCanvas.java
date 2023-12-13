package main;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import field.field;

public class subCanvas extends JPanel implements Runnable, MouseListener {
	protected boolean stop;
	protected Point mouse = new Point();
	int x, y;
	protected Thread worker;
	private field selectField;
	private JLabel button1 = new JLabel("버튼1");
	private JLabel button2 = new JLabel("버튼2");
	private JLabel button3 = new JLabel("버튼3");
	private JLabel button4 = new JLabel("버튼4");
	private JLabel button1Label = new JLabel();
	private JLabel button2Label = new JLabel();
	private JLabel button3Label = new JLabel();
	private JLabel button4Label = new JLabel();
	private JLabel dir[] = new JLabel[4];
	private Font fontTitle = new Font("굴림", Font.BOLD, 27);
	private Font fontButton = new Font("굴림", Font.BOLD, 20);
	private BufferedImage offScreenImage;
	private boolean fieldActivate = false;
	private boolean dirActivate = true;
	private ImageIcon buttonIcon = new ImageIcon(
			new ImageIcon("images\\subCanvas\\button1.png").getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
	private ImageIcon backGround = new ImageIcon(new ImageIcon("images\\subCanvas\\background2.png").getImage()
			.getScaledInstance(400, 640, Image.SCALE_SMOOTH));
	private ImageIcon preview = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\preview.png").getImage()
			.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	private ImageIcon dirIcon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrow.png").getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH));

	subCanvas() {
		addMouseListener(this);
		createLabel();
		setLayout(null);

		button1Label.setFont(fontButton);
		button1Label.setText("버튼 1");

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y]
						.button1();
			}
		});
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y]
						.button2();
			}
		});
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y]
						.button3();
			}
		});
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y]
						.button4();
			}
		});
	}

	void start() {
		stop = false;
		worker = new Thread(this);
		worker.start();
		requestFocus();
		repaint();
	}

	void stop() {
		stop = true;
	}

	public void paintComponent(Graphics g) {
		//
		if (offScreenImage == null) {
			offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		Graphics offScreenGraphics = offScreenImage.getGraphics();
		super.paintComponent(offScreenGraphics);

		selectField = gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y];

		offScreenGraphics.drawImage(backGround.getImage(), 0, 0, this);

		offScreenGraphics.setColor(Color.black);
		offScreenGraphics.drawImage(preview.getImage(), getWidth() / 8, 50, this);
		fieldmain(offScreenGraphics);
		dirmain(offScreenGraphics);

		offScreenGraphics.dispose();

		// 더블 버퍼링을 사용하여 이미지를 화면에 그립니다.
		g.drawImage(offScreenImage, 0, 0, this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!stop) {
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public JLabel getButton(int number) {
		if (number == 1) {
			return button1;
		} else if (number == 2) {
			return button2;
		} else if (number == 3) {
			return button3;
		} else {
			return button4;
		}
	}
	
	// 버튼 메인 화면
	public void fieldmain(Graphics g) {
		if (fieldActivate == true) {
			g.setFont(fontTitle);
			g.drawString("[" + selectField.name + "]", getWidth() / 2 - 43, getWidth() - 5);

			switch (selectField.getbuttonCount()) {
			case 0:
				button(0);
				break;
			case 1:
				button(1);
				break;
			case 2:
				button(2);
				break;
			case 3:
				button(3);
				break;
			case 4:
				button(4);
				break;
			}
		}
	}

	// 버튼 관리
	public void button(int num) {
		button1.setBounds(getWidth() / 6, getHeight() / 2 + 130, 100, 50);
		button2.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 130, 100, 50);
		button3.setBounds(getWidth() / 6, getHeight() / 2 + 210, 100, 50);
		button4.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 210, 100, 50);
		button1Label.setBounds(getWidth() / 6, getHeight() / 2 + 130, 100, 50);
		button2Label.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 130, 100, 50);
		button3Label.setBounds(getWidth() / 6, getHeight() / 2 + 210, 100, 50);
		button4Label.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 210, 100, 50);
		if (num == 0) {
			button1.setVisible(false);
			button1Label.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
		} else if (num == 1) {
			button1.setVisible(true);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
		} else if (num == 2) {
			button1.setVisible(true);
			button2.setVisible(true);
			button3.setVisible(false);
			button4.setVisible(false);
		} else if (num == 3) {
			button1.setVisible(true);
			button2.setVisible(true);
			button3.setVisible(true);
			button4.setVisible(false);
		} else if (num == 4) {
			button1.setVisible(true);
			button1Label.setVisible(true);
			button2.setVisible(true);
			button3.setVisible(true);
			button4.setVisible(true);
		}
	}

	//버튼 생성
	public void createLabel() {
		selectField = gameGUI.getData().map.field[gameGUI.getMainCanvas().select.x][gameGUI.getMainCanvas().select.y];
		button1Label.setHorizontalAlignment(SwingConstants.CENTER);
		button1Label.setVerticalAlignment(SwingConstants.CENTER);
		button1Label.setText(selectField.getButtonName(1));
		add(button1Label);
		button2Label.setHorizontalAlignment(SwingConstants.CENTER);
		button2Label.setVerticalAlignment(SwingConstants.CENTER);
		button2Label.setText(selectField.getButtonName(2));
		add(button2Label);
		button3Label.setHorizontalAlignment(SwingConstants.CENTER);
		button3Label.setVerticalAlignment(SwingConstants.CENTER);
		button3Label.setText(selectField.getButtonName(3));
		add(button3Label);
		button4Label.setHorizontalAlignment(SwingConstants.CENTER);
		button4Label.setVerticalAlignment(SwingConstants.CENTER);
		button4Label.setText(selectField.getButtonName(4));
		add(button4Label);
		button1.setIcon(buttonIcon);
		button2.setIcon(buttonIcon);
		button3.setIcon(buttonIcon);
		button4.setIcon(buttonIcon);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		
		for(int i =0;i<dir.length;i++) {
			dir[i] = new JLabel();
			dir[i].setIcon(dirIcon);
			dir[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// 클릭 시 수행할 동작을 여기에 추가
					System.out.println("1");
				}
			});
			add(dir[i]);
		}
	}
	
	

	//방향 선택 화면 메인
	public void dirmain(Graphics g) {
		if(dirActivate == true) {
			for(int i =0;i<dir.length;i++) {
				dir[0].setBounds(getWidth() / 6, getHeight() / 2 + 130, 100, 50);
				dir[1].setBounds(getWidth() / 2 + 35, getHeight() / 2 + 130, 100, 50);
				dir[2].setBounds(getWidth() / 6, getHeight() / 2 + 210, 100, 50);
				dir[3].setBounds(getWidth() / 2 + 35, getHeight() / 2 + 210, 100, 50);
				dir[i].setVisible(true);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("x: " + mouse.x + " | y: " + mouse.y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
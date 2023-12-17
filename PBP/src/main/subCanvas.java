package main;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import field.field;
import unit.unit;

public class subCanvas extends JPanel implements Runnable, MouseListener {
	protected boolean stop;
	protected Point mouse = new Point();
	int x, y;
	protected Thread worker;
	private field selectField;
	private JLabel button1 = new JLabel();
	private JLabel button2 = new JLabel();
	private JLabel button3 = new JLabel();
	private JLabel button4 = new JLabel();
	private JLabel button1Label = new JLabel();
	private JLabel button2Label = new JLabel();
	private JLabel button3Label = new JLabel();
	private JLabel button4Label = new JLabel();
	private JLabel populationLabel = new JLabel();
	private JLabel buttonTitleLabel = new JLabel();
	private JLabel buttonTitle = new JLabel();
	private JLabel dir[] = new JLabel[4];
	private JLabel dirDisable[] = new JLabel[4];
	private JLabel dirTitle = new JLabel();
	private JLabel dirTitleLabel = new JLabel();
	private Font fontTitle = new Font("굴림", Font.BOLD, 27);
	private Font fontButton = new Font("굴림", Font.BOLD, 20);
	private BufferedImage offScreenImage;
	private ImageIcon buttonIcon = new ImageIcon(
			new ImageIcon("images\\subCanvas\\button.png").getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH));
	private ImageIcon backGround = new ImageIcon(new ImageIcon("images\\subCanvas\\background.png").getImage()
			.getScaledInstance(400, 640, Image.SCALE_SMOOTH));
	private ImageIcon preview = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\preview.png").getImage()
			.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	private ImageIcon buttonTitleIcon, dirTitleIcon;
	private ImageIcon dir1Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowUp.png").getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dir2Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowRight.png").getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dir3Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowDown.png").getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dir4Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowLeft.png").getImage()
			.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dirDisable1Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowDisableUp.png")
			.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dirDisable2Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowDisableRight.png")
			.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dirDisable3Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowDisableDown.png")
			.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
	private ImageIcon dirDisable4Icon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\arrowDisableLeft.png")
			.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

	subCanvas() {
		addMouseListener(this);
		createLabel();
		setLayout(null);

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x, gameGUI.getMainCanvas().select.y)
						.button1();
			}
		});
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x, gameGUI.getMainCanvas().select.y)
						.button2();
			}
		});
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x, gameGUI.getMainCanvas().select.y)
						.button3();
			}
		});
		button4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 클릭 시 수행할 동작을 여기에 추가
				gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x, gameGUI.getMainCanvas().select.y)
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

		selectField = gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x,
				gameGUI.getMainCanvas().select.y);

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

	// 라벨 생성
	public void createLabel() {
		selectField = gameGUI.getData().map.getField(gameGUI.getMainCanvas().select.x,
				gameGUI.getMainCanvas().select.y);
		button1Label.setHorizontalAlignment(SwingConstants.CENTER);
		button1Label.setVerticalAlignment(SwingConstants.CENTER);
		button1Label.setText(selectField.getButtonName(1));
		button2Label.setHorizontalAlignment(SwingConstants.CENTER);
		button2Label.setVerticalAlignment(SwingConstants.CENTER);
		button2Label.setText(selectField.getButtonName(2));
		button3Label.setHorizontalAlignment(SwingConstants.CENTER);
		button3Label.setVerticalAlignment(SwingConstants.CENTER);
		button3Label.setText(selectField.getButtonName(3));
		button4Label.setHorizontalAlignment(SwingConstants.CENTER);
		button4Label.setVerticalAlignment(SwingConstants.CENTER);
		button4Label.setText(selectField.getButtonName(4));

		add(button1Label);
		add(button2Label);
		add(button3Label);
		add(button4Label);

		button1.setIcon(buttonIcon);
		button2.setIcon(buttonIcon);
		button3.setIcon(buttonIcon);
		button4.setIcon(buttonIcon);
		add(button1);
		add(button2);
		add(button3);
		add(button4);

		setTitleIcon();
		buttonTitleLabel.setFont(fontTitle);
		buttonTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		buttonTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(buttonTitleLabel);

		populationLabel.setFont(fontButton);
		populationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		populationLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(populationLabel);

		buttonTitle.setIcon(buttonTitleIcon);
		add(buttonTitle);

		dirTitleLabel.setFont(fontTitle);
		dirTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dirTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(dirTitleLabel);

		dirTitle.setIcon(dirTitleIcon);
		add(dirTitle);

		for (int i = 0; i < dir.length; i++) {
			int dirnum = i;
			dir[i] = new JLabel();
			dir[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					while (true) {
						// 다이얼로그를 통해 숫자 입력 받기
						String input = JOptionPane.showInputDialog(null,
								"숫자를 입력하세요: (최대 : " + selectField.getUnitCount() + ")", "숫자 입력",
								JOptionPane.QUESTION_MESSAGE);

						// 입력이 null이 아니고 빈 문자열이 아닌 경우에만 처리
						if (input != null && !input.trim().isEmpty()) {
							try {
								// 문자열을 숫자로 변환하여 변수에 저장
								int inputValue = Integer.parseInt(input);

								if (selectField.getUnitCount() >= inputValue) {
									gameGUI.getData().dirnum = dirnum;
									gameGUI.getData().inputValue = inputValue;
									break; // 올바른 값이 입력되었으므로 반복문 종료
								} else {
									JOptionPane.showMessageDialog(null,
											String.valueOf(selectField.getUnitCount()) + " 이하 소환가능합니다", "오류",
											JOptionPane.WARNING_MESSAGE);
								}

							} catch (NumberFormatException ex) {
								// 숫자로 변환할 수 없는 경우 예외 처리
								JOptionPane.showMessageDialog(null, "올바른 숫자를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							// 사용자가 취소 버튼을 눌렀거나 빈 문자열을 입력한 경우
							break; // 반복문 종료
						}
					}
					selectField.setFieldActivate(true);
					selectField.setDirActivate(false);
				}
			});
			dirDisable[i] = new JLabel();
			dirDisable[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// 클릭 시 수행할 동작을 여기에 추가]
					System.out.println("그쪽으로는 소환할 수 없겠는데요?");
					selectField.setFieldActivate(true);
					selectField.setDirActivate(false);
				}
			});
			add(dir[i]);
			add(dirDisable[i]);
		}
		dir[0].setIcon(dir1Icon);
		dir[1].setIcon(dir2Icon);
		dir[2].setIcon(dir3Icon);
		dir[3].setIcon(dir4Icon);
		dirDisable[0].setIcon(dirDisable1Icon);
		dirDisable[1].setIcon(dirDisable2Icon);
		dirDisable[2].setIcon(dirDisable3Icon);
		dirDisable[3].setIcon(dirDisable4Icon);
	}

	// 버튼 메인 화면
	public void fieldmain(Graphics g) {
		if (selectField.getFieldActivate() == true) {
			button1.setBounds(getWidth() / 6, getHeight() / 2 + 130, 100, 50);
			button2.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 130, 100, 50);
			button3.setBounds(getWidth() / 6, getHeight() / 2 + 210, 100, 50);
			button4.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 210, 100, 50);
			button1Label.setBounds(getWidth() / 6, getHeight() / 2 + 130, 100, 50);
			button2Label.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 130, 100, 50);
			button3Label.setBounds(getWidth() / 6, getHeight() / 2 + 210, 100, 50);
			button4Label.setBounds(getWidth() / 2 + 35, getHeight() / 2 + 210, 100, 50);
			buttonTitleLabel.setText("[" + selectField.name + "]");
			buttonTitle.setBounds(getWidth() / 6 + 70, getHeight() / 2 + 40, 130, 50);
			buttonTitleLabel.setBounds(getWidth() / 6 + 85, getHeight() / 2 + 42, 100, 50);

			populationLabel.setText("병력 수 : " + selectField.getUnitCount());
			populationLabel.setBounds(getWidth() / 2 - 70, getHeight() / 2 - 20, 150, 50);

			button1Label.setFont(fontButton);
			button2Label.setFont(fontButton);
			button3Label.setFont(fontButton);
			button4Label.setFont(fontButton);

			button1Label.setText(selectField.getButtonName(1));
			button2Label.setText(selectField.getButtonName(2));
			button3Label.setText(selectField.getButtonName(3));
			button4Label.setText(selectField.getButtonName(4));

			buttonTitle.setVisible(true);
			buttonTitleLabel.setVisible(true);
			populationLabel.setVisible(true);

			if (selectField.getbuttonCount() == 0) {
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button1Label.setVisible(false);
				button2Label.setVisible(false);
				button3Label.setVisible(false);
				button4Label.setVisible(false);
			} else if (selectField.getbuttonCount() == 1) {
				button1.setVisible(true);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button1Label.setVisible(true);
				button2Label.setVisible(false);
				button3Label.setVisible(false);
				button4Label.setVisible(false);
			} else if (selectField.getbuttonCount() == 2) {
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(false);
				button4.setVisible(false);
				button1Label.setVisible(true);
				button2Label.setVisible(true);
				button3Label.setVisible(false);
				button4Label.setVisible(false);
			} else if (selectField.getbuttonCount() == 3) {
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(false);
				button1Label.setVisible(true);
				button2Label.setVisible(true);
				button3Label.setVisible(true);
				button4Label.setVisible(false);
			} else if (selectField.getbuttonCount() == 4) {
				button1.setVisible(true);
				button1Label.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				button1Label.setVisible(true);
				button2Label.setVisible(true);
				button3Label.setVisible(true);
				button4Label.setVisible(true);
			}
		} else {
			button1.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			buttonTitle.setVisible(false);
			button1Label.setVisible(false);
			button2Label.setVisible(false);
			button3Label.setVisible(false);
			button4Label.setVisible(false);
			buttonTitleLabel.setVisible(false);
		}
	}

	// 방향 선택 메인 화면
	public void dirmain(Graphics g) {
		populationLabel.setText("병력 수 : " + selectField.getUnitCount());
		if (selectField.getDirActivate() == true) {
			dirTitle.setBounds(getWidth() / 6 - 15, getHeight() / 2 + 40, 300, 50);
			dirTitleLabel.setBounds(getWidth() / 6 - 15, getHeight() / 2 + 42, 300, 50);

			dirTitleLabel.setText("유닛 이동 방향 선택");
			dirTitle.setVisible(true);
			dirTitleLabel.setVisible(true);
			dir[0].setBounds(getWidth() / 2 - 25, getHeight() / 2 + 120, 50, 50);
			dir[1].setBounds(getWidth() / 2 + 30, getHeight() / 2 + 170, 50, 50);
			dir[2].setBounds(getWidth() / 2 - 25, getHeight() / 2 + 220, 50, 50);
			dir[3].setBounds(getWidth() / 2 - 80, getHeight() / 2 + 170, 50, 50);

			dirDisable[0].setBounds(getWidth() / 2 - 25, getHeight() / 2 + 120, 50, 50);
			dirDisable[1].setBounds(getWidth() / 2 + 30, getHeight() / 2 + 170, 50, 50);
			dirDisable[2].setBounds(getWidth() / 2 - 25, getHeight() / 2 + 220, 50, 50);
			dirDisable[3].setBounds(getWidth() / 2 - 80, getHeight() / 2 + 170, 50, 50);

			for (int i = 0; i < dir.length; i++) {
				dir[i].setVisible(true);
				if (gameGUI.getMainCanvas().select.x == 0) {
					dir[3].setVisible(false);
					dirDisable[3].setVisible(true);
				}
				if (gameGUI.getMainCanvas().select.y == 0) {
					dir[0].setVisible(false);
					dirDisable[0].setVisible(true);
				}
				if (gameGUI.getMainCanvas().select.x == gameGUI.getData().map.getPosition().x - 1) {
					dir[1].setVisible(false);
					dirDisable[1].setVisible(true);
				}
				if (gameGUI.getMainCanvas().select.y == gameGUI.getData().map.getPosition().y - 1) {
					dir[2].setVisible(false);
					dirDisable[2].setVisible(true);
				}
			}
		} else {
			dirTitle.setVisible(false);
			dirTitleLabel.setVisible(false);
			for (int i = 0; i < dir.length; i++) {
				dir[i].setVisible(false);
				dirDisable[i].setVisible(false);
			}
		}
	}

	public void unitSummon(int hp, int dir, int x, int y,int selectX, int selectY) {
		System.out.println("아");
		if (gameGUI.getData().map.getField(selectX,selectY).getsummonCooldown() == false) {
			unit unit = new unit(gameGUI.getData().map.getField(selectX,selectY).getUnitType(), gameGUI.getData().map.getField(selectX,selectY).getOwner());
			unit.setPosition(x, y);
			unit.setSpawnPoint(selectX, selectX);
			unit.setAttack(gameGUI.getData().getPlayer(gameGUI.getData().map.getField(selectX, selectY).getOwner()).getBrood().getAD());
			gameGUI.getData().map.getField(selectX,selectY).unitCount -= hp;
			unit.setHp(hp);
			unit.speed = 1;
			unit.dir = dir;
			unit.setOwner(gameGUI.getData().map.getField(selectX,selectY).getOwner());
			unit.unitmove(dir);
			unit.start();
			gameGUI.getData().addUnit(unit);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setTitleIcon() {
		buttonTitleIcon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\button.png").getImage()
				.getScaledInstance(130, 50, Image.SCALE_SMOOTH));
		dirTitleIcon = new ImageIcon(new ImageIcon("images\\\\subCanvas\\\\dirTitle.png").getImage()
				.getScaledInstance(300, 50, Image.SCALE_SMOOTH));
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
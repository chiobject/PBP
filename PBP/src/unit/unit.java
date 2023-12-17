package unit;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import brood.elf;
import field.field;
import main.gameGUI;
import main.sound;

import java.util.Timer;
import java.util.TimerTask;

public class unit extends JPanel implements KeyListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int unitType;
	int attack;
	private int x = 50;
	private int y = 50;
	private Point position = new Point(x, y);
	public int dir = 0;
	public int diameter = 50;
	private int hp;
	private boolean running = false;
	private Thread worker;
	public int speed;
	Image charactorLeft, charactorRight;
	int sel;
	private boolean move;
	private int owner;
	private Point spawnPoint = new Point(0,0);
	private sound warSound;

	public unit(int unitType, int owner) {
		this.unitType = unitType;
		this.owner = owner;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setSpeed(int Speed) {
		this.speed = Speed;
	}

	public void changeHp(int hp) {
		this.hp += hp;
	}

	public int getHp() {
		return hp;
	}

	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}

	public void start() {
		sel = 0;
		speed = gameGUI.getData().getPlayer(owner).getBrood().getSpeed();
		attack = gameGUI.getData().getPlayer(owner).getBrood().getAD();
		if (!running) {
			running = true;
			move = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		running = false;
	}

	public void paint(Graphics g) {
		if (move == false) {
			sel = 0;
		} else {
			if (move == true && sel < 2) {
				sel++;
			} else {
				sel = 0;
			}
		}

		// 좌측이나 위로 올라갈 경우
		if (dir % 3 == 0) {
			g.drawImage(charactorLeft, position.x + 10, position.y + 5, position.x + 52, position.y + 58, sel * 42, 0,
					sel * 42 + 42, 53, this);

		}

		// 우측이나 아래로 올라갈 경우
		else {
			g.drawImage(charactorRight, position.x + 10, position.y + 5, position.x + 52, position.y + 58, sel * 42, 0,
					sel * 42 + 42, 53, this);
		}

	}

	@Override
	public void run() {
		while (running) {
			charactorLeft = Toolkit.getDefaultToolkit()
					.getImage(gameGUI.getData().getPlayer(owner).getBrood().getUnitLeft());
			charactorRight = Toolkit.getDefaultToolkit()
					.getImage(gameGUI.getData().getPlayer(owner).getBrood().getUnitRight());
			try {
				// 유닛 이동 가능
				setMove(true);
				if (isUnitCollision() == true) {
				} else {
					isFieldCollision();
				}
				isUnitCollision();
				// 유닛 이동
				if (move == true) {
					unitmove(dir);
				}

				// hp0이하일 시 유닛 사망
				if (hp <= 0) {
					gameGUI.getData().removeUnit(this);
					stop();
				}
				worker.sleep(speed);
			} catch (Exception e) {
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

	// 유닛 - 유닛 충돌 감지
	public boolean isUnitCollision() {
		int collision = 20;
		for (unit allUnits : gameGUI.getData().units) {
			int deltaX = Math.abs(this.getPosition().x - allUnits.getPosition().x);
			int deltaY = Math.abs(this.getPosition().y - allUnits.getPosition().y);
			if (allUnits != this && deltaX <= collision && deltaY <= collision) {
				setMove(false);

				if (this.getOwner() != allUnits.getOwner()) {
					System.out.println("적 교집합 상대 Hp : " + allUnits.getHp());
					allUnits.changeHp(-attack);
					try {
						warSound = new sound("sounds//싸움1.wav", -30);
						warSound.play();
						worker.sleep(200);
						warSound.stop();
						warSound.close();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// 수정 필요
					System.out.println("아군 교집합");
					allUnits.hp += this.hp;
					gameGUI.getData().removeUnit(this);
					stop();
					setMove(false);
				}
				return true;
			}
		}
		return false;
	}

	// 유닛 - 필드 충돌 감지
	public boolean isFieldCollision() {
		int collision = 28;
		for (int i = 0; i < gameGUI.getData().map.getPosition().x; i++) {
			for (int j = 0; j < gameGUI.getData().map.getPosition().y; j++) {
				int deltaX = Math.abs(this.getPosition().x - gameGUI.getMainCanvas().getFieldPosition(i, j).x + 30);
				int deltaY = Math.abs(this.getPosition().y - gameGUI.getMainCanvas().getFieldPosition(i, j).y + 31);
				if (deltaX <= collision && deltaY <= collision && (spawnPoint.x != i || spawnPoint.y != j)
						&& gameGUI.getData().map.getField(i, j).getType() != 0) {
					System.out.println(spawnPoint.x + "+/+" + spawnPoint.y);
					// 빈 필드일 때
					if (gameGUI.getData().map.getField(i, j).getUnitCount() <= 0) {
						gameGUI.getData().map.getField(i, j).setOwner(owner);
						gameGUI.getData().map.getField(i, j).setIsProduction(false);
						gameGUI.getData().map.getField(i, j).setIsBuilding(true);
						gameGUI.getData().map.getField(i, j).setUnitCount(hp);
						gameGUI.getData().removeUnit(this);
						stop();
					}
					// 상대 필드일 때
					else if (gameGUI.getData().map.getField(i, j).getOwner() != owner) {
						gameGUI.getData().map.getField(i, j).changeUnitCount(-attack);
						gameGUI.getData().map.getField(i, j).setIsProduction(false);
						if (gameGUI.getData().map.getField(i, j).getOwner() != 0) {
							hp -= gameGUI.getData().getPlayer(gameGUI.getData().map.getField(i, j).getOwner())
									.getBrood().getAD();
							System.out.println(hp);
						}
						try {
							warSound = new sound("sounds//싸움1.wav", -30);
							warSound.play();
							worker.sleep(200);
							warSound.stop();
							warSound.close();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					// 본인 필드일 때
					else if (gameGUI.getData().map.getField(i, j).getOwner() == owner) {

						if (hp + gameGUI.getData().map.getField(i, j).getUnitCount() > gameGUI.getData().map
								.getField(i, j).getUnitMax()) {
							if (gameGUI.getData().map.getField(i, j).getUnitMax() > gameGUI.getData().map.getField(i, j)
									.getUnitCount()) {
								hp -= (gameGUI.getData().map.getField(i, j).getUnitMax()
										- gameGUI.getData().map.getField(i, j).getUnitCount());
								gameGUI.getData().map.getField(i, j)
										.setUnitCount(gameGUI.getData().map.getField(i, j).getUnitMax());
							}
							System.out.println("성에 유닛 수가 꽉 차서 유닛이 들어갈 수 없습니다.");
						} else {
							gameGUI.getData().map.getField(i, j).changeUnitCount(hp);
							gameGUI.getData().removeUnit(this);
							stop();
						}
					}
					else {
						gameGUI.getData().map.getField(i, j).setIsProduction(true);
					}

					setMove(false);
					return true;
				}
			}
		}
		return false;
	}

	public void setSpawnPoint(int x, int y) {
		spawnPoint.x = x;
		spawnPoint.y = y;
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

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getSel() {
		return sel;
	}

	public void setHp(int hp) {
		// TODO Auto-generated method stub
		this.hp = hp;
	}
}

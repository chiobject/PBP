package main;

import field.blank;
import field.field;
import field.mainBase;
import field.neutralBase;
import field.subBase;
import java.awt.Point;

public class map extends Area {
	int type = 0; // 0: 평야 | 1: 사막 | 2: 설원 | 3:유적지
	private int max_x = 9;
	private int max_y = 9;
	private Point position = new Point(max_x, max_y);

	private field[][] field = new field[max_x][max_y];

	void reset() {
		for (int x = 0; x < max_x; x++) {
			for (int y = 0; y < max_y; y++) {
				field[x][y] = new blank();
			}
		}
	}

	void create() {
		for (int i = 0; i < max_x; i++) {
			for (int j = 0; j < max_y; j++) {
				field[i][j].setOwner("null");
				if (i % 2 == 1 || j % 2 == 1) {
					field[i][j] = new blank();
				} else if ((i % (max_x - 1) == 0 && j % (max_y - 1) == 0)) {
					field[i][j] = new mainBase();
				} else if ((i == (max_x - 1) / 2 && j % (max_y - 1) == 0)
						|| (i % (max_x - 1) == 0) && j == (max_y - 1) / 2
						|| (i == (max_x - 1) / 2 && j == (max_y - 1) / 2)) {
					field[i][j] = new subBase();
				} else {
					field[i][j] = new neutralBase();	
				}
				field[i][j].start();
			}
		}
	}
	public Point getPosition() {
		return position;
	}
	public field getField(int x, int y) {
		return field[x][y];
	}
}

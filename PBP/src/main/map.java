package main;

import field.blank;
import field.field;
import field.mainBase;
import field.neutralBase;
import field.subBase;

public class map extends Area {
	int type = 0; // 0: 평야 | 1: 사막 | 2: 설원 | 3:유적지
	public int max_x = 9;
	public int max_y = 9;

	public field[][] field = new field[max_x][max_y];

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
		field[0][0].setFieldOwner(1);
		field[max_x-1][max_y-1].setFieldOwner(2);
	}
}

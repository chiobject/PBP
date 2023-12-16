package main;

import java.util.ArrayList;
import java.util.List;
import unit.unit;

public class data {
	public List<unit> units = new ArrayList<>();
	public map map = new map();

	public player player0 = new player(0,1);
	public player player1 = new player(1,2);
	public player player2 = new player(2,3);
	
	public data() {
	}

	public void addUnit(unit unit) {
		units.add(unit);
	}
	
	public void removeUnit(unit unit) {
		units.remove(unit);
	}
	public player getPlayer(int playerNum) {
		if(playerNum == 1) {
			return player1;
		}
		else{
			return player2;
		}
	}
	public int getmainPlainCount(int owner) {
		int count = 0;
		for (int i = 0; i < map.getPosition().x; i++) {
			for (int j = 0; j < map. getPosition().y; j++) {
				if(map.getField(i,j).getOwner()== owner) {
					count += 1;
				}
			}
		}
		return count;
	}
}
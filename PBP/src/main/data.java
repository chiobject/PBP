package main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import unit.unit;

public class data {
	public List<unit> units = new CopyOnWriteArrayList<>();
	public List<player> playerList = new CopyOnWriteArrayList<>();
	public map map = new map();
	
	public player player0 = new player(1);
	public player player1 = new player(2);
	public player player2 = new player(3);
	public int inputValue, dirnum;
	
	public data() {
		playerList.add(player0);
		playerList.add(player1);
		playerList.add(player2);
	}

	public void addUnit(unit unit) {
		units.add(unit);
	}
	
	public void removeUnit(unit unit) {
		units.remove(unit);
	}
//	public player getPlayer(String owner) {
//		if(owner == 1) {
//			return player1;
//		}
//		else{
//			return player2;
//		}
//	}
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

	public player getPlayer(int owner) {
		if(owner == 1){
			return playerList.get(1);
		}
		else if (owner == 2) {
			return playerList.get(2);
		}
		else return playerList.get(0);
	}
}
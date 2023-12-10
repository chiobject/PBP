package main;

import java.util.ArrayList;
import java.util.List;
import unit.unit;

public class data {
	public List<unit> units = new ArrayList<>();
	public map map = new map();
	public player player = new player();

	public data() {
	}

	public void addUnit(unit unit) {
		units.add(unit);
	}

}
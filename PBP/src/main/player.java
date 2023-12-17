package main;

import brood.brood;
import brood.elf;
import brood.goblin;
import brood.human;

public class player {
	private int player;
	private brood brood;
	private boolean isOwner;
	
	public player(int brood){
		this.player = player;
		if(brood == 1) {
			this.brood = new elf();
		}
		else if(brood == 2) {
			this.brood = new goblin();
		}
		else if(brood == 3) {
			this.brood = new human();
		}
	}
	
	
	
	public int getplayer() {
		return player;
	}
	public brood getBrood() {
		return brood;
	}
	public void setplayer(int player) {
		this.player = player;
	}

	public void setbrood(int brood) {
		if(brood == 1) {
			this.brood = new human();
		}
		else if(brood == 2) {
			this.brood = new elf();
		}
		else if(brood == 3) {
			this.brood = new goblin();
		}
	}
	public void setisOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	public boolean getUUID() {
		return isOwner;
	}
}

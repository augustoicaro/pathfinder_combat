package com.dmtprogramming.pathfindercombat.modifier;

public class Enlarge extends ModifierBase {

	public int applyHit(int v) {
		return v - 1;
	}
	
	public int applyStr(int v) {
		return v + 2;
	}
	
	public int applySize(int v) {
		return v + 1;
	}

	@Override
	public String name() {
		return "Enlarge";
	}

}

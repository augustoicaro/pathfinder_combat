package com.dmtprogramming.pathfindercombat.modifier;

public class Defensive extends ModifierBase {

	@Override
	protected int applyAC(int s) {
		return s + 4;
	}
	
	@Override
	public String name() {
		return "Defensive";
	}
}

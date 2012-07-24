package com.dmtprogramming.pathfindercombat.modifier;

public class Dazzled extends ModifierBase {

	@Override
	protected int applyHit(int s) {
		return s - 1;
	}
	
	@Override
	public String name() {
		return "Dazzled";
	}
}

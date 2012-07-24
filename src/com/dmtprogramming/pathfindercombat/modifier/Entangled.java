package com.dmtprogramming.pathfindercombat.modifier;

public class Entangled extends ModifierBase {

	@Override
	protected int applyHit(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyDex(int s) {
		return s - 4;
	}
	
	@Override
	public String name() {
		return "Entangled";
	}
}

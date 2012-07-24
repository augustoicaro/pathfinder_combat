package com.dmtprogramming.pathfindercombat.modifier;

public class Helpless extends ModifierBase {
	
	@Override
	protected int applyDex(int s) {
		return -999;
	}
	
	@Override
	public String name() {
		return "Helpless";
	}
}

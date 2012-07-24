package com.dmtprogramming.pathfindercombat.modifier;

public class Blinded extends ModifierBase {

	@Override
	protected int applyAC(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyDexAC(int s) {
		return 0;
	}
	
	@Override
	public String name() {
		return "Blinded";
	}
}

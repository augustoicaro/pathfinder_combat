package com.dmtprogramming.pathfindercombat.modifier;

public class Stunned extends ModifierBase {
	
	@Override
	protected int applyDexAC(int s) {
		return 0;
	}
	
	@Override
	protected int applyAC(int s) {
		return s - 2;
	}
	
	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		return "Stunned";
	}
}

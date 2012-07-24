package com.dmtprogramming.pathfindercombat.modifier;

public class Paralyzed extends ModifierBase {

	@Override
	protected int applyStr(int s) {
		return -999;
	}
	
	@Override
	protected int applyDex(int s) {
		return -999;
	}
	
	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		return "Paralyzed";
	}
}

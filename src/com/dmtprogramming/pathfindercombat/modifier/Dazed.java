package com.dmtprogramming.pathfindercombat.modifier;

public class Dazed extends ModifierBase {

	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		return "Dazed";
	}
}

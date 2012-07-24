package com.dmtprogramming.pathfindercombat.modifier;

public class Staggered extends ModifierBase {

	@Override
	protected String applyActions(String s) {
		return "single";
	}
	
	@Override
	public String name() {
		return "Staggered";
	}
}

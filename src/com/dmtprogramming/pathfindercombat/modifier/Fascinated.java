package com.dmtprogramming.pathfindercombat.modifier;

public class Fascinated extends ModifierBase {

	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		return "Fascinated";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class Nauseated extends ModifierBase {

	@Override
	protected String applyActions(String s) {
		return "move";
	}
	
	@Override
	public String name() {
		return "Nauseated";
	}
}

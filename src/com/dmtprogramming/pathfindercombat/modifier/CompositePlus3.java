package com.dmtprogramming.pathfindercombat.modifier;

public class CompositePlus3 extends ModifierBase {

	@Override
	public String classes() {
		return "Ranger";
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 3;
	}

	@Override
	public String name() {
		return "Comp +3";
	}
}

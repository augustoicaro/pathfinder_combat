package com.dmtprogramming.pathfindercombat.modifier;

public class Smite extends ModifierBase {

	@Override
	public String classes() {
		return "Paladin";
	}
	
	@Override
	public int applyHit(int s) {
		return s += character().getChaMod();
	}
	
	@Override
	public int applyDamage(int s) {
		return s += character().getLevel();
	}

	@Override
	public String name() {
		return "Smite";
	}
	
}

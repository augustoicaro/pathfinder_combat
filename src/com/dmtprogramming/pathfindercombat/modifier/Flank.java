package com.dmtprogramming.pathfindercombat.modifier;

public class Flank extends ModifierBase {
	
	public String range() {
		return _r_melee;
	}
	
	public int applyHit(int value) {
		return value += 2;
	}

	@Override
	public String name() {
		return "Flank";
	}
}

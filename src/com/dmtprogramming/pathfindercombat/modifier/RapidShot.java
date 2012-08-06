package com.dmtprogramming.pathfindercombat.modifier;

public class RapidShot extends ModifierBase {

	public String range() {
		return _r_ranged;
	}

	@Override
	public int applyHit(int s) {
		return s - 2;
	}

	@Override
	public int applyExtraAttack(int v) {
		return v + 1;
	}
	
	@Override
	public String name() {
		return "Rapid Shot";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class DeadlyAim extends ModifierBase {

	public String range() {
		return _r_ranged;
	}

	@Override
	public int applyHit(int s) {
		int mod = (character().getBAB() / 4) + 1;
		return s -= mod;
	}
	
	@Override
	public int applyDamage(int s) {
		int mod = (character().getBAB() / 4) + 1;
		return s += (mod * 2);
	}

	@Override
	public String name() {
		return "Deadly Aim";
	}

}

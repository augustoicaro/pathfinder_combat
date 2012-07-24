package com.dmtprogramming.pathfindercombat.modifier;

public class Plus1Damage extends ModifierBase {

	@Override
	public int applyDamage(int s) {
		return s += 1;
	}

	@Override
	public String name() {
		return "+1 Dmg";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2Damage extends ModifierBase {

	@Override
	public int applyDamage(int s) {
		return s += 2;
	}

	@Override
	public String name() {
		return "+2 Dmg";
	}
}

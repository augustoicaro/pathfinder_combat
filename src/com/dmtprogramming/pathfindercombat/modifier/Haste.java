package com.dmtprogramming.pathfindercombat.modifier;

public class Haste extends ModifierBase {

	@Override
	public int applyHit(int v) {
		return v += 1;
	}
	
	@Override
	public int applyExtraAttack(int v) {
		return v += 1;
	}

	@Override
	public String name() {
		return "Haste";
	}
}

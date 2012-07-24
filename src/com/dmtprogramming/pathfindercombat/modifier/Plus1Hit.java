package com.dmtprogramming.pathfindercombat.modifier;

public class Plus1Hit extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 1;
	}

	@Override
	public String name() {
		return "+1 Hit";
	}
}

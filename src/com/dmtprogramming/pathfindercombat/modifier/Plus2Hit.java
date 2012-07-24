package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2Hit extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 2;
	}

	@Override
	public String name() {
		return "+2 Hit";
	}
}

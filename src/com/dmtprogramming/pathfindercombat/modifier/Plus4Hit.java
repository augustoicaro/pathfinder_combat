package com.dmtprogramming.pathfindercombat.modifier;

public class Plus4Hit extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 4;
	}

	@Override
	public String name() {
		return "+4 Hit";
	}
}

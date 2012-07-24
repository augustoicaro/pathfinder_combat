package com.dmtprogramming.pathfindercombat.modifier;

public class Plus4Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}

	@Override
	public int applyStr(int s) {
		return s += 4;
	}

	@Override
	public String name() {
		return "+4 Str";
	}
}

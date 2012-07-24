package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}
	
	@Override
	public int applyStr(int s) {
		return s += 2;
	}

	@Override
	public String name() {
		return "+2 Str";
	}

}

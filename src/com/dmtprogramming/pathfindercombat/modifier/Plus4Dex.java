package com.dmtprogramming.pathfindercombat.modifier;

public class Plus4Dex extends ModifierBase {

	public String range() {
		return _r_ranged;
	}
	
	@Override
	public int applyDex(int s) {
		return s + 4;
	}

	@Override
	public int applyDexMod(int s) {
		return s + 2;
	}
	
	@Override
	public String name() {
		return "+4 Dex";
	}

}

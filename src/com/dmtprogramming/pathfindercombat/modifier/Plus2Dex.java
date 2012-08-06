package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2Dex extends ModifierBase {

	public String range() {
		return _r_ranged;
	}
	
	@Override
	public int applyDex(int s) {
		return s + 2;
	}
	
	@Override
	public int applyDexMod(int s) {
		return s + 1;
	}

	@Override
	public String name() {
		return "+2 Dex";
	}

}

package com.dmtprogramming.pathfindercombat.modifier;

public class Deafened extends ModifierBase {

	@Override
	protected int applyInit(int s) {
		return s - 4;
	}
	
	@Override
	public String name() {
		return "Deafened";
	}
}

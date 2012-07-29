package com.dmtprogramming.pathfindercombat.modifier;

public class Critical extends ModifierBase {
	
	@Override
	public int applyCriticalDamage(int v) {
		return v;
	}
	
	@Override
	public String applyCriticalDamageDice(String s) {
		return s;
	}
	
	@Override
	public String name() {
		return "Critical";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class Critical extends ModifierBase {
	
	@Override
	public int applyCriticalDamage(int v) {
		return character().applyWeaponPlusCritical(v);
	}
	
	@Override
	public String applyCriticalDamageDice(String s) {
		return character().applyWeaponDiceCritical(s);
	}
	
	@Override
	public String name() {
		return "Critical";
	}
}

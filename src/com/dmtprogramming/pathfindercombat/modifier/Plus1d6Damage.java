package com.dmtprogramming.pathfindercombat.modifier;

public class Plus1d6Damage extends ModifierBase {

	@Override
	public String applyDamageDice(String s) {
		if (!s.equals("")) {
			s = s.concat(" + ");
		}
		return s.concat("1d6");
	}

	@Override
	public String name() {
		return "+1d6 Dmg";
	}
}

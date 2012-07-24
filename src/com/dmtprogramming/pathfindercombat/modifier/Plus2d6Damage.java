package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2d6Damage extends ModifierBase {
	
	@Override
	public String applyDamageDice(String s) {
		if (!s.equals("")) {
			s = s.concat(" + ");
		}
		return s.concat("2d6");
	}

	@Override
	public String name() {
		return "+2d6 Dmg";
	}
}

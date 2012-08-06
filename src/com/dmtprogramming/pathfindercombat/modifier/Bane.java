package com.dmtprogramming.pathfindercombat.modifier;

public class Bane extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 2;
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 2;
	}

	@Override
	public String applyDamageDice(String s) {
		if (!s.equals("")) {
			s = s.concat(" + ");
		}
		return s.concat("2d6");
	}
	
	@Override
	public String name() {
		return "Bane";
	}
}

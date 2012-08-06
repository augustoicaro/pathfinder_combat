package com.dmtprogramming.pathfindercombat.modifier;

public class FavoredEnemy4 extends ModifierBase {

	@Override
	public String classes() {
		return "Ranger";
	}
	
	@Override
	public int applyHit(int s) {
		return s += 4;
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 4;
	}
	
	@Override
	public String name() {
		return "Favored 4";
	}
}

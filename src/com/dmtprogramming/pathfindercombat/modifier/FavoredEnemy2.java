package com.dmtprogramming.pathfindercombat.modifier;

public class FavoredEnemy2 extends ModifierBase {

	@Override
	public String classes() {
		return "Ranger";
	}
	
	@Override
	public int applyHit(int s) {
		return s += 2;
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 2;
	}
	
	@Override
	public String name() {
		return "Favored 2";
	}
}

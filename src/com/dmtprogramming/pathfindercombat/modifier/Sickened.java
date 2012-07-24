package com.dmtprogramming.pathfindercombat.modifier;

public class Sickened extends ModifierBase {

	@Override
	protected int applyDamage(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyHit(int s) {
		return s - 2;
	}
	
	@Override
	protected int applySaves(int s) {
		return s - 2;
	}
	
	@Override
	protected int applySkillChecks(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyAbilityChecks(int s) {
		return s - 2;
	}
	
	@Override
	public String name() {
		return "Sickened";
	}
}

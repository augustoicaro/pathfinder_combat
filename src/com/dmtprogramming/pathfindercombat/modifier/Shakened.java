package com.dmtprogramming.pathfindercombat.modifier;

public class Shakened extends ModifierBase {
	
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
		return "Shakened";
	}
}

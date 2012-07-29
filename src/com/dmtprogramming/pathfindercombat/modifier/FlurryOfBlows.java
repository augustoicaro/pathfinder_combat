package com.dmtprogramming.pathfindercombat.modifier;

import com.dmtprogramming.pathfindercombat.models.PFCharacter;

public class FlurryOfBlows extends ModifierBase {
	
	@Override
	protected String applyAttacks(String s) {
		return PFCharacter.FLURRY_OF_BLOWS_ATTACKS[character().getLevel() - 1];
	}
	
	public String classes() {
		return "Monk";
	}
	
	@Override
	public String name() {
		return "Flurry of Blows";
	}

}

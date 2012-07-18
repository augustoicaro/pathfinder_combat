package com.dmtprogramming.pathfindercombat.modifier;

public class Plus1d6Damage extends ModifierBase {
	
	@Override
	public String apply(String field, String value) {
		if (field.equals(_damage_dice)) {
			if (!value.equals("")) {
				value = value.concat(" + ");
			}
			return value.concat("1d6");
		}
		return value;
	}

	@Override
	public String name() {
		return "+1d6 Dmg";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2d6Damage extends ModifierBase {
	
	@Override
	public String apply(String field, String value) {
		if (field.equals(_damage_dice)) {
			if (!value.equals("")) {
				value = value.concat(" + ");
			}
			return value.concat("2d6");
		}
		return value;
	}

	@Override
	public String name() {
		return "+2d6 Dmg";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2Damage extends ModifierBase {

	@Override
	public String apply(String field, String value) {
		if (field.equals(_damage)) {
			int i = parseInt(value);
			i += 2;
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "+2 Dmg";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

public class Plus1Damage extends ModifierBase {

	@Override
	public String apply(String field, String value) {
		if (field.equals(_damage)) {
			int i = parseInt(value);
			i += 1;
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "+1 Dmg";
	}
}

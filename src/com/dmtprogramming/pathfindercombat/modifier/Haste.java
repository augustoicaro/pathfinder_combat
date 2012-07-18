package com.dmtprogramming.pathfindercombat.modifier;

public class Haste extends ModifierBase {

	private static int _plus_hit = 1;

	@Override
	public String apply(String field, String value) {
		if (field.equals(_hit)) {
			int i = parseInt(value);
			i += _plus_hit;
			return String.valueOf(i);
		} else if (field.equals(_extra_attack)) {
			return "true";
		}
		return value;
	}

	@Override
	public String name() {
		return "Haste";
	}
}

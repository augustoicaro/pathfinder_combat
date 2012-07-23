package com.dmtprogramming.pathfindercombat.modifier;

public class Deafened extends ModifierBase {

	@Override
	public String apply(String field, String value) {
		if (field.equals(_hit)) {
			int i = parseInt(value);
			i += -4;
			return String.valueOf(i);
		} else if (field.equals(_ac)) {
			int i = parseInt(value);
			i += 2;
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "Deafened";
	}
}

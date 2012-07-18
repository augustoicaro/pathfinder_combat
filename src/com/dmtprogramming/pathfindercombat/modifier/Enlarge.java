package com.dmtprogramming.pathfindercombat.modifier;

public class Enlarge extends ModifierBase {

	@Override
	public String apply(String field, String value) {

		if (field.equals(_hit)) {
			int i = parseInt(value);
			i += -1;
			return String.valueOf(i);
		} else if (field.equals(_str)) {
			int i = parseInt(value);
			i += 2;
			return String.valueOf(i);
		} else if (field.equals(_size)) {
			int i = parseInt(value);
			i += 1;
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "Enlarge";
	}

}

package com.dmtprogramming.pathfindercombat.modifier;

public class Smite extends ModifierBase {

	public String classes() {
		return "Paladin";
	}
	
	@Override
	public String apply(String field, String value) {
		if (field.equals(_hit)) {
			int i = parseInt(value);
			i += character().getChaMod();
			return String.valueOf(i);
		} else if (field.equals(_damage)) {
			int i = parseInt(value);
			i += character().getLevel();
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "Smite";
	}
	
}

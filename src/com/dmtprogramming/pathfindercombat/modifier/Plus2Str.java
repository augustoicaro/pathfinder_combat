package com.dmtprogramming.pathfindercombat.modifier;

public class Plus2Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}
	
	@Override
	public String apply(String field, String value) {
		if (field.equals(_str)) {
			int i = parseInt(value);
			i += 2;
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "+2 Str";
	}

}

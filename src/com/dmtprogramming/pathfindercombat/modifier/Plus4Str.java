package com.dmtprogramming.pathfindercombat.modifier;

public class Plus4Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}
	
	@Override
	public String apply(String field, String value) {
		if (field.equals(_str)) {
			int i = parseInt(value);
			i += 4;
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "+4 Str";
	}
}

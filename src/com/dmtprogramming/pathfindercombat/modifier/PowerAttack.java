package com.dmtprogramming.pathfindercombat.modifier;

public class PowerAttack extends ModifierBase {

	public String range() {
		return _r_melee;
	}
	
	@Override
	public String apply(String field, String value) {
		int mod = (character().getBAB() / 4) + 1;
		if (field.equals(_hit)) {
			int i = parseInt(value);
			i -= mod;
			return String.valueOf(i);
		} else if (field.equals(_damage)) {
			int i = parseInt(value);
			i += (mod * 2);
			return String.valueOf(i);
		}
		return value;
	}

	@Override
	public String name() {
		return "Power Attack";
	}

}

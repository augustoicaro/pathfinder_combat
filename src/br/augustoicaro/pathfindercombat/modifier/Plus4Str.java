package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Plus4Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}

	@Override
	public int applyStr(int s) {
		return s += 4;
	}
	
	@Override
	public int applyStrMod(int s) {
		return s + 2;
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "+4 For";
		else
			return "+4 Str";
	}
}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Flank extends ModifierBase {
	
	public String range() {
		return _r_melee;
	}
	
	public int applyHit(int value) {
		return value += 2;
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Flanqueado";
		else
			return "Flank";
	}
}

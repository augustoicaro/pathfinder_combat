package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class RapidShot extends ModifierBase {

	public String range() {
		return _r_ranged;
	}

	@Override
	public int applyHit(int s) {
		return s - 2;
	}

	@Override
	public int applyExtraAttack(int v) {
		return v + 1;
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Tiro Rápido";
		else
			return "Rapid Shot";
	}
}

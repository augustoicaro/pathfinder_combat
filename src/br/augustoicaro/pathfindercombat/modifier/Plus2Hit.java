package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Plus2Hit extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 2;
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "+2 Ataque";
		else
			return "+2 Hit";
	}
}

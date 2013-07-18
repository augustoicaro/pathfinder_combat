package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Entangled extends ModifierBase {

	@Override
	protected int applyHit(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyDex(int s) {
		return s - 4;
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Entangled";
		else
			return "Entangled";
	}
}

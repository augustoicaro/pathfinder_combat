package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Enlarge extends ModifierBase {

	public int applyHit(int v) {
		return v - 1;
	}
	
	public int applyStr(int v) {
		return v + 2;
	}
	
	public int applySize(int v) {
		return v + 1;
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Aumentar Pessoa";
		else
			return "Enlarge";
	}

}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Smite extends ModifierBase {

	@Override
	public String classes() {
		return "Paladin";
	}
	
	@Override
	public int applyHit(int s) {
		return s += character().getChaMod();
	}
	
	@Override
	public int applyDamage(int s) {
		return s += character().getLevel();
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Destruir o Mal";
		else
			return "Smite";
	}
	
}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Haste extends ModifierBase {

	@Override
	public int applyHit(int v) {
		return v += 1;
	}
	
	@Override
	public int applyExtraAttack(int v) {
		return v += 1;
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Atq. Acelerado";
		else
		 return "Haste";
	}
}

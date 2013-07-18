package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Plus2Damage extends ModifierBase {

	@Override
	public int applyDamage(int s) {
		return s += 2;
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "+2 Dano";
		else
			return "+2 Dmg";
	}
}

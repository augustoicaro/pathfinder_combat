package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class DeadlyAim extends ModifierBase {

	public String range() {
		return _r_ranged;
	}

	@Override
	public int applyHit(int s) {
		int mod = (character().getBAB() / 4) + 1;
		return s -= mod;
	}
	
	@Override
	public int applyDamage(int s) {
		int mod = (character().getBAB() / 4) + 1;
		return s += (mod * 2);
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Tiro Preciso";
		else
		  return "Deadly Aim";
	}

}

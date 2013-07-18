package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class FavoredEnemy4 extends ModifierBase {

	@Override
	public String classes() {
		return "Ranger";
	}
	
	@Override
	public int applyHit(int s) {
		return s += 4;
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 4;
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "I. Favorito +4";
		else
			return "Favored 4";
	}
}

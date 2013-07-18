package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Paralyzed extends ModifierBase {

	@Override
	protected int applyStr(int s) {
		return -999;
	}
	
	@Override
	protected int applyDex(int s) {
		return -999;
	}
	
	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Paralizado";
		else
			return "Paralyzed";
	}
}

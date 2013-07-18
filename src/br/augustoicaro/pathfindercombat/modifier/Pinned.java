package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Pinned extends ModifierBase {

	@Override
	protected int applyAC(int s) {
		return s - 4;
	}
	
	@Override
	protected int applyDexAC(int s) {
		return 0;
	}
	
	@Override
	protected String applyActions(String s) {
		return "standard";
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Imobilizado";
		else
			return "Pinned";
	}
}

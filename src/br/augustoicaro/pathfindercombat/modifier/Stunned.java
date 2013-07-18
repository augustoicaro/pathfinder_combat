package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Stunned extends ModifierBase {
	
	@Override
	protected int applyDexAC(int s) {
		return 0;
	}
	
	@Override
	protected int applyAC(int s) {
		return s - 2;
	}
	
	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Atordoado";
		else
			return "Stunned";
	}
}

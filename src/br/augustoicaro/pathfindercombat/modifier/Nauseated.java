package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Nauseated extends ModifierBase {

	@Override
	protected String applyActions(String s) {
		return "move";
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Nauseado";
		else
			return "Nauseated";
	}
}

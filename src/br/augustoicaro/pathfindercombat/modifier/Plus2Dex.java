package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Plus2Dex extends ModifierBase {

	public String range() {
		return _r_ranged;
	}
	
	@Override
	public int applyDex(int s) {
		return s + 2;
	}
	
	@Override
	public int applyDexMod(int s) {
		return s + 1;
	}

	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "+2 Des";
		else
			return "+2 Dex";
	}

}

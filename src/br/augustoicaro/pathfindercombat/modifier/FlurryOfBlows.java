package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.models.PFCharacter;
import br.augustoicaro.pathfindercombat.MainActivity;

public class FlurryOfBlows extends ModifierBase {
	
	@Override
	protected String applyAttacks(String s) {
		return PFCharacter.FLURRY_OF_BLOWS_ATTACKS[character().getLevel() - 1];
	}
	
	public String classes() {
		return "Monk";
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Sequência de Golpes";
		else
			return "Flurry of Blows";
	}

}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.models.PFCharacter;
import br.augustoicaro.pathfindercombat.MainActivity;
import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class FlurryOfBlows extends ModifierBase {
	
	@Override
	protected String applyAttacks(String s) {
		return PFCharacter.FLURRY_OF_BLOWS_ATTACKS[character().getLevel() - 1];
	}
	
	public String classes() {
		return PFCombatApplication.getString(R.string.monk);
	}
	
	@Override
	public String name() {
  	return PFCombatApplication.getString(R.string.flurryOfBlows);
	}

}

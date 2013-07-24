package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Smite extends ModifierBase {

	@Override
	public String classes() {
		return PFCombatApplication.getString(R.string.paladin);
	}
	
	@Override
	public int applyHit(int s) {
		return s += character().getChaMod();
	}
	
	@Override
	public int applyDamage(int s) {
		return s += character().getLevel();
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.smite);
	}
	
}

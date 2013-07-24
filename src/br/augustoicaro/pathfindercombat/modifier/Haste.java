package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Haste extends ModifierBase {

	@Override
	public int applyHit(int v) {
		return v += 1;
	}
	
	@Override
	public int applyExtraAttack(int v) {
		return v += 1;
	}

	@Override
	public String name() {
	  return PFCombatApplication.getString(R.string.haste);
	}
}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus4Hit extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 4;
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus4Hit);
	}
}

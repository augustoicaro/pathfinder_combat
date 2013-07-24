package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Defensive extends ModifierBase {

	@Override
	protected int applyAC(int s) {
		return s + 4;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.defensive);
	}
}

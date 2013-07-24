package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Helpless extends ModifierBase {
	
	@Override
	protected int applyDex(int s) {
		return -999;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.helpless);
	}
}

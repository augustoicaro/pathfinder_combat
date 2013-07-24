package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Pinned extends ModifierBase {

	@Override
	protected int applyAC(int s) {
		return s - 4;
	}
	
	@Override
	protected int applyDexAC(int s) {
		return 0;
	}
	
	@Override
	protected String applyActions(String s) {
		return "standard";
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.pinned);
	}
}

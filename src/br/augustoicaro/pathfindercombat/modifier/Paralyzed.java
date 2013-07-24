package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Paralyzed extends ModifierBase {

	@Override
	protected int applyStr(int s) {
		return -999;
	}
	
	@Override
	protected int applyDex(int s) {
		return -999;
	}
	
	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.paralyzed);
	}
}

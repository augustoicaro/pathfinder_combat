package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Fascinated extends ModifierBase {

	@Override
	protected String applyActions(String s) {
		return "none";
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.fascinated);
	}
}

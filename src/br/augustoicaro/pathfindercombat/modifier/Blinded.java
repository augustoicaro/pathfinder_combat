package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Blinded extends ModifierBase {

	@Override
	protected int applyAC(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyDexAC(int s) {
		return 0;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.blinded);
	}
}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Entangled extends ModifierBase {

	@Override
	protected int applyHit(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyDex(int s) {
		return s - 4;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.entangled);
	}
}

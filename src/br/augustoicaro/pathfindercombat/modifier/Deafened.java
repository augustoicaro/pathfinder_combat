package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Deafened extends ModifierBase {

	@Override
	protected int applyInit(int s) {
		return s - 4;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.deafened);
	}
}

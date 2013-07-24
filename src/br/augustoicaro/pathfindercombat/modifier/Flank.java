package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Flank extends ModifierBase {
	
	public String range() {
		return _r_melee;
	}
	
	public int applyHit(int value) {
		return value += 2;
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.flank);
	}
}

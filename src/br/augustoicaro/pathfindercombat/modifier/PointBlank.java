package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class PointBlank extends ModifierBase {

	public String range() {
		return _r_ranged;
	}

	@Override
	public int applyHit(int s) {
		return s + 1;
	}
	
	@Override
	public int applyDamage(int s) {
		return s + 1;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.pointBlank);
	}
}

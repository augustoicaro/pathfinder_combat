package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus2Damage extends ModifierBase {

	@Override
	public int applyDamage(int s) {
		return s += 2;
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus2Damage);
	}
}

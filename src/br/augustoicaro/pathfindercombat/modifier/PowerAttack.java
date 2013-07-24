package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class PowerAttack extends ModifierBase {

	public String range() {
		return _r_melee;
	}

	@Override
	public int applyHit(int s) {
		int mod = (character().getBAB() / 4) + 1;
		return s -= mod;
	}
	
	@Override
	public int applyDamage(int s) {
		int mod = (character().getBAB() / 4) + 1;
		return s += (mod * 2);
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.powerAttack);
	}

}

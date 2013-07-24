package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus4Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}

	@Override
	public int applyStr(int s) {
		return s += 4;
	}
	
	@Override
	public int applyStrMod(int s) {
		return s + 2;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus4Str);
	}
}

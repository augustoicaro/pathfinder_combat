package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus2Str extends ModifierBase {

	public String range() {
		return _r_melee;
	}
	
	@Override
	public int applyStr(int s) {
		return s + 2;
	}

	@Override
	public int applyStrMod(int s) {
		return s + 1;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus2Str);
	}

}

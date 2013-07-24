package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus4Dex extends ModifierBase {

	public String range() {
		return _r_ranged;
	}
	
	@Override
	public int applyDex(int s) {
		return s + 4;
	}

	@Override
	public int applyDexMod(int s) {
		return s + 2;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus4Dex);
	}

}

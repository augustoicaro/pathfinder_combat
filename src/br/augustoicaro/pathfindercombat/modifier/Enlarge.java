package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Enlarge extends ModifierBase {

	public int applyHit(int v) {
		return v - 1;
	}
	
	public int applyStr(int v) {
		return v + 2;
	}
	
	public int applySize(int v) {
		return v + 1;
	}

	@Override
	public String name() {	
		return PFCombatApplication.getString(R.string.enlarge);
	}

}

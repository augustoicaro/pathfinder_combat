package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus1d6Damage extends ModifierBase {

	@Override
	public String applyDamageDice(String s) {
		if (!s.equals("")) {
			s = s.concat(" + ");
		}
		return s.concat("1d6");
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus1d6);
	}
}

package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Plus2d6Damage extends ModifierBase {
	
	@Override
	public String applyDamageDice(String s) {
		if (!s.equals("")) {
			s = s.concat(" + ");
		}
		return s.concat("2d6");
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.plus2d6);
	}
}

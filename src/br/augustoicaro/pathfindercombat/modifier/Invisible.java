package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Invisible extends ModifierBase {

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.invisible);
	}
}

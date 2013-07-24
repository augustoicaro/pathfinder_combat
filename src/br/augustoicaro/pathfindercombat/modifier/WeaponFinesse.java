package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class WeaponFinesse extends ModifierBase {

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		character().setWeaponFinesse(enabled);	
	}

	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.weaponFinesse);
	}
}


package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class Critical extends ModifierBase {
	
	@Override
	public int applyCriticalDamage(int v) {
		if(character().getWeapon().getName().equals("-"))
		  return v;
		else
			return character().applyWeaponPlusCritical(v);
	}
	
	@Override
	public String applyCriticalDamageDice(String s) {
		if(character().getWeapon().getName().equals("-"))
			return s;
		else
			return character().applyWeaponDiceCritical(s);
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.critical);
	}
}

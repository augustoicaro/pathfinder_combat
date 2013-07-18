package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

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
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Decisivo";
		else
			return "Critical";
	}
}

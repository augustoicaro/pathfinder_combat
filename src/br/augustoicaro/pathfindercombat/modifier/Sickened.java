package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Sickened extends ModifierBase {

	@Override
	protected int applyDamage(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyHit(int s) {
		return s - 2;
	}
	
	@Override
	protected int applySaves(int s) {
		return s - 2;
	}
	
	@Override
	protected int applySkillChecks(int s) {
		return s - 2;
	}
	
	@Override
	protected int applyAbilityChecks(int s) {
		return s - 2;
	}
	
	@Override
	public String name() {
		String locale = MainActivity.locale;
		if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			return "Enjoado";
		else
			return "Sickened";
	}
}

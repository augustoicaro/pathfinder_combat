package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.R;

public class FavoredEnemy2 extends ModifierBase {

	@Override
	public String classes() {
		return "Ranger";
	}
	
	@Override
	public int applyHit(int s) {
		return s += 2;
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 2;
	}
	
	@Override
	public String name() {
		return PFCombatApplication.getString(R.string.fenemy2);
	}
}

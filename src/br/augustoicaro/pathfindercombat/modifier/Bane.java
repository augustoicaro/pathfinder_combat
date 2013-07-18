package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.MainActivity;

public class Bane extends ModifierBase {

	@Override
	public int applyHit(int s) {
		return s += 2;
	}
	
	@Override
	public int applyDamage(int s) {
		return s += 2;
	}

	@Override
	public String applyDamageDice(String s) {
		if (!s.equals("")) {
			s = s.concat(" + ");
		}
		return s.concat("2d6");
	}
	
	@Override
	public String name() {
	  String locale = MainActivity.locale;
	  if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
		  return "Bane";
		else
		  return "Bane";
	}
}

package com.dmtprogramming.pathfindercombat.modifier;

import com.dmtprogramming.pathfindercombat.PFCombatApplication;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;

/*

-str
-dex
-hit
damage
damageDice
critical
extraAttack
size

ac
dex_ac
actions
init
speed
saves
skill_checks
ability_checks


 */


public abstract class ModifierBase {
	
	public static final String _str = "str";
	public static final String _dex = "dex";
	public static final String _str_mod = "str_mod";
	public static final String _dex_mod = "dex_mod";
	public static final String _hit = "hit";
	public static final String _damage = "damage";
	public static final String _damage_dice = "damage_dice";
	public static final String _critical = "critical";
	public static final String _extra_attack = "extra_attack";
	public static final String _size = "size";
	public static final String _ac = "ac";
	public static final String _dex_ac = "dex_ac";
	public static final String _actions = "actions";
	public static final String _init = "init";
	public static final String _speed = "speed";
	public static final String _saves = "saves";
	public static final String _skill_checks = "skill_checks";
	public static final String _ability_checks = "ability_checks";
	public static final String _cmd = "cmd";
	
	public static final String _r_melee = "melee";
	public static final String _r_ranged = "ranged";
	public static final String _all = "all";
	
	public abstract String apply(String field, String value);
	public abstract String name();
	
	public String classes() {
		return _all;
	}
	
	public String range() {
		return _all;
	}
	
	public String shortDescription() {
		return "";
	}
	
	protected int parseInt(String s) {
		if (s.equals("")) {
			return 0;
		}
		return Integer.parseInt(s);
	}
	
	protected PFCharacter character() {
		PFCombatApplication app = PFCombatApplication.getApplication();
		return app.getCurrentCharacter();
	}
}

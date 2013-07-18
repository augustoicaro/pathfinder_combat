package br.augustoicaro.pathfindercombat.modifier;

import br.augustoicaro.pathfindercombat.PFCombatApplication;
import br.augustoicaro.pathfindercombat.models.PFCharacter;

public abstract class ModifierBase {

	public enum ModifierField {
		_none,
		
		/* stats */
		_str, _dex, _con, _int, _wis, _cha,
		
		/* stat mods */
		_str_mod, _dex_mod, _con_mod, _int_mod, _wis_mod, _cha_mod,
		
		/* attacks */
		_hit, _damage, _damage_dice, _extra_attack, _attacks,
		
		_size, _ac, _dex_ac, _actions, _init, _speed,
		_saves, _skill_checks, _ability_checks, _cmd,
		
		/* critical */
		_critical_damage, _critical_damage_dice
	}

	public static final String _r_melee = "melee";
	public static final String _r_ranged = "ranged";
	public static final String _all = "all";

	private boolean enabled;
	
	public ModifierBase() {
		this.enabled = false;
	}
	
	// public abstract String apply(String field, String value);
	public abstract String name();

	public String apply(ModifierField field, String value) {
		if (!this.enabled) {
			return value;
		}
		
		switch (field) {
		case _str:
			return String.valueOf(applyStr(parseInt(value)));
		case _dex:
			return String.valueOf(applyDex(parseInt(value)));
		case _con:
			return String.valueOf(applyCon(parseInt(value)));
		case _int:
			return String.valueOf(applyInt(parseInt(value)));
		case _wis:
			return String.valueOf(applyWis(parseInt(value)));
		case _cha:
			return String.valueOf(applyCha(parseInt(value)));
			
			
		case _str_mod:
			return String.valueOf(applyStrMod(parseInt(value)));
		case _dex_mod:
			return String.valueOf(applyDexMod(parseInt(value)));
		case _con_mod:
			return String.valueOf(applyConMod(parseInt(value)));
		case _int_mod:
			return String.valueOf(applyIntMod(parseInt(value)));
		case _wis_mod:
			return String.valueOf(applyWisMod(parseInt(value)));
		case _cha_mod:
			return String.valueOf(applyChaMod(parseInt(value)));
			
			
		case _hit:
			return String.valueOf(applyHit(parseInt(value)));
		case _damage:
			return String.valueOf(applyDamage(parseInt(value)));
		case _damage_dice:
			return applyDamageDice(value);
		case _extra_attack:
			return String.valueOf(applyExtraAttack(parseInt(value)));
		case _attacks:
			return applyAttacks(value);
			
			
		case _size:
			return String.valueOf(applySize(parseInt(value)));
		case _ac:
			return String.valueOf(applyAC(parseInt(value)));
		case _dex_ac:
			return String.valueOf(applyDexAC(parseInt(value)));
		case _actions:
			return applyActions(value);
		case _init:
			return String.valueOf(applyInit(parseInt(value)));
		case _speed:
			return String.valueOf(applySpeed(parseInt(value)));
		case _saves:
			return String.valueOf(applySaves(parseInt(value)));
		case _skill_checks:
			return String.valueOf(applySkillChecks(parseInt(value)));
		case _ability_checks:
			return String.valueOf(applyAbilityChecks(parseInt(value)));
			
			
		case _critical_damage:
			return String.valueOf(applyCriticalDamage(parseInt(value)));			
		case _critical_damage_dice:
			return applyCriticalDamageDice(value);
		}

		return value;
	}

	/* stats */
	protected int applyStr(int value) {
		return value;
	}

	protected int applyDex(int value) {
		return value;
	}

	protected int applyCon(int value) {
		return value;
	}

	protected int applyInt(int value) {
		return value;
	}

	protected int applyWis(int value) {
		return value;
	}

	protected int applyCha(int value) {
		return value;
	}

	/* stat mods */
	protected int applyStrMod(int value) {
		return value;
	}

	protected int applyDexMod(int value) {
		return value;
	}

	protected int applyConMod(int value) {
		return value;
	}

	protected int applyIntMod(int value) {
		return value;
	}

	protected int applyWisMod(int value) {
		return value;
	}

	protected int applyChaMod(int value) {
		return value;
	}

	protected int applyHit(int value) {
		return value;
	}

	protected int applyDamage(int value) {
		return value;
	}

	protected String applyDamageDice(String value) {
		return value;
	}

	protected int applyAC(int value) {
		return value;
	}

	protected int applyDexAC(int value) {
		return value;
	}

	protected int applySpeed(int value) {
		return value;
	}

	protected int applySize(int value) {
		return value;
	}

	protected int applySaves(int value) {
		return value;
	}

	protected int applyInit(int value) {
		return value;
	}

	protected int applySkillChecks(int value) {
		return value;
	}

	protected int applyAbilityChecks(int value) {
		return value;
	}
	
	protected int applyExtraAttack(int value) {
		return value;
	}
	
	protected String applyAttacks(String value) {
		return value;
	}
	
	protected int applyCritical(int value) {
		return value;
	}
	
	protected String applyActions(String value) {
		return value;
	}
	
	protected int applyCriticalDamage(int value) {
		return value;
	}
	
	protected String applyCriticalDamageDice(String value) {
		return value;
	}
	
	/* informational methods */

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
		s = s.replace("+", "");
		return Integer.parseInt(s);
	}

	protected PFCharacter character() {
		PFCombatApplication app = PFCombatApplication.getApplication();
		return app.getCurrentCharacter();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean appliesToCharacterClass(String characterClass) {
		String clas = classes();
		if (clas.equals(_all)) {
			return true;
		}
		return clas.equals(characterClass);
	}
	
	public boolean appliesToRange(String characterRange) {
		String range = range();
		if (range.equals(_all)) {
			return true;
		}
		return range.equals(characterRange);
	}
}

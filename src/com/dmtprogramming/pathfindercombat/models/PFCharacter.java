package com.dmtprogramming.pathfindercombat.models;

import java.util.List;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;

public class PFCharacter {
	
	public static String[] SIZES = {"Tiny", "Medium", "Large"};
	public static String MEDIUM = "Medium";
	
	public static String[] FLURRY_OF_BLOWS_ATTACKS = { "-1 / -1", "0 / 0", "1 / 1", "2 / 2", "3 / 3", "4 / 4 / -1", "5 / 5 / 0", "6 / 6 / 1 / 1",
		"7 / 7 / 2 / 2", "8 / 8 / 3 / 3", "9 / 9 / 4 / 4 / -1", "10 / 10 / 5 / 5 / 0", "11 / 11 / 6 / 6 / 1", "12 / 12 / 7 / 7 / 2", "13 / 13 / 8 / 8 / 3 / 3",
		"14 / 14 / 9 / 9 / 4 / 4 / -1", "15 / 15 / 10 / 10 / 5 / 5 / 0", "16 / 16 / 11 / 11 / 6 / 6 / 1", "17 / 17 / 12 / 12 / 7 / 7 / 2", 
		"18 / 18 / 13 / 13 / 8 / 8 / 3"};
	
	public static String[] CHARACTER_CLASS_NAMES = { "Barbarian", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue" };
	public static String[] FAST_BAB_CLASSES = { "Barbarian", "Fighter", "Paladin", "Ranger" };
	public static String[] MEDIUM_BAB_CLASSES = { "Cleric", "Druid", "Monk", "Rogue" };
	
	public static int[] FAST_BAB_PROGRESSION = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	public static int[] MEDIUM_BAB_PROGRESSION = { 0, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10, 11, 12, 12, 13, 14, 15 };
	public static int[] SLOW_BAB_PROGRESSION = { 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10 };
	
	public static String[] TINY_WEAPON_DAMAGES = {"1d2", "1d3", "1d4", "1d6", "1d8", "1d4", "1d8", "1d10", "2d6"};
	public static String[] MEDIUM_WEAPON_DAMAGES = {"1d4", "1d6", "1d8", "1d10", "1d12", "2d4", "2d6", "2d8", "2d10"};
	public static String[] LARGE_WEAPON_DAMAGES = {"1d6", "1d8", "2d6", "2d8", "3d6", "2d6", "3d6", "3d8", "4d8"};
	
	public static String[] TINY_MONK_DAMAGES = { "1d4", "1d6", "1d8", "1d10", "2d6", "2d8" };
	public static String[] MEDIUM_MONK_DAMAGES = { "1d6", "1d8", "1d10", "2d6", "2d8", "2d10" };
	public static String[] LARGE_MONK_DAMAGES = { "1d8", "2d6", "2d8", "3d6", "3d8", "4d8" };
	
	public static String[] CRITICAL_MULIPLIERS = { "x2", "x3", "x4", "x5" };
	
	private long id;
	private String name;
	private String player;
	private int str;
	private int dex;
	private int con;
	private int intel;
	private int wis;
	private int cha;
	private int level;
	private int monk_level;
	private String character_class;
	private boolean weapon_focus;
	private boolean power_attack;
	private boolean flurry_of_blows;
	private String size;
	private String weapon_damage;
	private int weapon_plus;
	private boolean unarmed;
	private int daily_total;
	private int daily_current;
	private String daily_title;
	private String critical_multiplier;
	private List<Condition> conditions;
	
	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCharacterClass() {
		return this.character_class;
	}
	
	public void setCharacterClass(String c) {
		this.character_class = c;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
	public boolean setData(String field, String value) {
		if (value.equals("")) {
			return true;
		}
		if (field == DatabaseHelper._c_characters_name) {
			if (this.name.equals(value)) {
				return false;
			}
			setName(value);
			return true;
		}
		if (field == DatabaseHelper._c_characters_player) {
			if (this.player.equals(value)) {
				return false;
			}
			setPlayer(value);
			return true;
		}
		if (field == DatabaseHelper._c_characters_strength) {
			int i = Integer.parseInt(value);
			if (this.str == i) {
				return false;
			}
			setStr(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_dexterity) {
			int i = Integer.parseInt(value);
			if (this.dex == i) {
				return false;
			}
			setDex(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_constitution) {
			int i = Integer.parseInt(value);
			if (this.con == i) {
				return false;
			}
			setCon(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_intelligence) {
			int i = Integer.parseInt(value);
			if (this.intel == i) {
				return false;
			}
			setInt(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_wisdom) {
			int i = Integer.parseInt(value);
			if (this.wis == i) {
				return false;
			}
			setWis(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_charisma) {
			int i = Integer.parseInt(value);
			if (this.cha == i) {
				return false;
			}
			setCha(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_level) {
			int i = Integer.parseInt(value);
			if (this.level == i) {
				return false;
			}
			setLevel(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_weapon_plus) {
			int i = Integer.parseInt(value);
			if (this.weapon_plus == i) {
				return false;
			}
			setWeaponPlus(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_weapon_damage) {
			if (this.weapon_damage != null && this.weapon_damage.equals(value)) {
				return false;
			}
			setWeaponDamage(value);
			return true;
		}
		if (field == DatabaseHelper._c_characters_character_class) {
			if (this.character_class != null && this.character_class.equals(value)) {
				return false;
			}
			setCharacterClass(value);
			return true;
		}
		if (field == DatabaseHelper._c_characters_daily_title) {
			if (this.daily_title != null && this.daily_title.equals(value)) {
				return false;
			}
			setDailyTitle(value);
			return true;
		}
		if (field == DatabaseHelper._c_characters_daily_total) {
			int i = Integer.parseInt(value);
			if (this.daily_total == i) {
				return false;
			}
			setDailyTotal(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_daily_current) {
			int i = Integer.parseInt(value);
			if (this.daily_current == i) {
				return false;
			}
			setDailyCurrent(i);
			return true;
		}
		if (field == DatabaseHelper._c_characters_critical_multiplier) {
			if (this.critical_multiplier != null && this.critical_multiplier.equals(value)) {
				return false;
			}
			setCriticalMultiplier(value);
			return true;
		}

		return false;
	}
	
	public String toString() {
		return this.name + " (Level " + this.level + " " + this.character_class + ")";
	}

	public int getStr() {
		return this.str;
	}
	
	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return this.dex;
	}
	
	public void setDex(int dex) {
		this.dex = dex;
	}
	
	public int getCon() {
		return this.con;
	}
	
	public void setCon(int con) {
		this.con = con;
	}

	public int getStrMod() {
		return statMod(this.str);
	}

	public int getDexMod() {
		return statMod(this.dex);
	}
	
	public int getConMod() {
		return statMod(this.con);
	}
	
	public int getIntMod() {
		return statMod(this.intel);
	}
	
	public int getWisMod() {
		return statMod(this.wis);
	}
	
	public int getChaMod() {
		return statMod(this.cha);
	}
	
	public static int statMod(int stat) {
		return (int) Math.floor((stat - 10) / 2);
	}

	public String getStrModDisplay() {
		return statModDisplay(getStrMod());
	}

	public String getDexModDisplay() {
		return statModDisplay(getDexMod());
	}
	
	public String getConModDisplay() {
		return statModDisplay(getConMod());
	}
	
	public String getIntModDisplay() {
		return statModDisplay(getIntMod());
	}
	
	public String getWisModDisplay() {
		return statModDisplay(getWisMod());
	}
	
	public String getChaModDisplay() {
		return statModDisplay(getChaMod());
	}
	
	public String statModDisplay(int mod) {
		if (mod >= 0) {
			return "+" + String.valueOf(mod);
		}
		return String.valueOf(mod);
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getBAB() {
		int[] progression = getBABProgression(); 
		return progression[this.level - 1];
	}
	
	public String getAttacks() {
		String ret = "";
		if (this.flurry_of_blows) {
			return PFCharacter.FLURRY_OF_BLOWS_ATTACKS[this.level - 1];
		}
		int l = getBAB();
		ret = String.valueOf(l);
		l -= 5;
		while (l > 0) {
			ret = ret.concat(" / ");
			ret = ret.concat(String.valueOf(l));
			l -= 5;
		}
		return ret;
	}

	public int getInt() {
		return this.intel;
	}
	
	public void setInt(int intel) {
		this.intel = intel;
	}

	public int getWis() {
		return this.wis;
	}
	
	public void setWis(int wis) {
		this.wis = wis;
	}

	public int getCha() {
		return this.cha;
	}
	
	public void setCha(int cha) {
		this.cha = cha;
	}

	public int getMonkLevel() {
		return this.monk_level;
	}
	
	public void setMonkLevel(int monk_level) {
		this.monk_level = monk_level;
	}
	
	public void setWeaponFocus(boolean b) {
		this.weapon_focus = b;
	}
	
	public boolean getWeaponFocus() {
		return this.weapon_focus;
	}
	
	public void setPowerAttack(boolean b) {
		this.power_attack = b;
	}
	
	public boolean getPowerAttack() {
		return this.power_attack;
	}
	
	public int getWeaponFocusMod() {
		if (this.weapon_focus) {
			return 1;
		}
		return 0;
	}
	
	public int getPowerAttackMod() {
		if (this.power_attack) {
			return -2;
		}
		return 0;
	}
	
	public int getPowerAttackDamage() {
		if (this.power_attack) {
			return 4;
		}
		return 0;
	}
	
	public String getSize() {
		return this.size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getWeaponDamage() {
		return this.weapon_damage;
	}
	
	public String getEnlargedWeaponDamage() {
		int ind = -1;
		String[] damages = PFCharacter.MEDIUM_WEAPON_DAMAGES;
		String[] enlargeDamages = PFCharacter.LARGE_WEAPON_DAMAGES;
		if (this.flurry_of_blows) {
			damages = PFCharacter.MEDIUM_MONK_DAMAGES;
			enlargeDamages = PFCharacter.LARGE_MONK_DAMAGES;
		}
		for (int i = 0; i < damages.length; i++) {
			if (damages[i].equals(this.weapon_damage)) {
				ind = i;
			}
		}
		if (ind != -1) {
			return enlargeDamages[ind];
		}
		return this.weapon_damage;
	}
	
	public String getReducedWeaponDamage() {
		int ind = -1;
		String[] damages = PFCharacter.MEDIUM_WEAPON_DAMAGES;
		String[] reduceDamages = PFCharacter.TINY_WEAPON_DAMAGES;
		if (this.flurry_of_blows) {
			damages = PFCharacter.MEDIUM_MONK_DAMAGES;
			reduceDamages = PFCharacter.TINY_MONK_DAMAGES;
		}
		for (int i = 0; i < damages.length; i++) {
			if (damages[i].equals(this.weapon_damage)) {
				ind = i;
			}
		}
		if (ind != -1) {
			return reduceDamages[ind];
		}
		return this.weapon_damage;
	}
	
	public void setWeaponDamage(String weapon_damage) {
		this.weapon_damage = weapon_damage;
	}
	
	public int getWeaponPlus() {
		return this.weapon_plus;
	}
	
	public void setWeaponPlus(int weapon_plus) {
		this.weapon_plus = weapon_plus;
	}
	
	public int[] getBABProgression() {
		for (int i = 0; i < PFCharacter.FAST_BAB_CLASSES.length; i++) {
			if (getCharacterClass().equals(PFCharacter.FAST_BAB_CLASSES[i])) {
				return PFCharacter.FAST_BAB_PROGRESSION;
			}
		}
		for (int i = 0; i < PFCharacter.MEDIUM_BAB_CLASSES.length; i++) {
			if (getCharacterClass().equals(PFCharacter.MEDIUM_BAB_CLASSES[i])) {
				return PFCharacter.MEDIUM_BAB_PROGRESSION;
			}
		}
		return PFCharacter.FAST_BAB_PROGRESSION;
	}
	
	public boolean getFlurryOfBlows() {
		return this.flurry_of_blows;
	}
	
	public void setFlurryOfBlows(boolean b) {
		this.flurry_of_blows = b;
	}
	
	public boolean getUnarmed() {
		return this.unarmed;
	}
	
	public void setUnarmed(boolean b) {
		this.unarmed = b;
	}

	public int getDailyTotal() {
		return this.daily_total;
	}
	
	public void setDailyTotal(int i) {
		this.daily_total = i;
	}
	
	public int getDailyCurrent() {
		return this.daily_current;
	}
	
	public void setDailyCurrent(int i) {
		this.daily_current = i;
	}
	
	public String getDailyTitle() {
		return this.daily_title;
	}
	
	public void setDailyTitle(String t) {
		this.daily_title = t;
	}
	
	public String getCriticalMultiplier() {
		return this.critical_multiplier;
	}
	
	public void setCriticalMultiplier(String s) {
		this.critical_multiplier = s;
	}
	
	public int getCriticalMultiplierInt() {
		return Integer.parseInt(getCriticalMultiplier().substring(1));
	}

	public String applyWeaponDiceCritical(String weaponDice) {
		String[] parts = weaponDice.split("d");
		parts[0] = String.valueOf(Integer.parseInt(parts[0]) * getCriticalMultiplierInt());
		return parts[0] + "d" + parts[1];
	}
	
	public int applyWeaponPlusCritical(int plus) {
		return plus * getCriticalMultiplierInt();
	}
}


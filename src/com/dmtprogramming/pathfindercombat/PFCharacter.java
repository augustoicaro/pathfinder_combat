package com.dmtprogramming.pathfindercombat;

public class PFCharacter {
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
	private String characterClass;
	private boolean weapon_focus;
	private boolean power_attack;
	
	private String size;
	private String weapon_damage;
	private int weapon_plus;
	
	public static String[] SIZES = {
		"Tiny", "Medium", "Large"
	};
	
	public static String[] TINY_WEAPON_DAMAGES = {
		"1d2", "1d3", "1d4", "1d6", "1d8", "1d4", "1d8", "1d10", "2d6"
	};
	public static String[] MEDIUM_WEAPON_DAMAGES = {
		"1d4", "1d6", "1d8", "1d10", "1d12", "2d4", "2d6", "2d8", "2d10"
	};
	public static String[] LARGE_WEAPON_DAMAGES = {
		"1d6", "1d8", "2d6", "2d8", "3d6", "2d6", "3d6", "3d8", "4d8"
	};
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCharacterClass() {
		return this.characterClass;
	}
	
	public void setCharacterClass(String c) {
		this.characterClass = c;
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
		if (field == "name") {
			if (this.name.equals(value)) {
				return false;
			}
			setName(value);
			return true;
		}
		if (field == "player") {
			if (this.player.equals(value)) {
				return false;
			}
			setPlayer(value);
			return true;
		}
		if (field == "str") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.str == i) {
				return false;
			}
			setStr(i);
			return true;
		}
		if (field == "dex") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.dex == i) {
				return false;
			}
			setDex(i);
			return true;
		}
		if (field == "con") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.con == i) {
				return false;
			}
			setCon(i);
			return true;
		}
		if (field == "int") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.intel == i) {
				return false;
			}
			setInt(i);
			return true;
		}
		if (field == "wis") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.wis == i) {
				return false;
			}
			setWis(i);
			return true;
		}
		if (field == "cha") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.cha == i) {
				return false;
			}
			setCha(i);
			return true;
		}
		if (field == "level") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.level == i) {
				return false;
			}
			setLevel(i);
			return true;
		}
		if (field == "weapon_plus") {
			if (value.equals("")) {
				return true;
			}
			int i = Integer.parseInt(value);
			if (this.weapon_plus == i) {
				return false;
			}
			setWeaponPlus(i);
			return true;
		}

		return false;
	}
	
	public String toString() {
		return this.name + " (Level " + this.level + " " + this.characterClass + ")";
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

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getBAB() {
		return this.level;
	}
	
	public String getAttacks() {
		String ret = "";
		int l = this.level;
		while (l > 0) {
			if (!ret.equals("")) {
				ret = ret.concat(" / ");
				ret = ret.concat(String.valueOf(l));
			} else {
				ret = String.valueOf(l);
			}
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
		// return this.size;
		return "Medium";
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getWeaponDamage() {
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
}


package com.dmtprogramming.pathfindercombat;

public class CharacterModifier {
	private static final String TAG = "PFCombat:CharacterActivity";
	
	public int id;
	public int str;
	public String damageDice;
	public int damage;
	public int hit;
	public int size;
	public boolean enabled;
	public boolean extraAttack;
	public boolean flurry_of_blows;
	
	public CharacterModifier() {
		enabled = false;
		id = 0;
		str = 0;
		damageDice = "";
		damage = 0;
		hit = 0;
		size = 0;		
		extraAttack = false;
		flurry_of_blows = false;
	}
	
	public String apply(String field, String value) {
		if (this.enabled == false) {
			return value;
		}
		Log.d(TAG, "applying modifier str = " + str);
		if (field.equals("str")) {
			int i = parseInt(value);
			i += this.str;
			return String.valueOf(i);
		} else if (field.equals("strmod")) {
			int i = parseInt(value);
			i += this.str / 2;
			return String.valueOf(i);
		} else if (field.equals("plus_hit")) {
			int i = parseInt(value);
			i += this.hit;
			return String.valueOf(i);
		} else if (field.equals("plus_damage")) {
			int i = parseInt(value);
			i += this.damage;
			return String.valueOf(i);	
		} else if (field.equals("damage_dice")) {
			if (!value.equals("")) {
				value = value.concat(" + ");
			}
			return value.concat(this.damageDice);
		} else if (field.equals("size")) {
			int i = parseInt(value);
			i += this.size;
			return String.valueOf(i);
		} else if (field.equals("extra_attack")) {
			if (value.equals("true")) {
				return value;
			}
			if (this.extraAttack) {
				return "true";
			}
			return "false";
		} else if (field.equals("flurry_of_blows")) {
			if (value.equals("true")) {
				return value;
			}
			if (this.flurry_of_blows) {
				return "true";
			}
			return "false";
		}
		return value;
	}
	
	private int parseInt(String s) {
		if (s.equals("")) {
			return 0;
		}
		return Integer.parseInt(s);
	}
}

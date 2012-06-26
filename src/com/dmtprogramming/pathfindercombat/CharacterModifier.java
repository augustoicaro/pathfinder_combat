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
	
	public CharacterModifier() {
		enabled = false;
		id = 0;
		str = 0;
		damageDice = "";
		damage = 0;
		hit = 0;
		size = 0;		
	}
	
	public CharacterModifier(int _id) {
		enabled = false;
		id = _id;
		str = 0;
		damageDice = "";
		damage = 0;
		hit = 0;
		size = 0;
	}

	public String apply(String field, String value) {
		if (this.enabled == false) {
			return value;
		}
		Log.d(TAG, "applying modifier str = " + str);
		if (field.equals("str")) {
			int i = Integer.parseInt(value);
			i += this.str;
			return String.valueOf(i);
		} else if (field.equals("strmod")) {
			int i = Integer.parseInt(value);
			i += this.str / 2;
			return String.valueOf(i);
		} else if (field.equals("plus_hit")) {
			int i = Integer.parseInt(value);
			i += this.hit;
			return String.valueOf(i);
		} else if (field.equals("plus_damage")) {
			int i = Integer.parseInt(value);
			i += this.damage;
			return String.valueOf(i);	
		} else if (field.equals("damage_dice")) {
			if (!value.equals("")) {
				value = value.concat(" + ");
			}
			return value.concat(this.damageDice);
		}
		return value;
	}
}

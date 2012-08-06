package com.dmtprogramming.pathfindercombat.models;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "weapons")
public class Weapon {
	
	public static String[] CRITICAL_MULIPLIERS = { "2", "3", "4", "5" };	
	
	@DatabaseField(generatedId = true, columnName = "_id")
	private long id;
	@DatabaseField(canBeNull = true, foreign = true)
	private PFCharacter character;
	@DatabaseField
	private String name;
	@DatabaseField
	private String damage_dice;
	@DatabaseField
	private int hit;
	@DatabaseField
	private int damage;
	@DatabaseField
	private int critical_multiplier;
	@DatabaseField
	private int range;
	@DatabaseField
	private String additional_damage_dice;
	
	public Weapon() {
		this.name = "Short Sword";
		this.damage_dice = "1d6";
		this.hit = 0;
		this.damage = 0;
		this.critical_multiplier = 2;
		this.range = 0;
		this.additional_damage_dice = "";
	}
	
	public int getCriticalMultiplier() {
		return critical_multiplier;
	}
	public void setCriticalMultiplier(int critical_multiplier) {
		this.critical_multiplier = critical_multiplier;
	}
	public String getCriticalMultiplierString() {
		return "x" + String.valueOf(getCriticalMultiplier());
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getAdditionalDamageDice() {
		return additional_damage_dice;
	}
	public void setAdditionalDamageDice(String additional_damage_dice) {
		this.additional_damage_dice = additional_damage_dice;
	}
	public String getDamageDice() {
		return damage_dice;
	}
	public void setDamageDice(String damageDice) {
		this.damage_dice = damageDice;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean isMelee() {
		return this.range == 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PFCharacter getCharacter() {
		return character;
	}
	public void setCharacter(PFCharacter cha) {
		this.character = cha;
	}
	public String toString() {
		return this.name + " (" + rangeString() + ")"; 
	}
	public String rangeString() {
		String range_str = "melee";
		if (this.range != 0) {
			range_str = "ranged";
		}
		return range_str;
	}
}

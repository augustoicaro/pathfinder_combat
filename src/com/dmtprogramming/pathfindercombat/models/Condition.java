package com.dmtprogramming.pathfindercombat.models;
import com.dmtprogramming.pathfindercombat.modifier.ModifierBase;
import com.dmtprogramming.pathfindercombat.modifier.ModifierFactory;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "conditions")
public class Condition {
	
	@DatabaseField(generatedId = true, columnName = "_id")
	private long id;
	@DatabaseField(canBeNull = true, foreign = true)
	private PFCharacter character;
	@DatabaseField
	private String name;
	@DatabaseField
	private long duration;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
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
	public ModifierBase getModifier() {
		return ModifierFactory.create(this.name);
	}
}

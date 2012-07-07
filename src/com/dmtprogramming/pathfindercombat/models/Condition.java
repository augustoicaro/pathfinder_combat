package com.dmtprogramming.pathfindercombat.models;

public class Condition {	

	
	private long id;
	private long character_id;
	private String name;
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
	public long getCharacterId() {
		return character_id;
	}
	public void setCharacterId(long character_id) {
		this.character_id = character_id;
	}
}

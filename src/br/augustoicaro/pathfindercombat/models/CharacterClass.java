package br.augustoicaro.pathfindercombat.models;

public class CharacterClass {

	public String name;
	public int[] bab_progression;
	
	public CharacterClass(String _name, int[] _prog) {
		name = _name;
		bab_progression = _prog;
	}
}

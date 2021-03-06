package br.augustoicaro.pathfindercombat.modifier;

import java.util.ArrayList;
import java.util.List;

import br.augustoicaro.pathfindercombat.modifier.ModifierBase.ModifierField;

public class ModifierGroup {
	private List<ModifierBase> mods;
	
	ModifierGroup() {
		mods = new ArrayList<ModifierBase>();
	}
	
	public void add(ModifierBase mod) {
		mods.add(mod);
	}
	
	public String apply(ModifierField field, String value) {
		for (int i = 0; i < mods.size(); i++) {
			ModifierBase mod = mods.get(i);
			value = mod.apply(field, value);
		}
		return value;
	}
}

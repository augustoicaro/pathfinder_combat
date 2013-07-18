package br.augustoicaro.pathfindercombat;

import br.augustoicaro.pathfindercombat.modifier.ModifierBase;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;


//events for the toggle buttons
class ToggleClickListener implements OnClickListener {	
	private ToggleButton _toggle;
	private ModifierBase _mod;
	private FragmentBase _fragment;
	
	public ToggleClickListener(ToggleButton toggle, ModifierBase mod, FragmentBase fragment) {
		_toggle = toggle;
		_mod = mod;
		_fragment = fragment;
	}
	
	public void onClick(View v) {
		_mod.setEnabled(_toggle.isChecked());
		_fragment.populateStats("");
	}
}

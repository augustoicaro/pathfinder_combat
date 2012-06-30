package com.dmtprogramming.pathfindercombat;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ToggleButton;


//events for the toggle buttons
class ToggleClickListener implements OnClickListener {
	//private static final String TAG = "PFCombat:ToggleClickListener";
	
	private ToggleButton _toggle;
	private CharacterModifier _mod;
	private FragmentBase _fragment;
	
	public ToggleClickListener(ToggleButton toggle, CharacterModifier mod, FragmentBase fragment) {
		_toggle = toggle;
		_mod = mod;
		_fragment = fragment;
	}
	
	public void onClick(View v) {
		_mod.enabled = _toggle.isChecked();
		_fragment.populateStats("");
	}
}
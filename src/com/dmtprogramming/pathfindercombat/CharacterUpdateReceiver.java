package com.dmtprogramming.pathfindercombat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CharacterUpdateReceiver extends BroadcastReceiver {
	
	private static final String TAG = "PFCombat:CharacterUpdateReceiver";
	
	private FragmentBase _base;
	
	public CharacterUpdateReceiver(FragmentBase base) {
		_base = base;
	}
	
	public void onReceive(Context arg0, Intent arg1) {
		String field = arg1.getExtras().getString("field");
		Log.d(TAG, "got update intent update for field = " + field);
		_base.onAfterUpdateCharacter(field);
	}
}

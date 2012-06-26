package com.dmtprogramming.pathfindercombat;

import android.app.Application;
import android.util.Log;

public class PFCombatApplication extends Application {
	
	private static final String TAG = "PFCombat:PFCombatApplication";
	private PFCombatDB _db = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "onCreate()");
		_db = new PFCombatDB(this);
	}

}

package com.dmtprogramming.pathfindercombat;

import android.app.Application;
import android.util.Log;

public class PFCombatApplication extends Application {
	
	private static final String TAG = "PFCombat:PFCombatApplication";
	private PFCharacterDataSource _db = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "onCreate()");
		_db = new PFCharacterDataSource(this);
	}

	public PFCharacterDataSource characterDataSource() {
		return _db;
	}
}

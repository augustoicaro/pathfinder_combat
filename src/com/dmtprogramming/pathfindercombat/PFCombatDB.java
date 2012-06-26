package com.dmtprogramming.pathfindercombat;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PFCombatDB {

	private static final String TAG = "PFCombat:PFCombatDB";
	
	private PFCombatApplication _app = null;
	private DatabaseHelper _dbHelper = null;
	
	public PFCombatDB(PFCombatApplication app) {
		super();
		Log.v(TAG, "PFCombatDB()");
		_app = app;
		_dbHelper = new DatabaseHelper(_app);
		// TODO Auto-generated constructor stub
	}

}

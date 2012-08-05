package com.dmtprogramming.pathfindercombat;

import java.sql.SQLException;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.*;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class TabletFragmentActivity extends FragmentActivity {
	private static final String TAG = "PFCombat:ViewPagerFragmentActivity";
	
	private PFCharacter _char;
	
	private DatabaseHelper databaseHelper = null;

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    if (databaseHelper != null) {
	        OpenHelperManager.releaseHelper();
	        databaseHelper = null;
	    }
	}

	protected DatabaseHelper getHelper() {
	    if (databaseHelper == null) {
	        databaseHelper =
	            OpenHelperManager.getHelper(this, DatabaseHelper.class);
	    }
	    return databaseHelper;
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.tablet_layout);

        _char = null;
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	long _id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		try {
            		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
					_char = dao.queryForId((int) _id);
				} catch (SQLException e) {
					_char = null;
				}
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        }
	}  
	
	public void onResume() {

		super.onResume();
	}
}

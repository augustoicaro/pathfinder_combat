package com.dmtprogramming.pathfindercombat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TabletFragmentActivity extends Activity {
	private static final String TAG = "PFCombat:ViewPagerFragmentActivity";
	
	private PFCharacterDataSource _datasource;
	private PFCharacter _char;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.tablet_layout);

        _datasource = new PFCharacterDataSource(this);
        _datasource.open();
        _char = null;
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	long _id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		_char = _datasource.findCharacter(_id);
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        }
	}   
}

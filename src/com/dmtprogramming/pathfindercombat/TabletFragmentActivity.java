package com.dmtprogramming.pathfindercombat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.dmtprogramming.pathfindercombat.models.*;

public class TabletFragmentActivity extends FragmentActivity {
	private static final String TAG = "PFCombat:ViewPagerFragmentActivity";
	
	private PFCombatApplication _app;
	private PFCharacter _char;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.tablet_layout);

        _app = (PFCombatApplication)this.getApplication();
        _app.openDataSources();
        _char = null;
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	long _id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		_char = _app.getCharacterDataSource().findCharacter(_id);
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        }
	}   
	
	@Override
	protected void onResume() {
		_app.openDataSources();
		super.onResume();
	}

	@Override
	protected void onPause() {
		_app.closeDataSources();
		super.onPause();
	}
}

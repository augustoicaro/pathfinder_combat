package com.dmtprogramming.pathfindercombat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	private static final String TAG = "PFCombat:MainActivity";
	private PFCharacterDataSource datasource;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, String.format("test"));
        setContentView(R.layout.main);
        
        datasource = new PFCharacterDataSource(this);
        datasource.open();
        
        PFCharacter ch1 = datasource.findCharacter(1);
        Log.v(TAG, ch1.getName());
    }
}
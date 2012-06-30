package com.dmtprogramming.pathfindercombat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ParserError")
public class Test2Fragment extends Fragment {

	private static final String TAG = "PFCombat:CharacterActivity";
	private PFCharacterDataSource datasource;
	private PFCharacter _char;
	private View _view;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.test2, container, false);

        datasource = new PFCharacterDataSource(getActivity());
        datasource.open();
        
        _char = null;
        
        Bundle extras = super.getActivity().getIntent().getExtras();
        if (extras != null) {
        	long _id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		_char = datasource.findCharacter(_id);
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        }
        
		return _view;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, String.format("test"));
        //getActivity().setContentView(R.layout.character);
    }
}

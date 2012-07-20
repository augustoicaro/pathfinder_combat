package com.dmtprogramming.pathfindercombat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.*;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public abstract class FragmentBase extends Fragment {

	private static final String TAG = "PFCombat:FragmentBase";
	
	protected View _view;
	protected List<CharacterModifier> _mods;
	private CharacterUpdateReceiver _receiver;
    
    public abstract void onAfterUpdateCharacter(String field);
    protected abstract void populateStats(String field);
	
	private DatabaseHelper databaseHelper = null;
    
    public FragmentBase() {
     	_mods = new ArrayList<CharacterModifier>();
    }
    
    public void setupIntentFilter() {
     	IntentFilter filter = new IntentFilter("com.dmtprogramming.pathfindercombat.UPDATE_CHARACTER");
     	_receiver = new CharacterUpdateReceiver(this);
     	getActivity().registerReceiver(_receiver, filter);
    }
    
	protected DatabaseHelper getHelper() {
	    if (databaseHelper == null) {
	        databaseHelper =
	            OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
	    }
	    return databaseHelper;
	}
    
    // saves the character after a field update and refreshes everything
	public void updateCharacter(String field) {
		Log.d(TAG, "updateCharacter()");
		Dao<PFCharacter, Integer> dao;
		try {
			dao = getHelper().getCharacterDao();
			dao.update(getCharacter());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Intent intent = new Intent();
		intent.setAction("com.dmtprogramming.pathfindercombat.UPDATE_CHARACTER");
		intent.putExtra("field", field);
		getActivity().sendBroadcast(intent);
	}
	
	public PFCharacter getCharacter() {
		ViewPagerFragmentActivity view = (ViewPagerFragmentActivity) getActivity();
		if (view != null) {
			return view.getCharacter();
		}
		return null;
	}
	
    protected void setupEditTextTrigger(int id, String field) {
    	EditText e = (EditText) findViewById(id);
    	e.addTextChangedListener(new CustomTextWatcher(e, field, this));
    }
    
    protected void setupSpinnerTrigger(int id, String field) {
    	Spinner e = (Spinner) findViewById(id);
		e.setOnItemSelectedListener(new CustomSpinnerWatcher(e, field, this));
    }
	
	protected void populateField(int id, String ignore, String field, String value) {
		if (!ignore.equals(field)) {
			View v = findViewById(id);
			String type = v.getClass().getName();
			if (type.equals("android.widget.EditText")) {
				EditText e = (EditText) v;
				e.clearFocus();
	    		e.setText(value);
			} else if (type.equals("android.widget.TextView")) {
				TextView e = (TextView) v;
				e.clearFocus();
				e.setText(applyToggles(field, value));
			}
		}
	}
	
	public void populateSpinner(int id, String selected, String[] options) {
		Spinner spinner = (Spinner) _view.findViewById(id);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, options);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		for (int i = 0; i < options.length; i++) {
			String test = options[i];
			if (test.equals(selected)) {
				spinner.setSelection(i);
			}
		}    	
	}
	 
    // apply the stats from all of the toggles to a single field
	protected String applyToggles(String field, String value) {
		Log.d(TAG, "applyToggles(" + field + ", " + value +") - mods = " + _mods.size());
		for (int i = 0; i < _mods.size(); i++) {
			CharacterModifier mod = _mods.get(i);
			value = mod.apply(field, value);
		}
		return value;
	}
	
    protected View findViewById(int id) {
    	return _view.findViewById(id);
    }

	protected int parseIntField(int id) {
    	View v = findViewById(id);
		String type = v.getClass().getName();
		int i = 0;
		if (type.equals("android.widget.EditText")) {
			EditText tv = (EditText) v;
			i = Integer.parseInt(tv.getText().toString());
		} else if (type.equals("android.widget.TextView")) {
			TextView tv = (TextView) v;
			i = Integer.parseInt(tv.getText().toString());
		} else if (type.equals("android.widget.Spinner")) {
			Spinner sp = (Spinner) v;
			i = Integer.parseInt(sp.getSelectedItem().toString());
		}
		return i;
    }
    
    @Override
	public void onResume() {
     	IntentFilter filter = new IntentFilter("com.dmtprogramming.pathfindercombat.UPDATE_CHARACTER");
    	getActivity().registerReceiver(_receiver, filter);
		super.onResume();
	}

	@Override
	public void onPause() {
		getActivity().unregisterReceiver(_receiver);
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    if (databaseHelper != null) {
	        OpenHelperManager.releaseHelper();
	        databaseHelper = null;
	    }
	}
}

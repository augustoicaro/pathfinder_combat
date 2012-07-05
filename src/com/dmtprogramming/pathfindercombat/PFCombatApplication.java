package com.dmtprogramming.pathfindercombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dmtprogramming.pathfindercombat.database.*;
import com.dmtprogramming.pathfindercombat.models.Condition;

import android.app.Application;
import android.util.Log;

public class PFCombatApplication extends Application {
	
	private static final String TAG = "PFCombat:PFCombatApplication";
	private CharacterDataSource _character_db = null;
	private ConditionDataSource _condition_db = null;
	private List<Condition> _conditions;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "onCreate()");
		_character_db = new CharacterDataSource(this);
		_condition_db = new ConditionDataSource(this);

		setup();
	}
	
	public void setup() {	
		_conditions = Condition.loadConditions(getResources().openRawResource(R.raw.condition_data));		
	}

	public CharacterDataSource getCharacterDataSource() {
		return _character_db;
	}
	
	public ConditionDataSource getConditionDataSource() {
		return _condition_db;
	}
	
	public void openDataSources() {
		_character_db.open();
		_condition_db.open();
	}
	
	public void closeDataSources() {
		_character_db.close();
		_condition_db.close();
	}

	public List<Condition> getConditions() {
		return _conditions;
	}
	
	public List<String> getSortedConditionNames() {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < _conditions.size(); i++) {
			Condition condition = _conditions.get(i);
			ret.add(condition.getName());
		}
		Collections.sort(ret, new Comparator<String>() {

			@Override
			public int compare(String lhs, String rhs) {
				return lhs.compareToIgnoreCase(rhs);
			}
			
		});
		return ret;
	}
}

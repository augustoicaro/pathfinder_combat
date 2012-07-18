package com.dmtprogramming.pathfindercombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dmtprogramming.pathfindercombat.database.*;
import com.dmtprogramming.pathfindercombat.models.ConditionData;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;
import com.dmtprogramming.pathfindercombat.models.Toggles;
import com.dmtprogramming.pathfindercombat.modifier.ModifierBase;

import android.app.Application;
import android.util.Log;

public class PFCombatApplication extends Application {
	
	private static final String TAG = "PFCombat:PFCombatApplication";
	private CharacterDataSource _character_db = null;
	private ConditionDataSource _condition_db = null;
	private PFCharacter _current_character = null;
	private List<ConditionData> _conditionData;
	private List<ModifierBase> _toggles;
	private static PFCombatApplication _app = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "onCreate()");
		_character_db = new CharacterDataSource(this);
		_condition_db = new ConditionDataSource(this);

		_app = this;
		setup();
	}
	
	public static PFCombatApplication getApplication() {
		return _app;
	}
	
	public void setup() {	
		_conditionData = ConditionData.loadConditions(getResources().openRawResource(R.raw.condition_data));	
		_toggles = Toggles.loadToggles(getResources().openRawResource(R.raw.toggles));
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
	
	public String getConditionShortDescription(String conditionName) {
		for (int i = 0; i < _conditionData.size(); i++) {
			if (_conditionData.get(i).getName().equals(conditionName)) {
				return _conditionData.get(i).getShortDescription();
			}
		}
		return "";
	}
	
	public String getConditionLongDescription(String conditionName) {
		for (int i = 0; i < _conditionData.size(); i++) {
			if (_conditionData.get(i).getName().equals(conditionName)) {
				return _conditionData.get(i).getLongDescription();
			}
		}
		return "";
	}
	
	public List<String> getSortedConditionNames() {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < _conditionData.size(); i++) {
			ConditionData condition = _conditionData.get(i);
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

	public void setCurrentCharacter(PFCharacter _current_character) {
		this._current_character = _current_character;
	}

	public PFCharacter getCurrentCharacter() {
		return _current_character;
	}

	public List<ModifierBase> getToggles() {
		return _toggles;
	}
}

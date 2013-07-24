package br.augustoicaro.pathfindercombat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.augustoicaro.pathfindercombat.models.ConditionData;
import br.augustoicaro.pathfindercombat.models.PFCharacter;
import br.augustoicaro.pathfindercombat.models.Toggles;
import br.augustoicaro.pathfindercombat.modifier.ModifierBase;

import android.app.Application;
import android.util.Log;
import android.content.res.*;

public class PFCombatApplication extends Application {
	
	private static final String TAG = "PFCombat";

	private PFCharacter _current_character = null;
	private List<ConditionData> _conditionData;
	private List<ModifierBase> _toggles;
	private static PFCombatApplication _app = null;
	private static Resources _res = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v(TAG, "PFCombatApplication: onCreate()");

		_app = this;
		_res = getResources();
		//setup();
	}
	
	public static PFCombatApplication getApplication() {
		return _app;
	}
	
	public static String getString(int resId)
	{
		String mystring = _res.getString(resId);
		return mystring;
	}
	
	public void setup() {	
		_conditionData = ConditionData.loadConditions(getResources().openRawResource(R.raw.condition_data));
			Log.v(TAG, "PFCombatApplication: setup()" + _conditionData);
		_toggles = Toggles.loadToggles(getResources().openRawResource(R.raw.toggles));
			Log.v(TAG, "PFCombatApplication: setup()" + _toggles);
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
			ret.add(condition.getModifier().name());
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
	
	public List<ConditionData> getSortedConditionData() {
		List<ConditionData> ret = _conditionData;
		
		Collections.sort(ret, new Comparator<ConditionData>() {
		  @Override
			public int compare(ConditionData lhs, ConditionData rhs) {
				return lhs.getModifier().name().compareToIgnoreCase(rhs.getModifier().name());
			}
		});
	  return ret;
	}
}

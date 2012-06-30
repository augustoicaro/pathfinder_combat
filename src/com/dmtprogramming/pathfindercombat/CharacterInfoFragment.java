package com.dmtprogramming.pathfindercombat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CharacterInfoFragment extends FragmentBase {
	
	private static final String TAG = "PFCombat:CharacterInfoFragment";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		_view = inflater.inflate(R.layout.character_info, container, false);
		
		setupIntentFilter();
		
        setupView();
        populateStats("");

        setupTriggers();
		return _view;
	}
    
    // populate all of the stats from the db model
    // also uses the toggles to change the data
    public void populateStats(String f) {
    	Log.d(TAG, "populateStats(" + f + ")");
    	
    	// save some typing
    	PFCharacter c = getCharacter();
    	
    	populateField(R.id.txtCharacter, f, "name", c.getName());
    	populateField(R.id.txtPlayer, f, "player", c.getPlayer());
    	populateField(R.id.txtStr, f, "str", String.valueOf(c.getStr()));
    	populateField(R.id.txtDex, f, "dex", String.valueOf(c.getDex()));
    	populateField(R.id.txtCon, f, "con", String.valueOf(c.getCon()));
    	populateField(R.id.txtInt, f, "int", String.valueOf(c.getInt()));
    	populateField(R.id.txtWis, f, "wis", String.valueOf(c.getWis()));
    	populateField(R.id.txtCha, f, "cha", String.valueOf(c.getCha()));
    	populateField(R.id.txtStrMod, f, "strmod", String.valueOf(c.getStrMod()));
    	populateField(R.id.txtDexMod, f, "dexmod", String.valueOf(c.getDexMod()));
    	populateField(R.id.txtConMod, f, "conmod", String.valueOf(c.getConMod()));
    	populateField(R.id.txtIntMod, f, "intmod", String.valueOf(c.getIntMod()));
    	populateField(R.id.txtWisMod, f, "wismod", String.valueOf(c.getWisMod()));
    	populateField(R.id.txtChaMod, f, "chamod", String.valueOf(c.getChaMod()));
    }

	// set up the events for the toggle buttons
    private	 void setupView() {
    	String[] levels = { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20" };
    	populateSpinner(R.id.spinClass, getCharacter().getCharacterClass(), PFCharacter.CHARACTER_CLASSES);
    	populateSpinner(R.id.spinLevel, String.valueOf(getCharacter().getLevel() - 1), levels);
    }

    // set up the events for when text fields are updated by the user
    private void setupTriggers() {
    	setupEditTextTrigger(R.id.txtCharacter, "name");
    	setupEditTextTrigger(R.id.txtPlayer, "player");
    	setupEditTextTrigger(R.id.txtStr, "str");
    	setupEditTextTrigger(R.id.txtDex, "dex");
    	setupEditTextTrigger(R.id.txtCon, "con");
    	setupEditTextTrigger(R.id.txtInt, "int");
    	setupEditTextTrigger(R.id.txtWis, "wis");
    	setupEditTextTrigger(R.id.txtCha, "cha");
    	
    	setupSpinnerTrigger(R.id.spinClass, DatabaseHelper._c_characters_character_class);
    	setupSpinnerTrigger(R.id.spinLevel, DatabaseHelper._c_characters_level);
    }
    
    @Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onAfterUpdateCharacter(String field) {
		populateStats(field);
	}
}


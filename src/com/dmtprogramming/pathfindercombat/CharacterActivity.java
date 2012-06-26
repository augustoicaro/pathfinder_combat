package com.dmtprogramming.pathfindercombat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CharacterActivity extends Activity {
	
	private static final String TAG = "PFCombat:CharacterActivity";
	private PFCharacterDataSource datasource;
	private PFCharacter _char;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, String.format("test"));
        setContentView(R.layout.character);
        
        datasource = new PFCharacterDataSource(this);
        datasource.open();
        
        _char = null;
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	long _id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		_char = datasource.findCharacter(_id);
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        }
        
        populateStats("");
        setupTriggers();
    }
    
    protected void populateStats(String f) {
    	Log.d(TAG, "populateStats()");
    	
    	// same some typing
    	PFCharacter c = _char;
    	
    	populate(R.id.txtCharacter, f, "name", c.getName());
    	populate(R.id.txtPlayer, f, "player", c.getPlayer());
    	populate(R.id.txtLevel, f, "level", String.valueOf(c.getLevel()));
    	populate(R.id.txtStr, f, "str", String.valueOf(c.getStr()));
    	populate(R.id.txtDex, f, "dex", String.valueOf(c.getDex()));
    	populate(R.id.txtCon, f, "con", String.valueOf(c.getCon()));
    	populate(R.id.txtInt, f, "int", String.valueOf(c.getInt()));
    	populate(R.id.txtWis, f, "wis", String.valueOf(c.getWis()));
    	populate(R.id.txtCha, f, "cha", String.valueOf(c.getCha()));
    	populate(R.id.txtStrMod, f, "strmod", String.valueOf(c.getStrMod()));
    	populate(R.id.txtDexMod, f, "dexmod", String.valueOf(c.getDexMod()));
    	populate(R.id.txtConMod, f, "conmod", String.valueOf(c.getConMod()));
    	populate(R.id.txtIntMod, f, "intmod", String.valueOf(c.getIntMod()));
    	populate(R.id.txtWisMod, f, "wismod", String.valueOf(c.getWisMod()));
    	populate(R.id.txtChaMod, f, "chamod", String.valueOf(c.getChaMod()));
    	
    	populate(R.id.txtAttacks, f, "attacks", c.getAttacks());
    	populate(R.id.txtStrModPlusAttack, f, "strmod", String.valueOf(c.getStrMod()));
    	populate(R.id.txtWeaponFocusPlusAttack, f, "weapon_focus_atk", String.valueOf(c.getWeaponFocusMod()));
    	populate(R.id.txtPowerAttackPlusAttack, f, "power_attack_atk", String.valueOf(c.getPowerAttackMod()));
    	
    	populate(R.id.txtStrModPlusDamage, f, "strmod", String.valueOf(c.getStrMod()));
    	populate(R.id.txtPowerAttackPlusDamage, f, "power_attack_dmg", String.valueOf(c.getPowerAttackDamage()));
    }
    
    protected void populate(int id, String ignore, String field, String value) {
    	if (!ignore.equals(field)) {
    		View v = findViewById(id);
    		String type = v.getClass().getName();
    		if (type.equals("android.widget.EditText")) {
    			EditText e = (EditText) v;
        		e.setText(value);
    		} else if (type.equals("android.widget.TextView")) {
    			TextView e = (TextView) v;
    			e.setText(value);
    		}
    		
    	}
    }
    
    protected void setupTriggers() {
    	setupTrigger(R.id.txtCharacter, "name");
    	setupTrigger(R.id.txtPlayer, "player");
    	setupTrigger(R.id.txtLevel, "level");
    	setupTrigger(R.id.txtStr, "str");
    	setupTrigger(R.id.txtDex, "dex");
    	setupTrigger(R.id.txtCon, "con");
    	setupTrigger(R.id.txtInt, "int");
    	setupTrigger(R.id.txtWis, "wis");
    	setupTrigger(R.id.txtCha, "cha");
    }
    
    protected void setupTrigger(int id, String field) {
    	EditText e = (EditText) findViewById(id);
    	e.addTextChangedListener(new CustomTextWatcher(e, field, this));
    }
    
    public void updateCharacter(String field) {
    	Log.d(TAG, "updateCharacter()");
    	datasource.updatePFCharacter(_char);
    	populateStats(field);
    }
    
    public PFCharacter character() {
    	return _char;
    }
    
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
    
}

class CustomTextWatcher implements TextWatcher {
	private static final String TAG = "PFCombat:CustomTextWatcher";
	private EditText _text;
	private String _field;
	private CharacterActivity _activity;
	
	public CustomTextWatcher(EditText e, String field, CharacterActivity a) {
		_text = e;
		_field = field;
		_activity = a;
	}
	
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    public void afterTextChanged(Editable s) {
    	String txt = _text.getText().toString();
    	if (txt == null || txt.equals("")) {
    		return;
    	}
    	if (_activity.character().setData(_field, _text.getText().toString())) {
    		Log.d(TAG, _field + " updated successfully, updating the sheet");
    		_activity.updateCharacter(_field);
    	}
    }
}

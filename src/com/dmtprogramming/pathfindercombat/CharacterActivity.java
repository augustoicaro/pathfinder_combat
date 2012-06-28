package com.dmtprogramming.pathfindercombat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CharacterActivity extends Activity implements AdapterView.OnItemSelectedListener {
	
	private static final String TAG = "PFCombat:CharacterActivity";
	private PFCharacterDataSource datasource;
	private PFCharacter _char;
	private List<CharacterModifier> _mods;
	private CharacterModifier _smite;
		
	
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
    
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        final CharacterActivity t = this;
        btnDelete.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		new AlertDialog.Builder(t)
        		.setIcon(android.R.drawable.ic_dialog_alert)
        		.setTitle(R.string.delete)
        		.setMessage(R.string.really_delete)
        		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						datasource.deletePFCharacter(_char);
						finish();
					}
				})
				.setNegativeButton(R.string.no, null)
				.show();
        	}
        });
        
        setupToggles();
        populateStats("");

        setupTriggers();
    }
    
    // populate all of the stats from the db model
    // also uses the toggles to change the data
    public void populateStats(String f) {
    	Log.d(TAG, "populateStats(" + f + ")");
    	
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
    	populate(R.id.txtWeaponPlus, f, "weapon_plus", String.valueOf(c.getWeaponPlus()));
    	populate(R.id.txtWeaponDice, f, "weapon_damage", c.getWeaponDamage());
    	
    	populate(R.id.txtAttacks, f, "attacks", c.getAttacks());
    	populate(R.id.txtStrModPlusAttack, f, "strmod", String.valueOf(c.getStrMod()));
    	populate(R.id.txtWeaponPlusAttack, f, "weapon_plus", String.valueOf(c.getWeaponPlus()));
    	populate(R.id.txtWeaponFocusPlusAttack, f, "weapon_focus_atk", String.valueOf(c.getWeaponFocusMod()));
    	populate(R.id.txtOtherPlusAttack, f, "plus_hit", String.valueOf(0));
    	
    	populate(R.id.txtStrModPlusDamage, f, "strmod", String.valueOf(c.getStrMod()));
    	populate(R.id.txtWeaponPlusDamage, f, "weapon_plus", String.valueOf(c.getWeaponPlus()));
    	populate(R.id.txtOtherPlusDamage, f, "plus_damage", String.valueOf(c.getPowerAttackDamage()));
    	
    	populate(R.id.txtDailyCurrent, f, "daily_current", String.valueOf(c.getDailyCurrent()));
    	populate(R.id.txtDailyTotal, f, "daily_total", String.valueOf(c.getDailyTotal()));
    	populate(R.id.txtDailyTitle, f, "daily_title", c.getDailyTitle());
    	
    	// flurry of blows calcs
    	ToggleButton flurryOfBlows = (ToggleButton) findViewById(R.id.btnFlurryOfBlows);
    	flurryOfBlows.setChecked(_char.getFlurryOfBlows());
    	
    	// unarmed calcs
    	ToggleButton unarmed = (ToggleButton) findViewById(R.id.btnUnarmed);
    	unarmed.setChecked(_char.getUnarmed());
    	
    	// weapon focus calcs
    	ToggleButton weaponFocus = (ToggleButton) findViewById(R.id.btnWeaponFocus);
    	weaponFocus.setChecked(_char.getWeaponFocus());
    	if (weaponFocus.isChecked()) {
    		TextView tv = (TextView) findViewById(R.id.txtWeaponFocusPlusAttack);
    		tv.clearFocus();
    		tv.setText("1");
    	}
    	
    	// size modifier calcs
    	String weapon_damage = _char.getWeaponDamage();
    	int sizeMod = 0;
    	String sizeStr = applyToggles("size", "");
    	if (!sizeStr.equals("")) {
    		sizeMod = Integer.parseInt(sizeStr);
    	}
    	if (sizeMod > 0) {
    		int ind = -1;
    		for (int i = 0; i < PFCharacter.MEDIUM_WEAPON_DAMAGES.length; i++) {
    			if (PFCharacter.MEDIUM_WEAPON_DAMAGES[i].equals(weapon_damage)) {
    				ind = i;
    			}
    		}
    		if (ind != -1) {
    			weapon_damage = PFCharacter.LARGE_WEAPON_DAMAGES[ind];
    		}
    	}
    	
    	// extra attack calc
    	String extraAttack = applyToggles("extra_attack", "false");
    	if (extraAttack.equals("true")) {
    		TextView tv = (TextView) findViewById(R.id.txtAttacks);
    		String attacks = tv.getText().toString();
    		attacks = attacks.concat(" / " + _char.getBAB());
    		tv.clearFocus();
    		tv.setText(attacks);
    	}
    	
    	EditText weaponPlus = (EditText) findViewById(R.id.txtWeaponPlus);
    	TextView weaponPlusAttack = (TextView) findViewById(R.id.txtWeaponPlusAttack);
    	TextView weaponPlusDamage = (TextView) findViewById(R.id.txtWeaponPlusDamage);
    	weaponPlusAttack.clearFocus();
    	weaponPlusAttack.setText(weaponPlus.getText());
    	weaponPlusDamage.clearFocus();
    	weaponPlusDamage.setText(weaponPlus.getText());
    	
    	// plus to hit calcs
    	int plusHit = 0;
    	plusHit += parseIntField(R.id.txtStrModPlusAttack);
    	plusHit += parseIntField(R.id.txtWeaponPlus);
    	plusHit += parseIntField(R.id.txtWeaponFocusPlusAttack);
    	plusHit += parseIntField(R.id.txtOtherPlusAttack);
    	String attacks = (String) ((TextView) findViewById(R.id.txtAttacks)).getText();
    	calculateFinalPlusHit(attacks, plusHit);
    	
    	// plus to damage calcs
    	int plusDamage = 0;
    	plusDamage += parseIntField(R.id.txtStrModPlusDamage);
    	plusDamage += parseIntField(R.id.txtWeaponPlus);
    	plusDamage += parseIntField(R.id.txtOtherPlusDamage);  
    	calculateFinalPlusDamage(weapon_damage, plusDamage);
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
		}
		return i;
    }
    
    protected void calculateFinalPlusHit(String attacks_str, int plusHit) {
    	TextView tv = (TextView) findViewById(R.id.txtPlusAttack);
    	String finalPlusAttack = "";
    	String[] attacks = attacks_str.split("/");
    	for (int i = 0; i < attacks.length; i++) {
    		int hit = Integer.parseInt(attacks[i].trim());
    		if (!finalPlusAttack.equals("")) {
    			finalPlusAttack += " / ";
    		}
    		finalPlusAttack = finalPlusAttack + String.valueOf(hit + plusHit);
    	}
    	tv.clearFocus();
    	tv.setText(finalPlusAttack);
    }
    
    protected void calculateFinalPlusDamage(String weaponDice, int plusDamage) {
    	TextView tv = (TextView) findViewById(R.id.txtPlusDamage);
    	String plusDamageDice = applyToggles("damage_dice", "");
    	if (!plusDamageDice.equals("")) {
    		plusDamageDice = " + " + plusDamageDice;
    	}
    	ToggleButton crit = (ToggleButton) findViewById(R.id.btnCritical);
    	if (crit.isChecked()) {
    		plusDamage *= 2;
    	}
    	tv.clearFocus();
    	tv.setText(weaponDice + " + " + String.valueOf(plusDamage) + plusDamageDice);
    }
    
    protected void populate(int id, String ignore, String field, String value) {
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
    
    // apply the stats from all of the toggles to a single field
    protected String applyToggles(String field, String value) {
    	Log.d(TAG, "applyToggles(" + field + ", " + value +") - mods = " + _mods.size());
    	for (int i = 0; i < _mods.size(); i++) {
    		CharacterModifier mod = _mods.get(i);
    		value = mod.apply(field, value);
    	}
    	return value;
    }
    
    // update smite when the character gets updated
    protected void updateSmite() {
    	_smite.hit = _char.getChaMod();
    	_smite.damage = _char.getLevel();
    }
    
    // callback for the damage spinner
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
    	Spinner damageSpinner = (Spinner) findViewById(R.id.spinDamage);
    	_char.setWeaponDamage(damageSpinner.getSelectedItem().toString());
    	Spinner classSpinner = (Spinner) findViewById(R.id.spinClass);
    	_char.setCharacterClass(classSpinner.getSelectedItem().toString());
    	updateCharacter("");
    }
    
    // set up the events for the toggle buttons
    protected void setupToggles() {
    	
    	Spinner damageSpinner = (Spinner) findViewById(R.id.spinDamage);
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PFCharacter.MEDIUM_WEAPON_DAMAGES);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	damageSpinner.setAdapter(adapter);
    	damageSpinner.setOnItemSelectedListener(this);
    	
    	for (int i = 0; i < PFCharacter.MEDIUM_WEAPON_DAMAGES.length; i++) {
    		String test = PFCharacter.MEDIUM_WEAPON_DAMAGES[i];
    		if (test.equals(_char.getWeaponDamage())) {
    			damageSpinner.setSelection(i);
    		}
    	}
    	
    	Spinner classSpinner = (Spinner) findViewById(R.id.spinClass);
    	ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PFCharacter.CHARACTER_CLASSES);
    	adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	classSpinner.setAdapter(adapter2);
    	classSpinner.setOnItemSelectedListener(this);
    	
    	for (int i = 0; i < PFCharacter.CHARACTER_CLASSES.length; i++) {
    		String test = PFCharacter.CHARACTER_CLASSES[i];
    		if (test.equals(_char.getCharacterClass())) {
    			classSpinner.setSelection(i);
    		}
    	}
    	
    	_mods = new ArrayList<CharacterModifier>();

    	CharacterModifier powerAttack = new CharacterModifier();
    	powerAttack.hit = -2;
    	powerAttack.damage = 4;
    	
    	_smite = new CharacterModifier();
    	updateSmite();
    	
    	CharacterModifier plus2Str = new CharacterModifier();
    	plus2Str.str = 2;
    	
    	CharacterModifier plus4Str = new CharacterModifier();
    	plus4Str.str = 4;
    	
    	CharacterModifier plus1Hit = new CharacterModifier();
    	plus1Hit.hit = 1;
    	
    	CharacterModifier plus2Hit = new CharacterModifier();
    	plus2Hit.hit = 2;
    	
    	CharacterModifier plus4Hit = new CharacterModifier();
    	plus4Hit.hit = 4;
    	
    	CharacterModifier flank = new CharacterModifier();
    	flank.hit = 2;
    	
    	CharacterModifier plus1Damage = new CharacterModifier();
    	plus1Damage.damage = 1;
    	
    	CharacterModifier plus2Damage = new CharacterModifier();
    	plus2Damage.damage = 2;
    	
    	CharacterModifier fightDefensively = new CharacterModifier();
    	fightDefensively.hit = -4;
    	
    	CharacterModifier plus1d6 = new CharacterModifier();
    	plus1d6.damageDice = "1d6";
    	
    	CharacterModifier plus2d6 = new CharacterModifier();
    	plus2d6.damageDice = "2d6";
    	
    	CharacterModifier crit = new CharacterModifier();
    	
    	CharacterModifier enlargePerson = new CharacterModifier();
    	enlargePerson.size = 1;
    	enlargePerson.str = 2;
    	enlargePerson.hit = -1;
    	
    	CharacterModifier haste = new CharacterModifier();
    	haste.hit = 1;
    	haste.extraAttack = true;
    	
    	addToggle(R.id.btnPowerAttack, powerAttack);
    	addToggle(R.id.btnSmite, _smite);
    	addToggle(R.id.btnPlus2Str, plus2Str);
    	addToggle(R.id.btnPlus4Str, plus4Str);
    	addToggle(R.id.btnPlus1Hit, plus1Hit);
    	addToggle(R.id.btnPlus2Hit, plus2Hit);
    	addToggle(R.id.btnPlus4Hit, plus4Hit);
    	addToggle(R.id.btnFlank, flank);
    	addToggle(R.id.btnPlus1Damage, plus1Damage);
    	addToggle(R.id.btnPlus2Damage, plus2Damage);
    	addToggle(R.id.btnFightDefensively, fightDefensively);
    	addToggle(R.id.btnPlus1d6, plus1d6);
    	addToggle(R.id.btnPlus2d6, plus2d6);
    	addToggle(R.id.btnCritical, crit);
    	addToggle(R.id.btnEnlargePerson, enlargePerson);
    	addToggle(R.id.btnHaste, haste);
    	
    	ToggleButton weaponFocus = (ToggleButton) findViewById(R.id.btnWeaponFocus);
    	weaponFocus.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ToggleButton tb = (ToggleButton) v;
    			_char.setWeaponFocus(tb.isChecked());
    			updateCharacter("weapon_focus");
    		}
    	});
    	
    	ToggleButton flurryOfBlows = (ToggleButton) findViewById(R.id.btnFlurryOfBlows);
    	flurryOfBlows.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ToggleButton tb = (ToggleButton) v;
    			_char.setFlurryOfBlows(tb.isChecked());
    			updateCharacter("flurry_of_blows");
    		}
    	});
    	
    	ToggleButton unarmed = (ToggleButton) findViewById(R.id.btnUnarmed);
    	unarmed.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ToggleButton tb = (ToggleButton) v;
    			_char.setUnarmed(tb.isChecked());
    			updateCharacter("unarmed");
    		}
    	});
    }
    
    protected void addToggle(int id, CharacterModifier mod) {
    	ToggleButton t = (ToggleButton) findViewById(id);
    	t.setOnClickListener(new ToggleClickListener(t, mod, this));
    	_mods.add(mod);
    }
    
    // set up the events for when text fields are updated by the user
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
    	setupTrigger(R.id.txtWeaponPlus, "weapon_plus");
    	setupTrigger(R.id.txtDailyTitle, "daily_title");
    	setupTrigger(R.id.txtDailyCurrent, "daily_current");
    	setupTrigger(R.id.txtDailyTotal, "daily_total");
    }
    
    protected void setupTrigger(int id, String field) {
    	EditText e = (EditText) findViewById(id);
    	e.addTextChangedListener(new CustomTextWatcher(e, field, this));
    }
    
    // saves the character after a field update and refreshes everything
    public void updateCharacter(String field) {
    	Log.d(TAG, "updateCharacter()");
    	datasource.updatePFCharacter(_char);
    	updateSmite();
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

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
}

// text watcher for the various text fields on the page
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

// events for the toggle buttons
class ToggleClickListener implements OnClickListener {
	//private static final String TAG = "PFCombat:ToggleClickListener";
	
	private ToggleButton _toggle;
	private CharacterModifier _mod;
	private CharacterActivity _activity;
	
	public ToggleClickListener(ToggleButton toggle, CharacterModifier mod, CharacterActivity activity) {
		_toggle = toggle;
		_mod = mod;
		_activity = activity;
	}
	
	public void onClick(View v) {
		_mod.enabled = _toggle.isChecked();
		_activity.populateStats("");
	}
}

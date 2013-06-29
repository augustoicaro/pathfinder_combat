package com.dmtprogramming.pathfindercombat;

import java.sql.SQLException;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.Weapon;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class WeaponEditActivity extends Activity {
	private static final String TAG = "PFCombat";
	private Weapon _weapon;
	private PFCombatApplication _app;
	private boolean new_record;
	
	private DatabaseHelper databaseHelper = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.weapon_edit_dialog);

        _app = (PFCombatApplication)this.getApplication();
        
        Bundle extras = getIntent().getExtras();
        long _id = -1;
        if (extras != null) {
        	_id = extras.getLong("WEAPON_ID");
        }
        
        _weapon = null;

        if (extras != null) {
        	if (_id > 0) {
        		try {
            		Dao<Weapon, Integer> dao = getHelper().getWeaponDao();
					_weapon = dao.queryForId((int) _id);
				} catch (SQLException e) {
					_weapon = null;
				}
        	}
        }
        
        if (_weapon != null) {
					Log.d(TAG, "WeaponEditActivity: loaded weapon with id = " + _weapon.getId());
        	new_record = false;
        } else {
        	_weapon = new Weapon();
        	new_record = true;
        }
        
        if (!new_record) {
        	Button add = (Button) findViewById(R.id.btnAdd);
        	add.setText("Update");
        }
        
        populateFields();
        setupButtons();
    }
	 
    private void populateFields() {
    	EditText name = (EditText) findViewById(R.id.txtWeaponName);
    	name.setText(_weapon.getName());
    	
    	String[] weaponHit = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
    	String[] weaponDamage = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
    	populateSpinner(R.id.spinWeaponHit, String.valueOf(_weapon.getHit()), weaponHit);
    	populateSpinner(R.id.spinWeaponDamage, String.valueOf(_weapon.getDamage()), weaponDamage);
    	populateSpinner(R.id.spinWeaponCritical, String.valueOf(_weapon.getCriticalMultiplier()), Weapon.CRITICAL_MULIPLIERS);
    	
    	String[] weaponRanges = { "Melee", "10", "20", "30", "50", "60", "70", "80", "90", "100", "110", "120" };
    	String weaponRange = String.valueOf(_weapon.getRange());
    	if (weaponRange.equals("0")) {
    		weaponRange = "Melee";
    	}
    	populateSpinner(R.id.spinWeaponRange, weaponRange, weaponRanges);
    	
    	String[] damageDice = {"1d2", "1d3", "1d4", "1d6", "1d8", "1d10", "1d12", "2d4", "2d6", "2d8", "2d10" };
    	populateSpinner(R.id.spinWeaponDice, _weapon.getDamageDice(), damageDice);

    	String[] additionalDice = { "", "1d4", "2d4", "3d4", "1d6", "2d6", "3d6", "1d8", "2d8", "3d8" };
    	populateSpinner(R.id.spinWeaponAdditionalDice, _weapon.getAdditionalDamageDice(), additionalDice);
    }
    
	private void populateSpinner(int id, String selected, String[] options) {
		Spinner spinner = (Spinner) findViewById(id);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		for (int i = 0; i < options.length; i++) {
			String test = options[i];
			if (test.equals(selected)) {
				spinner.setSelection(i);
			}
		}    	
	}
    
    private void populateWeapon() {
    	_weapon.setName(fieldValue(R.id.txtWeaponName));
    	_weapon.setHit(Integer.parseInt(fieldValue(R.id.spinWeaponHit)));
    	_weapon.setDamage(Integer.parseInt(fieldValue(R.id.spinWeaponDamage)));
    	_weapon.setCriticalMultiplier(Integer.parseInt(fieldValue(R.id.spinWeaponCritical)));
    	
    	String range = fieldValue(R.id.spinWeaponRange);
    	if (range.equals("Melee")) {
    		range = "0";
    	}
    	_weapon.setRange(Integer.parseInt(range));
    	_weapon.setDamageDice(fieldValue(R.id.spinWeaponDice));
    	_weapon.setAdditionalDamageDice(fieldValue(R.id.spinWeaponAdditionalDice));    	
    }
    
    private String fieldValue(int id) {
    	View v = findViewById(id);
		String type = v.getClass().getName();
		if (type.equals("android.widget.EditText")) {
			EditText tv = (EditText) v;
			return tv.getText().toString();
		} else if (type.equals("android.widget.Spinner")) {
			Spinner sp = (Spinner) v;
			return sp.getSelectedItem().toString();
		}
		return "";
    }
    
    private void setupButtons() {
    	Button add = (Button) findViewById(R.id.btnAdd);
    	add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				populateWeapon();
	    		try {
	        		Dao<Weapon, Integer> dao = getHelper().getWeaponDao();
	        		if (new_record) {
	        			_weapon.setCharacter(_app.getCurrentCharacter());
	        			dao.create(_weapon);
	        		} else {
	        			dao.update(_weapon);
	        		}
				} catch (SQLException e) {
					Log.d(TAG, "WeaponEditActivity: error saving weapon to database");
				}
				finish();
			}
		});  
    	Button cancel = (Button) findViewById(R.id.btnCancel);
    	cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});  
    }
    
	protected DatabaseHelper getHelper() {
	    if (databaseHelper == null) {
	        databaseHelper =
	            OpenHelperManager.getHelper(this, DatabaseHelper.class);
	    }
	    return databaseHelper;
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

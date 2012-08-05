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
import android.widget.Button;
import android.widget.EditText;

public class WeaponEditActivity extends Activity {
	private static final String TAG = "PFCombat:WeaponEditActivity";
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
        	Log.d(TAG, "loaded weapon with id = " + _weapon.getId());
        	new_record = false;
        } else {
        	_weapon = new Weapon();
        	new_record = true;
        }
        
        populateFields();
        setupButtons();
    }
	 
    private void populateFields() {
    	EditText name = (EditText) findViewById(R.id.txtWeaponName);
    	name.setText(_weapon.getName());
    }
    
    private void populateWeapon() {
    	EditText name = (EditText) findViewById(R.id.txtWeaponName);
    	_weapon.setName(name.getText().toString());
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
					Log.d(TAG, "error saving weapon to database");
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

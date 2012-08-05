package com.dmtprogramming.pathfindercombat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;
import com.dmtprogramming.pathfindercombat.models.Weapon;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WeaponActivity extends ListActivity {
	
	private static final String TAG = "PFCombat:WeaponActivity";
	private PFCharacter _char;
	private List<Weapon> values; 
	
	private DatabaseHelper databaseHelper = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.v(TAG, String.format("test"));
        setContentView(R.layout.weapon_dialog);

        Bundle extras = getIntent().getExtras();
        long _id = -1;
        _char = null;
        values = new ArrayList<Weapon>();

        if (extras != null) {
        	_id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		try {
            		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
					_char = dao.queryForId((int) _id);
				} catch (SQLException e) {
					_char = null;
				}
        	}
        }
        
        if (_char == null) {
        	finish();
        }
        
        populateList();
    }
    
	public void onClick(View view) {
		Intent editIntent = new Intent(view.getContext(), WeaponEditActivity.class);
		editIntent.putExtra("CHARACTER_ID", _char.getId());
		int pos = getListView().getCheckedItemPosition();
		Weapon weapon = values.get(pos);
		
		switch (view.getId()) {
		case R.id.add_weapon:
			startActivityForResult(editIntent, 0);
			break;
		case R.id.edit_weapon:
			if (weapon != null) {
				editIntent.putExtra("WEAPON_ID", weapon.getId());
				startActivityForResult(editIntent, 0);
			} else {
				Log.d(TAG, "no weapon selected!!");
			}
			break;
		case R.id.select_weapon:
			if (weapon != null) {
				_char.setWeapon(weapon);
				Log.d(TAG, "equiping weapon - " + weapon.toString());
				Log.d(TAG, "character weapon = " + _char.getWeapon().toString());
	    		Dao<PFCharacter, Integer> dao;
				try {
					dao = getHelper().getCharacterDao();
					dao.update(_char);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			}
		}
	}
	
	@Override
	public void onResume() {
		populateList();
		super.onResume();
	}

	protected void populateList() {
		try {
    		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
			dao.refresh(_char);
		} catch (SQLException e) {
			_char = null;
		}
		ForeignCollection<Weapon> weapons = _char.getWeapons();
		values.clear();
		Iterator<Weapon> iter = weapons.iterator();
		while (iter.hasNext()) {
			Weapon w = iter.next();
			values.add(w);
		}
		ArrayAdapter<Weapon> adapter = new ArrayAdapter<Weapon>(this, android.R.layout.simple_list_item_activated_1, values);
		setListAdapter(adapter);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		getListView().setItemsCanFocus(true);
		
        Weapon weapon = _char.getWeapon();
        if (weapon != null) {
        	long id = weapon.getId();
        	Iterator<Weapon> iter2 = values.iterator();
        	int ind = 0;
        	while (iter2.hasNext()) {
        		Weapon w = iter2.next();
        		if (w.getId() == id) {
        			getListView().setItemChecked(ind, true);
        		}
        		ind += 1;
        	}
        }
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

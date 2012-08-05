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
	
	private DatabaseHelper databaseHelper = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        Log.v(TAG, String.format("test"));
        setContentView(R.layout.weapon_dialog);

        Bundle extras = getIntent().getExtras();
        long _id = -1;
        if (extras != null) {
        	_id = extras.getLong("CHARACTER_ID");
        }
        
        _char = null;

        if (extras != null) {
        	if (_id > 0) {
        		try {
            		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
					_char = dao.queryForId((int) _id);
				} catch (SQLException e) {
					_char = null;
				}
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        } else {
        	Log.d(TAG, "CHARACTER IS NULL!!!!!!!");
        }
        
        populateList();
    }
    
	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.add_weapon:
			Intent myIntent = new Intent(view.getContext(), WeaponEditActivity.class);
			myIntent.putExtra("CHARACTER_ID", _char.getId());
			startActivityForResult(myIntent, 0);
		}
	}
	
	@Override
	public void onResume() {
		populateList();
		super.onResume();
	}
/*
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Weapon weapon = (Weapon) getListAdapter().getItem(position);
		Log.d(TAG, "weapon selected with id = " + weapon.getId());
		
		Intent myIntent = new Intent(v.getContext(), ViewPagerFragmentActivity.class);;

		myIntent.putExtra("WEAPON_ID", weapon.getId());
		startActivityForResult(myIntent, 0);
	}
	*/
	protected void populateList() {
		try {
    		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
			dao.refresh(_char);
		} catch (SQLException e) {
			_char = null;
		}
		ForeignCollection<Weapon> weapons = _char.getWeapons();
		List<Weapon> values = new ArrayList<Weapon>();
		Iterator<Weapon> iter = weapons.iterator();
		while (iter.hasNext()) {
			Weapon w = iter.next();
			values.add(w);
		}
		ArrayAdapter<Weapon> adapter = new ArrayAdapter<Weapon>(this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
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

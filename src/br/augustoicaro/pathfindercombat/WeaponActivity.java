package br.augustoicaro.pathfindercombat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.augustoicaro.pathfindercombat.database.DatabaseHelper;
import br.augustoicaro.pathfindercombat.models.PFCharacter;
import br.augustoicaro.pathfindercombat.models.Weapon;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WeaponActivity extends ListActivity {
	
	private static final String TAG = "PFCombat";
	private PFCharacter _char;
	private List<Weapon> values; 
	
	private DatabaseHelper databaseHelper = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
				Log.v(TAG, String.format("WeaponActivity: test"));
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
		Weapon weapon = null;
		if (pos != -1) {
			weapon = values.get(pos);
		}
		
		switch (view.getId()) {
		case R.id.add_weapon:
			startActivityForResult(editIntent, 0);
			break;
		case R.id.edit_weapon:
			if (weapon != null) {
				editIntent.putExtra("WEAPON_ID", weapon.getId());
				startActivityForResult(editIntent, 0);
			} else {
				Log.d(TAG, "WeaponActivity: no weapon selected!!");
			}
			break;
		case R.id.select_weapon:
			if (weapon != null) {
				_char.setWeapon(weapon);
				Log.d(TAG, "WeaponActivity: equiping weapon - " + weapon.toString());
				Log.d(TAG, "WeaponActivity: character weapon = " + _char.getWeapon().toString());
	    	Dao<PFCharacter, Integer> dao;
				try {
					dao = getHelper().getCharacterDao();
					dao.update(_char);
					dao.refresh(_char);
					
					Dao<Weapon, Integer> weaponDao = getHelper().getWeaponDao();
					weaponDao.update(weapon);
					weaponDao.refresh(weapon);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Intent intent = this.getIntent();
				intent.putExtra("FIELD", "weapon_id");
				if (getParent() == null) {
					setResult(Activity.RESULT_OK, intent);
				} else {
					getParent().setResult(Activity.RESULT_OK, intent);
				}
				finish();
			}
			break;
		case R.id.delete_weapon:
			if (weapon != null) {
				showConfirmDelete();
			}
			break;
		}
	}
	
	private void showConfirmDelete() {
		new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle("Delete")
			.setMessage("Really delete selected weapon?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					int pos = getListView().getCheckedItemPosition();
					Weapon weapon = values.get(pos);
					Dao<Weapon, Integer> dao;
					try {
						dao = getHelper().getWeaponDao();
						dao.delete(weapon);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					populateList();
				}
			})
			.setNegativeButton("No", null)
			.show();
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

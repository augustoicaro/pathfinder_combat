package com.dmtprogramming.pathfindercombat;

import java.sql.SQLException;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.dmtprogramming.pathfindercombat.ViewPagerFragmentActivity;
import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.*;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class MainActivity extends ListActivity {
	
	private static final String TAG = "PFCombat";
	private PFCombatApplication _app;
	private DatabaseHelper databaseHelper = null;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
				Log.v(TAG, String.format("MainActivity: test"));
        setContentView(R.layout.main);
        
        _app = (PFCombatApplication)this.getApplication();
        
        populateList();
    }
    
	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<PFCharacter> adapter = (ArrayAdapter<PFCharacter>) getListAdapter();
		PFCharacter cha = null;
		switch (view.getId()) {
		case R.id.add:
    		try {
        		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
        		cha = new PFCharacter();
        		cha.setName("character");
        		dao.create(cha);
			} catch (SQLException e) {
				cha = null;
			}
			adapter.add(cha);
			break;
		}
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onResume() {
		populateList();
		super.onResume();
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		PFCharacter cha = (PFCharacter) getListAdapter().getItem(position);
		Log.d(TAG, "MainActivity: character selected with id = " + cha.getId());
		
		Intent myIntent = new Intent(v.getContext(), ViewPagerFragmentActivity.class);;
		
		int layout = getResources().getConfiguration().screenLayout;
		Log.d(TAG, "MainActivity: screen layout = " + layout);
		if (isScreenSize(Configuration.SCREENLAYOUT_SIZE_XLARGE)) {
			myIntent.putExtra("TABLET", true);
		} else {
			myIntent.putExtra("TABLET", false);
		}

		myIntent.putExtra("CHARACTER_ID", cha.getId());
		_app.setCurrentCharacter(cha);
		startActivityForResult(myIntent, 0);
	}
	
	private boolean isScreenSize(int test_size) {
		int size = getResources().getConfiguration().screenLayout;
		return (size & test_size) == test_size;
	}
	
	protected void populateList() {
		Dao<PFCharacter, Integer> dao;
		try {
			dao = getHelper().getCharacterDao();
	        List<PFCharacter> values = dao.queryForAll();
	        ArrayAdapter<PFCharacter> adapter = new ArrayAdapter<PFCharacter>(this, android.R.layout.simple_list_item_1, values);
	        setListAdapter(adapter);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

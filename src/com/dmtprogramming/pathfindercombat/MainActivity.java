package com.dmtprogramming.pathfindercombat;

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
import com.dmtprogramming.pathfindercombat.models.*;

public class MainActivity extends ListActivity {
	
	private static final String TAG = "PFCombat:MainActivity";
	private PFCombatApplication _app;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, String.format("test"));
        setContentView(R.layout.main);
        
        _app = (PFCombatApplication)this.getApplication();
        _app.openDataSources();
        
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
			cha = _app.getCharacterDataSource().createPFCharacter("character");
			adapter.add(cha);
			break;
		}
		adapter.notifyDataSetChanged();
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		PFCharacter cha = (PFCharacter) getListAdapter().getItem(position);
		Log.d(TAG, "character selected with id = " + cha.getId());
		
		Intent myIntent = new Intent(v.getContext(), ViewPagerFragmentActivity.class);;
		
		int layout = getResources().getConfiguration().screenLayout;
		Log.d(TAG, "screen layout = " + layout);
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
        List<PFCharacter> values = _app.getCharacterDataSource().getAllPFCharacters();
        
        ArrayAdapter<PFCharacter> adapter = new ArrayAdapter<PFCharacter>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);		
	}
	
	@Override
	protected void onResume() {
		_app.openDataSources();
		populateList();
		super.onResume();
	}

	@Override
	protected void onPause() {
		_app.closeDataSources();
		super.onPause();
	}
}
package com.dmtprogramming.pathfindercombat;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	private static final String TAG = "PFCombat:MainActivity";
	private PFCharacterDataSource datasource;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, String.format("test"));
        setContentView(R.layout.main);
        
        datasource = new PFCharacterDataSource(this);
        datasource.open();
        
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
			cha = datasource.createPFCharacter("character");
			adapter.add(cha);
			break;
	/*	case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				cha = (PFCharacter) getListAdapter().getItem(0);
				datasource.deletePFCharacter(cha);
				adapter.remove(cha);
			}
			break;*/
		}
		adapter.notifyDataSetChanged();
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		PFCharacter cha = (PFCharacter) getListAdapter().getItem(position);
		Log.d(TAG, "character selected with id = " + cha.getId());
		
		Intent myIntent = new Intent(v.getContext(), CharacterActivity.class);
		myIntent.putExtra("CHARACTER_ID", cha.getId());
		startActivityForResult(myIntent, 0);
	}
	
	protected void populateList() {
        List<PFCharacter> values = datasource.getAllPFCharacters();
        
        ArrayAdapter<PFCharacter> adapter = new ArrayAdapter<PFCharacter>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);		
	}
	
	@Override
	protected void onResume() {
		datasource.open();
		populateList();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}
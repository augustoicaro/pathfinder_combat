package br.augustoicaro.pathfindercombat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import br.augustoicaro.pathfindercombat.ViewPagerFragmentActivity;
import br.augustoicaro.pathfindercombat.database.DatabaseHelper;
import br.augustoicaro.pathfindercombat.models.*;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import android.widget.*;

public class MainActivity extends ListActivity {
	
	private static final String TAG = "PFCombat";
	private PFCombatApplication _app;
	private DatabaseHelper databaseHelper = null;
	private List<CharacterViewHolder> holders;
	private List<PFCharacter> checked;
	private CharacterArrayAdapter _listAdapter;
	private List<PFCharacter> characters;
	public static String locale = "English";
		
    
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
		Log.v(TAG, String.format("MainActivity: test"));
    setContentView(R.layout.main);
		Log.v(TAG, String.format("MainActivity: test1"));
		holders = new ArrayList<CharacterViewHolder>();
	  checked = new ArrayList<PFCharacter>();
        
    _app = (PFCombatApplication)this.getApplication();
		Log.v(TAG, String.format("MainActivity: test2"));
    populateList();
		Log.v(TAG, String.format("MainActivity: test3"));
  }
    
	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		PFCharacter cha = null;
		switch (view.getId()) {
		case R.id.add:
			Log.v(TAG, String.format("MainActivity: Create a new character..."));
		  try {
        Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
       	cha = new PFCharacter();
       	cha.setName("Character");
        dao.create(cha);
			} catch (SQLException e) {
				cha = null;
			}
      populateList();
			break;
		case R.id.delete_cha:
			Log.v(TAG, String.format("MainActivity: Deleting checked characters"));
			ListView lv = (ListView) findViewById(android.R.id.list);
			List<PFCharacter> deleters;
			if (lv == null) {
				deleters = checked;
			} else {
				deleters = _listAdapter.checked();
				Log.v(TAG, String.format("MainActivity: Deleting checked characters: " + _listAdapter.checked()));
			}
			for (int i = 0; i < deleters.size(); i++) {
				Dao<PFCharacter, Integer> dao;
				try {
					dao = getHelper().getCharacterDao();
					dao.delete(deleters.get(i));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			populateList();
		  break;
		}
	}
	
	@Override
	public void onResume() {
		populateList();
		super.onResume();
	}

	private boolean isScreenSize(int test_size) {
		int size = _app.getResources().getConfiguration().screenLayout;
		return (size & test_size) == test_size;
	}
		
	protected void populateList() {
		if (_listAdapter != null) {
			_listAdapter.notifyDataSetInvalidated();
		}

		holders.clear();
		checked.clear();

		_listAdapter = null;

		Dao<PFCharacter, Integer> dao;
		try {
			dao = getHelper().getCharacterDao();
	    characters = dao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		 	e.printStackTrace();
	 	}
		Log.d(TAG, "MainActivity: Populatelist() characters = " + characters);
		if (characters != null) { 
			_listAdapter = new CharacterArrayAdapter(this, characters, _app, this);
		}

		ListView lv = (ListView) findViewById(android.R.id.list);
		if (lv == null) {
			lv.removeAllViews();
			Iterator<PFCharacter> i = characters.iterator();
			while (i.hasNext()) {
				PFCharacter character = i.next();
				View vi = this.getLayoutInflater().inflate(R.layout.main_row, null);
				final CharacterViewHolder holder = new CharacterViewHolder();
				holder.textView = (TextView) vi.findViewById(R.id.rowTextView2);
				holder.checkBox = (CheckBox) vi.findViewById(R.id.rowCheckBox2);
				holder.row = (RelativeLayout) vi.findViewById(R.id.layoutCharacterRow);
				holder.character = character;

				holder.checkBox.setText("");
				holder.textView.setText(character.getName());
			  holder.row.setOnClickListener(new View.OnClickListener() {
			  	@Override
					public void onClick(View v) {
				  	Log.v(TAG, String.format("MainActivity: Click on row"));
						PFCharacter cha = holder.character;
						locale = v.getContext().getResources().getConfiguration().locale.getDisplayLanguage();
						Log.d(TAG, String.format("MainActivity: System language is *" + locale + "*"));			
						Log.d(TAG, "MainActivity: character selected with id = " + cha.getId());

						_app.setup();

						Intent myIntent = new Intent(v.getContext(), ViewPagerFragmentActivity.class);

						int layout = getResources().getConfiguration().screenLayout;
						Log.d(TAG, "MainActivity: screen layout = " + layout);
						if (isScreenSize(Configuration.SCREENLAYOUT_SIZE_XLARGE) || isScreenSize(Configuration.SCREENLAYOUT_SIZE_LARGE)) {
							myIntent.putExtra("TABLET", true);
						} else {
							myIntent.putExtra("TABLET", false);
						}

						myIntent.putExtra("CHARACTER_ID", cha.getId());
						_app.setCurrentCharacter(cha);
						startActivityForResult(myIntent, 0);	
					}
				});
				lv.addView(vi);
			}
		} else {
			if (_listAdapter != null) {
				lv.setAdapter(_listAdapter);
				lv.forceLayout();
				_listAdapter.notifyDataSetChanged();
			}
		}
		if (characters != null) {
			Log.d(TAG, "MainActiviy: populating list, count = " + characters.size());
			Iterator<PFCharacter> i = characters.iterator();
			while (i.hasNext()) {
				PFCharacter cha = i.next();
				Log.d(TAG, "MainActivity:      id = " + cha.getId());
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

	
	private static class CharacterViewHolder {
		PFCharacter character;
		TextView textView;
		CheckBox checkBox;
		RelativeLayout row;
	}

	private static class CharacterArrayAdapter extends ArrayAdapter<PFCharacter> {
		private static final String TAG = "PFCombat";
		private LayoutInflater inflater;
		private List<CharacterViewHolder> holders;
		private List<PFCharacter> checked;
		private PFCombatApplication _app;
		private MainActivity _main;

		public CharacterArrayAdapter(Context context, List<PFCharacter> characters, PFCombatApplication app, MainActivity main) {
			super(context, R.layout.condition_row, R.id.rowTextView, characters.toArray(new PFCharacter[0]));
			holders = new ArrayList<CharacterViewHolder>();
			checked = new ArrayList<PFCharacter>();
			inflater = LayoutInflater.from(context);
			_app = app;
			_main = main;
		}

		public List<PFCharacter> checked() {
			return this.checked;
		}
		
		// Change check boxes status
		public void checkHolder(boolean checked, CharacterViewHolder activeHolder) {
			if (checked) {
				this.checked.add(activeHolder.character);
				Log.d(TAG, "MainActivity: checkbox added");
			} else {
				this.checked.remove(activeHolder.character);
				Log.d(TAG, "MainActivity: checkbox removed,");
		  }
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			PFCharacter character = this.getItem(position);
			CheckBox checkBox;
			TextView textView;
			RelativeLayout row;
			
			Log.v(TAG, String.format("MainActivity: Enter in GetView()"));
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.main_row, null, false);

			  checkBox = (CheckBox) convertView.findViewById(R.id.rowCheckBox2);
				row = (RelativeLayout) convertView.findViewById(R.id.layoutCharacterRow);
				textView = (TextView) convertView.findViewById(R.id.rowTextView2);

				final CharacterViewHolder holder = new CharacterViewHolder();
				holder.checkBox = checkBox;
				holder.character = character;
				holder.row = row;
				holder.textView = textView;
					
			  checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				  @Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						checkHolder(isChecked, holder);
			  	}
				});
				
				row.setOnClickListener(new View.OnClickListener() {
				  @Override
				  public void onClick(View v) {
						Log.v(TAG, String.format("MainActivity: GetView() - Click on row"));
						PFCharacter cha = holder.character;
						locale = v.getContext().getResources().getConfiguration().locale.getDisplayLanguage();
						Log.d(TAG, "MainActivity: GetView() - System language is *" + locale + "*");			
						Log.d(TAG, "MainActivity: GetView() - character selected with id = " + cha.getId());

						_app.setup();

						Intent myIntent = new Intent(v.getContext(), ViewPagerFragmentActivity.class);

						int layout = _main.getResources().getConfiguration().screenLayout;
							Log.d(TAG, "MainActivity: GetView() - screen layout = " + layout);
						if (_main.isScreenSize(Configuration.SCREENLAYOUT_SIZE_XLARGE) || _main.isScreenSize(Configuration.SCREENLAYOUT_SIZE_LARGE)) {
            	myIntent.putExtra("TABLET", true);
						} else {
							myIntent.putExtra("TABLET", false);
						}
						Log.d(TAG, "MainActivity: GetView() - Two steps to the end ");
						myIntent.putExtra("CHARACTER_ID", cha.getId());
						Log.d(TAG, "MainActivity: GetView() - One steps to the end ");
						_app.setCurrentCharacter(cha);
						Log.d(TAG, "MainActivity: GetView() - The end ");
						_main.startActivityForResult(myIntent, 0);	
		  		}
				});

				holders.add(holder);
				convertView.setTag(holder);
			} else {
				CharacterViewHolder viewHolder = (CharacterViewHolder) convertView.getTag();
				checkBox = viewHolder.checkBox;
				row = viewHolder.row;
				textView = viewHolder.textView;
			}
			Log.v(TAG, String.format("MainActivity: GetView() - Filling row"));
			checkBox.setText("");
			textView.setText(character.getName());
			return convertView;
		}
	}
}


package com.dmtprogramming.pathfindercombat;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dmtprogramming.pathfindercombat.R;
import com.dmtprogramming.pathfindercombat.CharacterInfoFragment;
import com.dmtprogramming.pathfindercombat.CharacterCombatFragment;
import com.dmtprogramming.pathfindercombat.ConditionsFragment;
import com.dmtprogramming.pathfindercombat.database.DatabaseHelper;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class ViewPagerFragmentActivity extends FragmentActivity {

	private static final String TAG = "PFCombat";
	
	private PagerAdapter mPagerAdapter;
	private PFCharacter _char;
	private DatabaseHelper databaseHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        Bundle extras = getIntent().getExtras();
        boolean loadTablet = false;
        long _id = -1;
        if (extras != null) {
        	loadTablet = extras.getBoolean("TABLET");
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
					Log.d(TAG, "ViewPagerFragmentActivity: loaded character with id = " + _char.getId());
        } else {
					Log.d(TAG, "ViewPagerFragmentActivity: CHARACTER IS NULL!!!!!!!");
        }
            
        if (loadTablet) {
    		super.setContentView(R.layout.tablet_layout);
        } else {
        	super.setContentView(R.layout.viewpager_layout);
    		this.initialisePaging();
        }
	}

	private void initialisePaging() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, CharacterInfoFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, CharacterCombatFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, ConditionsFragment.class.getName()));
		this.mPagerAdapter  = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
		pager.setAdapter(this.mPagerAdapter);
	}
	
	public PFCharacter getCharacter() {
		return _char;
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.character_menu, menu);
        return true;
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == R.id.menuDelete) {
	        showDeleteDialog();
	        return true;	        
        }
        return super.onOptionsItemSelected(item);
    }

	private void showDeleteDialog() {
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(R.string.delete)
		.setMessage(R.string.really_delete)
		.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
        		try {
            		Dao<PFCharacter, Integer> dao = getHelper().getCharacterDao();
					dao.delete(getCharacter());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			}
		})
		.setNegativeButton(R.string.no, null)
		.show();		
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

package com.dmtprogramming.pathfindercombat;

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
import com.dmtprogramming.pathfindercombat.database.CharacterDataSource;
import com.dmtprogramming.pathfindercombat.database.ConditionDataSource;
import com.dmtprogramming.pathfindercombat.models.PFCharacter;

public class ViewPagerFragmentActivity extends FragmentActivity {

	private static final String TAG = "PFCombat:ViewPagerFragmentActivity";
	
	private PagerAdapter mPagerAdapter;
	private PFCombatApplication _app;
	private PFCharacter _char;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        Bundle extras = getIntent().getExtras();
        boolean loadTablet = false;
        long char_id = -1;
        if (extras != null) {
        	loadTablet = extras.getBoolean("TABLET");
        	char_id = extras.getLong("CHARACTER_ID");
        }
        
        _app = (PFCombatApplication)this.getApplication();
        _app.openDataSources();
        _char = null;

    	if (char_id > 0) {
    		_char = getCharacterDataSource().findCharacter(char_id);
    	}
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        } else {
        	Log.d(TAG, "CHARACTER IS NULL!!!!!!!");
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
	
	public CharacterDataSource getCharacterDataSource() {
		return _app.getCharacterDataSource();
	}
	
	public PFCharacter getCharacter() {
		return _char;
	}
	
    @Override
	public void onResume() {
		_app.openDataSources();
		super.onResume();
	}

	@Override
	public void onPause() {
		_app.closeDataSources();
		super.onPause();
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
				getCharacterDataSource().deletePFCharacter(getCharacter());
				finish();
			}
		})
		.setNegativeButton(R.string.no, null)
		.show();		
	}

	public ConditionDataSource getConditionDataSource() {
		return _app.getConditionDataSource();
	}
}
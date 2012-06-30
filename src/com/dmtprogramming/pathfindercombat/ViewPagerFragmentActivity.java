package com.dmtprogramming.pathfindercombat;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.dmtprogramming.pathfindercombat.R;
import com.dmtprogramming.pathfindercombat.CharacterInfoFragment;
import com.dmtprogramming.pathfindercombat.CharacterCombatFragment;
//import com.dmtprogramming.pathfindercombat.TestFragment;

public class ViewPagerFragmentActivity extends FragmentActivity {

	private static final String TAG = "PFCombat:ViewPagerFragmentActivity";
	
	private PagerAdapter mPagerAdapter;
	private PFCharacterDataSource _datasource;
	private PFCharacter _char;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager_layout);

        _datasource = new PFCharacterDataSource(this);
        _datasource.open();
        _char = null;
		
        _char = null;
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	long _id = extras.getLong("CHARACTER_ID");
        	if (_id > 0) {
        		_char = getDatasource().findCharacter(_id);
        	}
        }
        
        if (_char != null) {
        	Log.d(TAG, "loaded character with id = " + _char.getId());
        }
        
		this.initialisePaging();
	}

	private void initialisePaging() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, CharacterInfoFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, CharacterCombatFragment.class.getName()));
		//fragments.add(Fragment.instantiate(this, TestFragment.class.getName()));
		this.mPagerAdapter  = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);
		//
		ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
		pager.setAdapter(this.mPagerAdapter);
	}
	
	public PFCharacterDataSource getDatasource() {
		return _datasource;
	}
	
	public PFCharacter getCharacter() {
		return _char;
	}
	
    @Override
	public void onResume() {
		_datasource.open();
		super.onResume();
	}

	@Override
	public void onPause() {
		_datasource.close();
		super.onPause();
	}
}
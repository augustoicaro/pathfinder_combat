package com.dmtprogramming.pathfindercombat;

import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.dmtprogramming.pathfindercombat.R;
import com.dmtprogramming.pathfindercombat.CharacterFragment;
import com.dmtprogramming.pathfindercombat.Test2Fragment;

public class ViewPagerFragmentActivity extends FragmentActivity {

	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager_layout);

		this.initialisePaging();
	}

	private void initialisePaging() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, CharacterFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, Test2Fragment.class.getName()));
	//	fragments.add(Fragment.instantiate(this, Tab3Fragment.class.getName()));
		this.mPagerAdapter  = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);
		//
		ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
		pager.setAdapter(this.mPagerAdapter);
	}
}
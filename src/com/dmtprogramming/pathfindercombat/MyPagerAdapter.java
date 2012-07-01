package com.dmtprogramming.pathfindercombat;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;

	public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
	
	public CharSequence getPageTitle(int pos) {
		if (pos == 0) {
			return "Character";
		}
		if (pos == 1) {
			return "Melee";
		}
		return "Other";
	}
}
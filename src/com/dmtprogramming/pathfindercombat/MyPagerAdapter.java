package com.dmtprogramming.pathfindercombat;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * The <code>PagerAdapter</code> serves the fragments when paging.
 * @author mwho
 */
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
}
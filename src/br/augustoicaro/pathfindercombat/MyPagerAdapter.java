package br.augustoicaro.pathfindercombat;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.augustoicaro.pathfindercombat.PFCombatApplication;

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
			return PFCombatApplication.getString(R.string.fcharacter);
		}
		if (pos == 1) {
			return PFCombatApplication.getString(R.string.fcombat);
		}
		if (pos == 2) {
			return PFCombatApplication.getString(R.string.fcondition);
		}
		return "Other";
	}
}

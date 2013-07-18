package br.augustoicaro.pathfindercombat;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import br.augustoicaro.pathfindercombat.MainActivity;

public class MyPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;
	private String locale = MainActivity.locale;

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
			if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			  return "Personagem";
			else
			  return "Character";
		}
		if (pos == 1) {
			if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			  return "Combate";
			else
			  return "Combat";
		}
		if (pos == 2) {
			if(locale.equals("Portugês") || locale.equals("Portuges") || locale.equals("Portuguese") || locale.equals("português") || locale.equals("portugues") || locale.equals("portuguese"))
			  return "Condições";
			else
			  return "Conditions";
		}
		return "Other";
	}
}

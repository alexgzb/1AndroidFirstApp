package com.gezelbom.digiscore2;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Class that currently only displays a Scrollable tabs view. No further logic
 * is created so far
 * 
 * @author Alex
 * 
 */
public class RoundActivity extends Activity {

	// The Adapter} that will provide
	// fragments for each of the sections
	FragmentPagerAdapter fragmentPagerAdapter;

	// The ViewPager that will host the section contents.
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_round);

		// Create the adapter that will return a fragment for each of the three
		// primary tabs of the activity.
		FragmentManager fragmentManager = getFragmentManager();
		fragmentPagerAdapter = new fPagerAdapter(fragmentManager);

		// Set up the ViewPager with the adapter.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(fragmentPagerAdapter);

	}

	/**
	 * A FragmentPagerAdapter that returns a fragment corresponding to one of
	 * the sections/tabs/pages.
	 */
	public class fPagerAdapter extends FragmentPagerAdapter {

		public fPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		/**
		 * Method that gets the current tab by an int index value
		 * 
		 */
		@Override
		public Fragment getItem(int position) {
			// Create a fragment
			Fragment fragment = null;
			
			// If position 0 create and display the first fragment and so on
			if (position == 0) {
				fragment = new RoundFragmentOne();
			} else if (position == 1) {
				fragment = new RoundFragmentTwo();
			} else if (position == 2) {
				fragment = new RoundFragmentThree();
			}
			// Return the fragment to display
			return fragment;
		}

		// total amount of tabs, needed to work properly
		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		/**
		 * Uses the same methodology as getItem to display the correct title for each tab fragment
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_fragment1).toUpperCase(l);
			case 1:
				return getString(R.string.title_fragment2).toUpperCase(l);
			case 2:
				return getString(R.string.title_fragment3).toUpperCase(l);
			}
			return null;
		}
	}

}

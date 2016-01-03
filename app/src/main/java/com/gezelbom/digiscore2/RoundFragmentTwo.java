package com.gezelbom.digiscore2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment to display in the scrollable tabs activity (RoundActivity)
 * @author Alex
 *
 */
public class RoundFragmentTwo extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_round_two, container, false);
	}

}

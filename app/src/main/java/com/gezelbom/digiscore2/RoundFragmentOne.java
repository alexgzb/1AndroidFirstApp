package com.gezelbom.digiscore2;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment to display in the scrollable tabs activity (RoundActivity)
 * @author Alex
 *
 */
public class RoundFragmentOne extends Fragment {

	public RoundFragmentOne() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_round_one, container, false);
	}

}

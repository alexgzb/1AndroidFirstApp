package com.gezelbom.digiscore2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
 * Fragment for the MyFriendsActivity that handles friends and adds them to a
 * file
 * 
 * @author Alex
 * 
 */
public class AddFriendFragment extends Fragment implements
		OnSeekBarChangeListener, OnFocusChangeListener {

	private SeekBar hcpBar;
	private EditText hcpValue;
	private EditText friendName;
	private EditText friendMail;
    private String fileName;
	private String path;

    public AddFriendFragment(){

    }

	// Constructor that takes the path and filename of the Friends file as
	// parameters
	@SuppressLint("ValidFragment")
    public AddFriendFragment(String path, String fileName) {
		this.fileName = fileName;
		this.path = path;
		// Log.d(TAG, "This is the path in the fragment " + path);
	}

    /*
	// Constructor that only takes the filename as parameter
	@SuppressLint("ValidFragment")
    public AddFriendFragment(String fileName) {
		this.fileName = fileName;
	}*/
	/**
	 * On CreateView method initialised when the fragments view is created
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Get the rootView
		View V = inflater.inflate(R.layout.fragment_add_friend, container,
				false);

		friendName = (EditText) V.findViewById(R.id.editTextAddFriendName);
		friendMail = (EditText) V.findViewById(R.id.editTextAddFriendMail);

		// Use the rootView to get hcpValue View
		hcpValue = (EditText) V.findViewById(R.id.editTextAddHCP);

		// Set the hcpValues onFocusChangeListener to this Class/Fragment
		hcpValue.setOnFocusChangeListener(this);

		// Get the Seekbar view from the rootView
		hcpBar = (SeekBar) V.findViewById(R.id.seekBarHCP);

		// Set the seekbar listener to this fragment
		hcpBar.setOnSeekBarChangeListener(this);

		// This method call is used to be able to inflate a new Menu
		setHasOptionsMenu(true);

		// Return the rootView to the Parent ACtivity
		return V;
	}

	/**
	 * On Menu Items clicked
	 */
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {

		// Get the ID for the Menu Item clicked
		int id = item.getItemId();

		// If item is cancel, then pop back to previous in the stack which is
		// the ShowFriendsFragment
		if (id == R.id.add_friend_cancel) {
			getFragmentManager().popBackStack();
		}

		// Add Data to DB or file
		if (id == R.id.add_friend_done) {

			String name;
			String mail;
			int hcp;

			// Get the values from the Edit Textfields
			try {
				hcp = Integer.parseInt(hcpValue.getText().toString());
                String TAG = "AddFriendFragment";
                Log.d(TAG, "Print value got from Mail field " + hcp);

				name = friendName.getText().toString();
				Log.d(TAG, "Print value got from Name field " + name);

				mail = friendMail.getText().toString();
				Log.d(TAG, "Print value got from Mail field " + mail);

				// If name is not empty Create a FriendDTO with the gathered
				// values
				// And use the ManageFile Class to store the friend to a file
				if (!name.isEmpty()) {

                    FriendDTO friend = new FriendDTO(name, mail, hcp);
					ManageFile manager = new ManageFile(path, fileName);
					manager.writeToFile(friend);

					// If name is missing show a Toast that tells the user to
					// enter a name
				} else {
					Toast.makeText(getActivity(),
							getString(R.string.add_valid_name),
							Toast.LENGTH_LONG).show();
				}

				// If Exception is thrown it is most likely missing HCP value,
				// show a TOAST
				// And request a HCP value
			} catch (Exception e) {
				Toast.makeText(getActivity(),
						getString(R.string.add_valid_hcp), Toast.LENGTH_LONG)
						.show();
			}

		}

		// When finished pop back in the stack, in other words back to the
		// previous fragment/activity
		getFragmentManager().popBackStack();
		return true;
	}

	/**
	 * Inflate the add_friend menu and hide menuItem from Main Menu
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		menu.getItem(0).setVisible(false);
		inflater.inflate(R.menu.add_friend, menu);
	}

	/**
	 * Update the hcpValue onSeekBaProgressChanged
	 */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		hcpValue.setText(String.valueOf(progress));

	}

	// Currently not used
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	// Currently not used
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	/**
	 * Method To Update the Seek bar onFocus changed from hcpValue
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {

		if (!hasFocus) {
			if (hcpValue.length() > 0) {

				hcpBar.setProgress(Integer.parseInt(hcpValue.getText()
						.toString()));
			}
		}

	}

}
package com.gezelbom.digiscore2;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Fragment that loads and shows a list of FriendDTOs
 * 
 * @author Alex
 * 
 */
public class ShowFriendsFragment extends ListFragment {

	final String TAG = "ShowFriendsFragment";
	private String path;
	private String fileName;
	private ArrayList<FriendDTO> friends;
	private String[] friendsArray;
	private FriendDTO chosen;

	/**
	 * Constructor for the showFriendsFragment
	 * 
	 * @param path
	 *            path to where to store the file
	 * @param fileName
	 *            Name of the file to store
	 */
	@SuppressLint("ValidFragment")
    public ShowFriendsFragment(String path, String fileName) {

		this.path = path;
		this.fileName = fileName;
	}

    public ShowFriendsFragment() {

        this.path = path;
        this.fileName = fileName;
    }



    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View V = inflater.inflate(R.layout.fragment_show_friends, container,
				false);

		// Create a new Manager and read all the FriendDTOs from the file to a
		// an Array
		// Then store all the names in a StringArray
		ManageFile manager = new ManageFile(path, fileName);
		friends = manager.readFromFile();
		friendsArray = new String[friends.size()];

		// Store the names in the String Array log(debug) rest of the values
		for (int i = 0; i < friends.size(); i++) {

			Log.d(TAG,
					"Name = " + friends.get(i).name + " Mail = + "
							+ friends.get(i).mail + " HCP = "
							+ friends.get(i).hcp);
			friendsArray[i] = friends.get(i).name;
		}

		// Create a ArrayAdapter for the list. use the created String array
		ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1,
				friendsArray);

		// Since the fragment extends ListFragment we can use the super to set
		// the fragments listAdapter to the created one
		super.setListAdapter(ad);

		return V;
	}

	/**
	 * When a list item i chosen or clicked. Create a Toast to display the chosen ones name
	 * Finish the activity and return.
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		// The chosen friend is stored
		chosen = friends.get(position);

		// Creates a toast to display the name of the chosen friend
		Toast.makeText(getActivity(),
				getString(R.string.choosen) + " " + chosen.getName(),
				Toast.LENGTH_SHORT).show();

		Log.d(TAG, Integer.toString(position));

		// Put extra to the activity that is waiting for results and return
		Intent returnIntent = new Intent();
		returnIntent.putExtra("Result", chosen);
		getActivity().setResult(Activity.RESULT_OK, returnIntent);
		getActivity().finish();

	}

}

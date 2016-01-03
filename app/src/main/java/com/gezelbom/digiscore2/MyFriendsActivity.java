package com.gezelbom.digiscore2;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Activity that calls the two different fragments that show or adds friends.
 * Extends FragmentActivity for more fragment managing functionality
 * 
 * @author Alex
 * 
 */
public class MyFriendsActivity extends FragmentActivity {

	String fileName = "myFriends.txt";
	ArrayList<FriendDTO> friends;
	String[] friendsArray;
	ListView list;

	/**
	 * Inflate the activitys own layout and the show the "ShowFriendsFragment"
	 * using getSupportFragmentManager
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_friends);

		// Create the Fragment
		ShowFriendsFragment ShowFriendsFragment = new ShowFriendsFragment(
				getFilesDir().toString(), fileName);
		// Use the fragmentManager to add the fragment
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, ShowFriendsFragment).commit();
	}

	/**
	 * Inflate the menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_friends, menu);
		return true;
	}

	/**
	 * On the menu item "Add-User" is selected call the addFriends method
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.add_friends) {
			// Method to add friend
			addFriend();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Method that Creates a new fragment for AddFriend and replaces the current
	 * fragment Adding the new fragment to the back stack so that the back
	 * button returns back to the previous fragment
	 */
	public void addFriend() {

		AddFriendFragment newFragment = new AddFriendFragment(getFilesDir()
				.toString(), fileName);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container, newFragment)
				.addToBackStack(null)
				.setCustomAnimations(android.R.animator.fade_in,
						android.R.animator.fade_out).commit();

	}
}

package com.gezelbom.digiscore2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Main Activity Shows three buttons that if clicked takes the user to other
 * activites
 * 
 * @author Alex
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * Method to initiate the MyFriendsActivity
	 * 
	 * @param view
	 */
	public void myFriends(View view) {
		Intent intent = new Intent(this, MyFriendsActivity.class);
		startActivity(intent);
	}

	/**
	 * Method to declare and start a Explicit intent to the NewRound Activity
	 * 
	 * @param view
	 */
	public void newRound(View view) {
		Intent intent = new Intent(this, NewRoundActivity.class);
		startActivity(intent);
	}
	
	public void myRounds(View view) {
		Intent intent = new Intent(this, RoundActivity.class);
		startActivity(intent);
	}
}

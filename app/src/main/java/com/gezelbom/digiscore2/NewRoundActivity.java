package com.gezelbom.digiscore2;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Activity for creating a new Round. A RoundDTO is created taking in
 * Golfcourse, date, time, players and number of holes
 * 
 * @author Alex
 * 
 */
public class NewRoundActivity extends Activity implements
		OnItemSelectedListener {

	private TextView dateTextView;
	private TextView timeTextView;
	private final String TAG = "NewRound";
	final Calendar c = Calendar.getInstance();
	int mYear = c.get(Calendar.YEAR);
	int mMonth = c.get(Calendar.MONTH);
	int mDay = c.get(Calendar.DAY_OF_MONTH);
	int mHour = c.get(Calendar.HOUR_OF_DAY);
	int mMin = c.get(Calendar.MINUTE);
	Calendar cal = new GregorianCalendar();
	RoundDTO round;
	GolfCourse course = new GolfCourse();
	FriendDTO playerOne;
	FriendDTO playerTwo;
	FriendDTO playerThree;
	FriendDTO playerFour;
	SharedPreferences sp;
	int holes = 18;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// The roundDTO to pass to the next activity
		round = new RoundDTO();

		// Get the sharedPreferences file
		sp = getPreferences(MODE_PRIVATE);

		// Change the icon in the action bar
		// getActionBar().setIcon(R.drawable.ic_action_name);
		setContentView(R.layout.activity_new_round);

		// Set the DateTextView to the current date, Using Stringformat to make
		// it more aesthetic
		dateTextView = (TextView) findViewById(R.id.textViewDate);
		dateTextView.setText(String.format("%02d-%02d-%02d", mYear, mMonth,
				mDay));

		// Set the TimeTextView to the current time use Stringformat to solve
		// for one digit values
		timeTextView = (TextView) findViewById(R.id.textViewTime);
		timeTextView.setText(String.format("%02d : %02d", mHour, mMin));

		// Set the Spinners adapter preferences and listener
		// Gather the values from String array R.array.courses_array
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setOnItemSelectedListener(this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.courses_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

	}

	/**
	 * Inflate the menu to the action bar
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_round, menu);
		return true;
	}

	/**
	 * React to item selected from Action Bar menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.new_round_cancel) {
			// Finish the activity
			finish();
			return true;
		} else if (id == R.id.new_round_done) {
			// Launch the progress Dialog
			launchProgressDialog(getWindow().getDecorView().getRootView());
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Get the chosen player / friend from the my friends activity This method
	 * is called when the activity that has been called finishes.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		try {
			// Get the requestCode to be able to check which button that was
			// pressed
			Log.d(TAG, "OnActivity return requestCode " + requestCode);
			// The data that was returned is a FriendDTO.
			FriendDTO chosen = (FriendDTO) data.getSerializableExtra("Result");
			Log.d(TAG, "OnActivity returnd FriendDTO name " + chosen.name);

			TextView tV;
			ImageButton iB;

			// Depending on the request code that was pressed
			// Change the textview below the button, change the buttons
			// background
			// Set the rounds player to the chosen player
			switch (requestCode) {
			case R.id.playerOne_button:
				tV = (TextView) findViewById(R.id.textViewPlayerOne);
				tV.setText(chosen.name);
				// Change the buttons icon
				iB = (ImageButton) findViewById(R.id.playerOne_button);
				iB.setImageResource(R.drawable.ic_action_user);
				// Set the rounds player
				round.setPlayerOne(chosen);
				break;
			case R.id.playerTwo_button:
				tV = (TextView) findViewById(R.id.textViewPlayerTwo);
				tV.setText(chosen.name);
				iB = (ImageButton) findViewById(R.id.playerTwo_button);
				iB.setImageResource(R.drawable.ic_action_user);
				round.setPlayerTwo(chosen);
				break;
			case R.id.playerThree_button:
				tV = (TextView) findViewById(R.id.textViewPlayerThree);
				tV.setText(chosen.name);
				iB = (ImageButton) findViewById(R.id.playerThree_button);
				iB.setImageResource(R.drawable.ic_action_user);
				round.setPlayerThree(chosen);
				break;
			case R.id.playerFour_button:
				tV = (TextView) findViewById(R.id.textViewPlayerFour);
				tV.setText(chosen.name);
				iB = (ImageButton) findViewById(R.id.playerFour_button);
				iB.setImageResource(R.drawable.ic_action_user);
				round.setPlayerFour(chosen);
				break;
			default:
				break;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			Log.d(TAG, "No value returned");
		}

	}

	/**
	 * The method that reacts to the buttons for adding player Creates an
	 * Explicit intent to MyFriendsActivity Then starts the activity and waits
	 * for result
	 * 
	 * @param view
	 */
	public void addPlayer(View view) {
		Intent intent = new Intent(this, MyFriendsActivity.class);

		Log.d(TAG, "ID of button = " + Integer.toString(view.getId()));
		startActivityForResult(intent, view.getId());

	}

	/**
	 * Dialog that displays a datepicker and saves the values to the
	 * sharedpreferences file Changes the Textview of the DateTextview to the
	 * chosen date
	 * 
	 * @param v
	 *            The view that initialised the method
	 */
	public void showDatePickerDialog(View v) {

		DatePickerDialog dpd = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {

						// Save the date in Shared preferences file
						Editor spedit = sp.edit();

						spedit.putInt("year", year);
						spedit.putInt("month", monthOfYear);
						spedit.putInt("day", dayOfMonth);
						spedit.commit();

						dateTextView.setText(year + "-" + (monthOfYear + 1)
								+ "-" + dayOfMonth);
						Log.d(TAG, "Inside Dialog " + year + "-"
								+ (monthOfYear + 1) + "-" + dayOfMonth);
					}
				}, mYear, mMonth, mDay);
		dpd.show();
	}

	/**
	 * Dialog that displays a TimePicker and saves the values of Hour and minute
	 * in the sharedPreference file with Keys "hour" and "minute"
	 * 
	 * @param v
	 *            The calling view.
	 */
	public void showTimePickerDialog(View v) {

		TimePickerDialog tpd = new TimePickerDialog(this,
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {

						// Create an Editer and store the picked hour and minute
						Editor spedit = sp.edit();
						spedit.putInt("hour", hourOfDay);
						spedit.putInt("minute", minute);
						spedit.commit();

						// If the integer value for minute is less than two
						// digits add
						// a zero to make it more aesthetic

						timeTextView.setText(String.format("%02d:%02d",
								hourOfDay, minute));

						Log.d(TAG, "Inside Dialog " + hourOfDay + " : "
								+ minute);

					}
				}, mHour, mMin, true);
		tpd.show();
	}

	/**
	 * ProgressDialog that creates a new thread and sleeps for 2 sec
	 * 
	 * @param v
	 */
	public void launchProgressDialog(View v) {
		// Create a new ProgressDialog
		final ProgressDialog pb = new ProgressDialog(this);
		pb.setTitle(getString(R.string.please_wait));
		pb.setMessage(getString(R.string.loading) + "...");
		pb.setCancelable(false);
		pb.show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (Exception e) {
					Log.d(TAG, "Thread sleep problems in progress dialog");
					e.printStackTrace();
				}
				// Dismiss the progressbar after 4 second sleep
				pb.dismiss();
			}
		}).start();
		// Call the startRound Method
		startRound();
	}

	/**
	 * Finalises all variables for the RoundDTO and the starts the activity
	 */
	public void startRound() {

		// Set the Date and Time values and pass the Calendar object to the
		// RoundDTO
		cal.set(sp.getInt("year", mYear), sp.getInt("month", mMonth),
				sp.getInt("day", mDay), sp.getInt("hour", mHour),
				sp.getInt("minute", mMin));
		round.setDate(cal);

		// Log.d(TAG, "Course before start " + course.name);
		round.setCourse(course);

		// Set the number gathered from the radio buttons
		round.setHoles(holes);

		// Put extra and start the activity with an intent
		Intent intent = new Intent(this, RoundActivity.class);
		intent.putExtra("round", round);
		startActivity(intent);
	}

	/**
	 * Methods for the Spinners OnItemSelected Listener
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		course.setName(parent.getItemAtPosition(position).toString());

	}

	/**
	 * Methods for the Spinners OnItemSelected Listener
	 */
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		course.setName(parent.getItemAtPosition(0).toString());
		round.setCourse(course);
	}

	/**
	 * Set the holes method for the radiobutton
	 * 
	 * @param v
	 */
	public void setHoles(View v) {
		RadioButton rb = (RadioButton) v;
		Log.d(TAG, "Radiobutton clicked with text " + rb.getText());
		holes = Integer.parseInt((String) (rb.getText()));
	}
}

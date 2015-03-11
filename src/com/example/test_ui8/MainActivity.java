package com.example.test_ui8;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemSelectedListener {

	protected static int PROFILE_STATUS;
	protected static int PROFILE_COUNTER;
	protected static int SECURITY_LEVEL = 3;
	protected static boolean LOGIN_STATUS = false;
	public static ArrayList<String> PROFILE_LIST;

//	@Override
//	public void onSaveInstanceState(Bundle savedInstanceState) {
//	    // Save the user's current game state
//	    savedInstanceState.putInt("profile_status", PROFILE_STATUS);
//	    savedInstanceState.putInt("profile_counter", PROFILE_COUNTER);
//	    savedInstanceState.putStringArrayList("profile_list", PROFILE_LIST);
//	    // Always call the superclass so it can save the view hierarchy state
//	    super.onSaveInstanceState(savedInstanceState);
//	}
//
//	@Override
//	protected void onRestoreInstanceState (Bundle savedInstanceState){
//		super.onRestoreInstanceState(savedInstanceState);
//	    savedInstanceState.getInt("profile_status");
//	    savedInstanceState.getInt("profile_counter");
//	    savedInstanceState.getStringArrayList("profile_list");		
//	}
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.test_ui8.R.layout.activity_main);

		if (PROFILE_LIST == null) {
			PROFILE_LIST = new ArrayList<String>();
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.turn_off));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.pna_modus));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.profile1));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.profile2));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.profile3));
			Toast.makeText(this, "Profil-Liste neu erstellt",
					Toast.LENGTH_SHORT).show();		
		}
	}

	private void createNewProfile(int key, String name) {





	}

	@Override
	protected void onResume() {
		Spinner spinner = (Spinner) findViewById(com.example.test_ui8.R.id.profile_spinner);
		// create an ArrayAdaptar from the String Array
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				com.example.test_ui8.R.layout.spinner_item, PROFILE_LIST);
		// set the view for the Drop down list
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// set the ArrayAdapter to the spinner
		spinner.setAdapter(dataAdapter);
		// attach the listener to the spinner
		spinner.setOnItemSelectedListener(this);
		super.onResume();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.example.test_ui8.R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		Intent exitIntent = new Intent(this, MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra(
				"com.example.test_ui8.EXIT", true);

		Intent mainActivityIntent = new Intent(this, MainActivity.class);

		Intent profileSettingsIntent = new Intent(this,
				ProfileSettingsActivity.class);

		Intent generalSettingsIntent = new Intent(this,
				GeneralSettingsActivity.class);

		switch (id) {
		case com.example.test_ui8.R.id.sendWorkStatus:
			sendStatusReport();
			return (true);
		case com.example.test_ui8.R.id.statusLog:
			return (true);
		case com.example.test_ui8.R.id.generalSettings:
			if (SECURITY_LEVEL < 1) {
				startActivity(generalSettingsIntent);
				return (true);
			} else if (LOGIN_STATUS) {
				startActivity(generalSettingsIntent);
				return (true);
			}
			passwordLogin(generalSettingsIntent, mainActivityIntent);
			return (true);
		}
		return super.onOptionsItemSelected(item);
	}

	private void sendStatusReport() {
		SendStatusDialogFragment sendStatus = new SendStatusDialogFragment();
		sendStatus.show(getFragmentManager(), null);
	}

	private void passwordLogin(Intent intent, Intent exitIntent) {
		PasswordDialogFragment enterPassword = new PasswordDialogFragment(
				intent, exitIntent);
		enterPassword.show(getFragmentManager(), null);
	}

	private void passwordLogout() {
		LogoutDialogFragment logout = new LogoutDialogFragment();
		logout.show(getFragmentManager(), null);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		parent.getItemAtPosition(pos);
		PROFILE_STATUS=pos;
		Toast.makeText(this, PROFILE_LIST.get(pos)+" wurde gewählt. Key: "+PROFILE_STATUS,
				Toast.LENGTH_SHORT).show();
	}
	
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}

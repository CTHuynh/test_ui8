package com.example.test_ui8;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.test_ui8.PasswordDialogFragment.OnPasswordCheckListener;

public class MainActivity extends Activity implements OnItemSelectedListener,
		OnPasswordCheckListener {

	protected static int PROFILE_STATUS;
	protected static boolean PWP = false;
	protected static ArrayList<String> PROFILE_LIST;

	@Override
	protected void onPause() {
		super.onPause();

		// save static variables to a sharedPreference
		SharedPreferences sharedPref = this.getSharedPreferences(
				"com.presentec.andpna.ui.profile", 0);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("profile_status", PROFILE_STATUS);
		int count = PROFILE_LIST.size();
		editor.putInt("count", count);
		for (int a = 0; a < count; a++) {
			editor.putString("profile_name" + a, PROFILE_LIST.get(a));
		}
		editor.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences generalPref = PreferenceManager
				.getDefaultSharedPreferences(this);
		PWP = generalPref.getBoolean("PasswordProtection", false);
		// load from sharedPreferences if static data gone.
		if (PROFILE_LIST == null) {
			PROFILE_LIST = new ArrayList<String>();
			SharedPreferences sharedPref = this.getSharedPreferences(
					"com.presentec.andpna.ui.profile", 0);
			PROFILE_STATUS = sharedPref.getInt("profile_status", 2);
			int count = sharedPref.getInt("count", 0);
			for (int i = 0; i < count; i++) {
				PROFILE_LIST
						.add(sharedPref.getString("profile_name" + i, null));
			}
			// Toast.makeText(this, "restored from SharedPreference",
			// Toast.LENGTH_SHORT).show();
		}
		// init for first start.
		if (PROFILE_LIST.size() == 0) {
			PROFILE_LIST = new ArrayList<String>();
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.turn_off));
			PROFILE_LIST
					.add(getString(com.example.test_ui8.R.string.pna_modus));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.profile1));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.profile2));
			PROFILE_LIST.add(getString(com.example.test_ui8.R.string.profile3));
//			Toast.makeText(this, "Profil-Liste wurde neu erstellt",
//					Toast.LENGTH_SHORT).show();
		}
		Spinner spinner = (Spinner) findViewById(com.example.test_ui8.R.id.profile_spinner);
		// create an ArrayAdaptar from the String Array
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				com.example.test_ui8.R.layout.spinner_item, PROFILE_LIST);
		// set the view for the Drop down list
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// set the ArrayAdapter to the spinner
		spinner.setAdapter(dataAdapter);
		spinner.setSelection(PROFILE_STATUS);
		// attach the listener to the spinner
		spinner.setOnItemSelectedListener(this);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.example.test_ui8.R.layout.activity_main);
		SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
		PWP = sharedPref.getBoolean("password_protection", false);

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

		Intent generalSettingsIntent = new Intent(this,
				GeneralSettingsActivity.class);

		switch (id) {
		case com.example.test_ui8.R.id.sendWorkStatus:
			sendStatusReport();
			return (true);
		case com.example.test_ui8.R.id.statusLog:
			return (true);
		case com.example.test_ui8.R.id.generalSettings:
			if (!PWP) {
				startActivity(generalSettingsIntent);
				return (true);
			} else {
				passwordLogin(2);
				return (true);
			}
		case com.example.test_ui8.R.id.createProfile:
			if (!PWP) {
				createProfile();
				return (true);
			} else {
				passwordLogin(4);
				return (true);
			}
		case com.example.test_ui8.R.id.deleteProfile:
			if (!PWP) {
				deleteProfile();
				return (true);
			} else {
				passwordLogin(5);
				return (true);
			}

		}
		return super.onOptionsItemSelected(item);
	}

	public void onCheckPassword(boolean pw, int key) {
		if (pw) {
			switch (key) {
			case 0: // turn off local profile

				break;
			case 1: // turn on local profile

				break;
			case 2: // general settings
				Intent generalSettingsIntent = new Intent(this,
						GeneralSettingsActivity.class);
				startActivity(generalSettingsIntent);
				break;
			case 3: // profile settings
				Intent profileSettingsIntent = new Intent(this,
						ProfileSettingsActivity.class);
				startActivity(profileSettingsIntent);
				break;
			case 4: // create local profile
				createProfile();
				break;
			case 5: // delete local profile
				deleteProfile();
				break;
			}
		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		parent.getItemAtPosition(pos);
		PROFILE_STATUS = pos;

		if (PROFILE_STATUS == 1) {
			finish();
			Intent pnaLoginIntent = new Intent(this, PnaLoginActivity.class);
			startActivity(pnaLoginIntent);
			overridePendingTransition(0, 0);
		} else if (PROFILE_STATUS == 0) {
			finish();
			Intent turnOffIntent = new Intent(this, TurnOffActivity.class);
			startActivity(turnOffIntent);
			overridePendingTransition(0, 0);
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

	public void applyModus(View view) {

	}

	public void startProfileSettings(View view) {

		Intent profileSettingsIntent = new Intent(this,
				ProfileSettingsActivity.class);
		if (!PWP)
			startActivity(profileSettingsIntent);
		else
			passwordLogin(3);
	}

	private void createProfile() {
		CreateProfileDialogFragment createProfile = new CreateProfileDialogFragment();
		createProfile.show(getFragmentManager(), null);
	}
	
	private void deleteProfile(){
		DeleteProfileDialogFragment deleteProfile = new DeleteProfileDialogFragment();
		deleteProfile.show(getFragmentManager(), null);
	}
	
	private void sendStatusReport() {
		SendStatusDialogFragment sendStatus = new SendStatusDialogFragment();
		sendStatus.show(getFragmentManager(), null);
	}

	private void passwordLogin(int key) {
		PasswordDialogFragment enterPassword = new PasswordDialogFragment(key);
		enterPassword.show(getFragmentManager(), null);
	}
}

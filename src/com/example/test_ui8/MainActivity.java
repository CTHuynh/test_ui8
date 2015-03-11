package com.example.test_ui8;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends Activity {

	protected static int PROFILE_STATUS;
	protected static int PROFILE_COUNTER;
	protected static int SECURITY_LEVEL = 3;
	protected static boolean LOGIN_STATUS = false;

	public class Profile {
		
		private String mName;
		private int mKey;
		
		Profile(int key, String name){
			mKey=key;
			mName=name;
		}
		public String getName(int key){
			return mName;
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (PROFILE_STATUS == 0)
			PROFILE_STATUS = 1;
	}

    private void createSpinnerDropDown(int key, String name) {
    	 
        //get reference to the spinner from the XML layout
        Spinner spinner = (Spinner) findViewById(R.id.profile_spinner);
        
        //Array list of animals to display in the spinner
        List<String> list = new ArrayList<String>();
        list.add("Bear");
        list.add("Camel");
        list.add("Cat");
        list.add("Cat");
        list.add("Deer");
        list.add("Dog");
        list.add("Goat");
        list.add("Horse");
        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        //set the view for the Drop down list
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);
        //attach the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
    }
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(this);
		String securityLevel = settings.getString("SecurityLevel", "3");
		SECURITY_LEVEL = Integer.valueOf(securityLevel);
		
		Intent exitIntent = new Intent(this, MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra(
				"com.example.test_ui8.EXIT", true);

		switch (PROFILE_STATUS) {
		case 1:
			RadioButton button1 = (RadioButton) findViewById(R.id.profile1);
			button1.setChecked(true);
			break;
		case 2:
			RadioButton button2 = (RadioButton) findViewById(R.id.profile2);
			button2.setChecked(true);
			break;
		case 3:
			RadioButton button3 = (RadioButton) findViewById(R.id.profile3);
			button3.setChecked(true);
			break;
		}
		if (SECURITY_LEVEL == 3 && !LOGIN_STATUS) {
			passwordLogin(exitIntent, exitIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		case R.id.sendWorkStatus:
			sendStatusReport();
			return (true);
		case R.id.statusLog:
			return (true);
		case R.id.generalSettings:
			if (SECURITY_LEVEL < 1) {
				startActivity(generalSettingsIntent);
				return (true);
			} else if (LOGIN_STATUS) {
				startActivity(generalSettingsIntent);
				return (true);
			}
			passwordLogin(generalSettingsIntent, mainActivityIntent);
			return (true);
		case R.id.profileSettings:
			if (SECURITY_LEVEL < 1) {
				startActivity(profileSettingsIntent);
				return (true);
			} else if (LOGIN_STATUS) {
				startActivity(profileSettingsIntent);
				return (true);
			}
			passwordLogin(profileSettingsIntent, mainActivityIntent);
			return (true);
		case R.id.logIn:
			if (LOGIN_STATUS) {
				passwordLogout();
			} else {
				passwordLogin(exitIntent, exitIntent);
			}
			return (true);
		}
		return super.onOptionsItemSelected(item);
	}

	private void sendStatusReport(){
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

	public void onRadioButtonClicked(View view) {

		Intent exitIntent = new Intent(this, MainActivity.class).setFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra(
				"com.example.test_ui8.EXIT", true);
		
		Intent mainActivityIntent = new Intent(this, MainActivity.class);
		
		if (SECURITY_LEVEL > 1 && !LOGIN_STATUS) {
			passwordLogin(mainActivityIntent, exitIntent);
		} else {
			// Is the button now checked?
			boolean checked = ((RadioButton) view).isChecked();

			// Check which radio button was clicked
			switch (view.getId()) {
			case R.id.profile1:
				if (checked)
					PROFILE_STATUS = 1;
				break;
			case R.id.profile2:
				if (checked)
					PROFILE_STATUS = 2;
				break;
			case R.id.profile3:
				if (checked)
					PROFILE_STATUS = 3;
				break;
			}
		}
	}
}

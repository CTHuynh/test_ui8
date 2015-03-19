package com.example.test_ui8;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceFragment.OnPreferenceStartFragmentCallback;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileSettingsActivity extends Activity implements
		OnPreferenceStartFragmentCallback, OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_settings);

		ArrayList<String> local_list = (ArrayList<String>) MainActivity.PROFILE_LIST
				.clone();
		local_list.remove(0);
		local_list.remove(0);
		Spinner spinner = (Spinner) findViewById(com.example.test_ui8.R.id.profile_spinner);
		// create an ArrayAdaptar from the String Array
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				com.example.test_ui8.R.layout.spinner_item, local_list);
		// set the view for the Drop down list
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// set the ArrayAdapter to the spinner
		spinner.setAdapter(dataAdapter);
		spinner.setSelection(MainActivity.PROFILE_STATUS - 2);
		// attach the listener to the spinner
		spinner.setOnItemSelectedListener(this);

		getFragmentManager()
				.beginTransaction()
				.replace(R.id.fragmentsContainer,
						new ProfileSettingsHeaderFragment()).commit();

	}


	public boolean onPreferenceStartFragment(PreferenceFragment caller,
			Preference pref) {

		String next_fragment = pref.getFragment();

		switch (next_fragment) {
		case "SetupFragment":
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new SetupFragment())
					.commit();
			break;
		case "LoneworkerHeaderFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new LoneworkerHeaderFragment()).commit();
			break;
		case "AdministrationFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new AdministrationFragment()).commit();
			break;
		case "ERCFragment":
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new ERCFragment())
					.commit();
			break;
		case "MandownGeneralFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new MandownGeneralFragment()).commit();
			break;

		case "SensorsHeaderFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new SensorsHeaderFragment()).commit();
			break;
		case "ERCNumbersFragment":
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new ERCNumbersFragment())
					.commit();
			break;
		case "SMSNumbersFragment":
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new SMSNumbersFragment())
					.commit();
			break;
		case "LocationSendFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new LocationSendFragment()).commit();
			break;
		case "ManualAlarmFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer, new ManualAlarmFragment())
					.commit();
			break;
		case "MandownIdleFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer, new MandownIdleFragment())
					.commit();
			break;
		case "MandownImpactFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new MandownImpactFragment()).commit();
			break;
		case "MandownTiltFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer, new MandownTiltFragment())
					.commit();
			break;
		case "MandownFallFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer, new MandownFallFragment())
					.commit();
			break;
		default:
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new ErrorFragment())
					.commit();
			break;
		}
		return true;
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {

		parent.getItemAtPosition(pos);
		MainActivity.PROFILE_STATUS = pos + 2;

		Fragment currentFragment = getFragmentManager().findFragmentById(
				R.id.fragmentsContainer);
		getFragmentManager().beginTransaction().detach(currentFragment)
				.attach(currentFragment).commit();

	}

//	public void onRadioButtonClicked(View view) {
//
//		Fragment currentFragment = getFragmentManager().findFragmentById(
//				R.id.fragmentsContainer);
//		getFragmentManager().beginTransaction().detach(currentFragment)
//				.attach(currentFragment).commit();
//	}
	
	
	
	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

}

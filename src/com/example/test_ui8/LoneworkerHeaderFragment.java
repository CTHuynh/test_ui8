package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class LoneworkerHeaderFragment extends PreferenceFragment {

	// implements OnSharedPreferenceChangeListener

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.loneworker_header1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.loneworker_header2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.loneworker_header3);
			break;
		}
	}

	@Override
	public void onStart() {
		getPreferenceScreen().removeAll();
//		loadPreferences();
		// getPreferenceManager().getSharedPreferences()
		// .registerOnSharedPreferenceChangeListener(this);
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.loneworker_header1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.loneworker_header2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.loneworker_header3);
			break;
		}

		super.onStart();
	}


	protected void loadPreferences() {

		String location;
		String manual;
		String idle;
		String tilt;
		String impact;
		String fall;
		int i = MainActivity.PROFILE_STATUS;

		location = "P" + i + "LocationSend";
		manual = "P" + i + "ManualAlarm";
		idle = "P" + i + "MandownCheckIdle";
		tilt = "P" + i + "MandownCheckTilt";
		impact = "P" + i + "MandownCheckImpact";
		fall = "P" + i + "MandownCheckFall";

		((Switch) getView().findViewById(R.string.location_reports))
				.setChecked(getPreferenceManager().getSharedPreferences()
						.getBoolean(location, false));
		((Switch) getView().findViewById(R.string.alarm_button))
				.setChecked(getPreferenceManager().getSharedPreferences()
						.getBoolean(manual, false));
		((Switch) getView().findViewById(R.string.idle))
				.setChecked(getPreferenceManager().getSharedPreferences()
						.getBoolean(idle, false));
		((Switch) getView().findViewById(R.string.tilt))
				.setChecked(getPreferenceManager().getSharedPreferences()
						.getBoolean(tilt, false));
		((Switch) getView().findViewById(R.string.impact))
				.setChecked(getPreferenceManager().getSharedPreferences()
						.getBoolean(impact, false));
		((Switch) getView().findViewById(R.string.fall))
				.setChecked(getPreferenceManager().getSharedPreferences()
						.getBoolean(fall, false));
	}

	protected void savePreference(Switch view) {

//		String name = getString(view.getId());
//
//		switch (name) {
//		case "":
//			String key = "P" + MainActivity.PROFILE_STATUS + "LocationSend";
//			getPreferenceManager().getSharedPreferences().edit()
//					.putBoolean(key, view.isChecked());
//		}
	}

	// @Override
	// public void onStop() {
	// getPreferenceManager().getSharedPreferences()
	// .unregisterOnSharedPreferenceChangeListener(this);
	// super.onStop();
	// }

	// public void onSharedPreferenceChanged(SharedPreferences
	// sharedPreferences,
	// String key) {
	// Preference pref = findPreference(key);
	//
	//
	// if (pref instanceof SwitchPreference) {
	// SwitchPreference switchPref = (SwitchPreference) pref;
	// sharedPreferences.edit().putBoolean(key,switchPref.isChecked()).apply();
	//
	// Context context = getActivity();
	// CharSequence text = "saved: " + key;
	// int duration = Toast.LENGTH_SHORT;
	//
	// Toast toast = Toast.makeText(context, text, duration);
	// toast.show();
	// }
	// }
}

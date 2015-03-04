package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class LoneworkerHeaderFragment extends PreferenceFragment  {

	
//	implements OnSharedPreferenceChangeListener

	
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
//		getPreferenceManager().getSharedPreferences()
//		.registerOnSharedPreferenceChangeListener(this);
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

//	@Override
//	public void onStop() {
//		getPreferenceManager().getSharedPreferences()
//				.unregisterOnSharedPreferenceChangeListener(this);
//		super.onStop();
//	}

//	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
//			String key) {
//		Preference pref = findPreference(key);
//		
//		
//		if (pref instanceof SwitchPreference) {
//			SwitchPreference switchPref = (SwitchPreference) pref;
//			sharedPreferences.edit().putBoolean(key,switchPref.isChecked()).apply();
//			
//			Context context = getActivity();
//			CharSequence text = "saved: " + key;
//			int duration = Toast.LENGTH_SHORT;
//
//			Toast toast = Toast.makeText(context, text, duration);
//			toast.show();
//		}
//	}
}
